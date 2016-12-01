import java.util.logging.*;
import java.awt.image.*;
import javax.imageio.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public final class ImagePanel extends JPanel
{   private BufferedImage image;

    public ImagePanel(File i)
    {   try
        {   image = ImageIO.read(i);
            setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
        }
        catch(IOException ex)
        {
        }
    }

    @Override
    protected void paintComponent(Graphics g)
    {   super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}
