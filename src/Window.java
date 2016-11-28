import javax.sound.sampled.*;
import javax.imageio.*;
import javax.swing.*;

import java.nio.charset.*;
import java.nio.file.*;

import java.awt.image.*;
import java.awt.event.*;
import java.awt.*;

import java.util.*;
import java.io.*;

class Window extends JFrame
{   private JComboBox<String> combo;
    private JButton askMe = new JButton("Ask me!");
    private final Random random = new Random();

    private File questionsDir;
    private String[] options;

    private int rightStreak = 0;

    private java.util.List<File> soundFiles = new java.util.ArrayList<>(16);

    public Window()
    {   addComponents();
        addEvents();
        java.net.URL url = ClassLoader.getSystemResource("icon.png");
        Toolkit kit = Toolkit.getDefaultToolkit();
        Image img = kit.createImage(url);
        setIconImage(img);
    }

    public void addComponents()
    {   JPanel p1 = new JPanel();
        initOptions();
        initSound();
        combo = new JComboBox<String>(options);

        p1.add(combo);
        p1.add(askMe);
        p1.add(new JLabel("This isn't even my final form"));
        add(p1);
    }

    public void addEvents()
    {   askMe.addActionListener(new ActionListener()
        {   public void actionPerformed(ActionEvent e)
            {   try
                {   if(combo.getSelectedItem().equals(" "))
                    {   JOptionPane.showMessageDialog(Window.this, "You need to select a set of questions!", "Invalid Option", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    File dir = new File(questionsDir, (String)combo.getSelectedItem());
                    java.util.List<Question> questions = new java.util.ArrayList<>();

                    for(File f:dir.listFiles())
                    {   if(f.isFile())
                        {   java.util.List<String> lines = Files.readAllLines(Paths.get(f.getAbsolutePath()), Charset.forName("UTF8"));
                            if(lines.size() >= 3)
                            questions.add(new Question(lines.get(0), lines.subList(1, lines.size())));
                        }
                    }

                    int rightAnswers = 0;
                    int i;
                    int maxQuestions = (int)Math.min(questions.size() * 1.5, 50);

                    for(i = 0; i < maxQuestions; i++)
                    {   Question question = questions.get(random.nextInt(questions.size()));
                        String[] availableOptions = question.getAnswers();

                        int response = JOptionPane.showOptionDialog
                        (   Window.this,
                            question.getQuestion(),
                            "Question " + (i + 1),
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            availableOptions,
                            availableOptions[0]
                        );

                        if(response == -1)
                        {   JOptionPane.showMessageDialog(Window.this, "You should try it again!", "No Response", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }

                        if(question.isCorrect(availableOptions[response]))
                        {   if(++rightStreak > 15)
                                rightStreak = 1;
                            rightAnswers++;

                            playSound(soundFiles.get(rightStreak));
                            JOptionPane.showMessageDialog(Window.this, "Yes! " + question.getExplanation() + "!", "You're Right!", JOptionPane.INFORMATION_MESSAGE);
                        }
                        else
                        {   rightStreak = 0;
                            playSound(soundFiles.get(0));
                            JOptionPane.showMessageDialog(Window.this, "Sorry, the right answer is: " + question.getRightAnswer(), "Wrong, Sorry!", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                    
                    JOptionPane.showMessageDialog(Window.this, "You got " + rightAnswers + "/" + (i) + " right answers", "Results", JOptionPane.INFORMATION_MESSAGE);
                }
                catch(Exception ex)
                {   JOptionPane.showMessageDialog(Window.this, ex.getMessage(), ex.getClass().getName(), JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void initOptions()
    {   questionsDir = new File("." + File.separator + "questions");

        if((!questionsDir.exists()) && (!questionsDir.mkdirs()))
        {   throw new RuntimeException("Unable to create questions directory");
        }

        java.util.List<String> dirNames = new java.util.ArrayList<>();
        dirNames.add(" ");

        for(File f:questionsDir.listFiles())
        {   if(f.isDirectory())
                dirNames.add(f.getName());
        }

        options = dirNames.toArray(new String[0]);
    }

    private void initSound()
    {   File soundsDir = new File("." + File.separator + "sound");
        for(int i = 0; i <= 15; i++)
        {   soundFiles.add(new File(soundsDir, String.format("%02d.wav", i)));
        }
    }

    public void playSound(final File file)
    {   try
        {   AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch(Exception ex)
        {   System.err.println("Error with playing sound");
        }
    }
}
