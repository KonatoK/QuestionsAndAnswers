import javax.swing.*;

public class QuestionApp
{   public static void main(String[] args)
    {   makeWindow();
    }

    private static void makeWindow()
    {   Window v = new Window();
        v.setSize(250, 100);
        v.setLocation(200, 100);
        v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        v.setDefaultLookAndFeelDecorated(true);
        v.setTitle("Questions & Answers");
        v.setVisible(true);
    }
}
