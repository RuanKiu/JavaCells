import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.Graphics;

public class CustomPanel extends JPanel implements ActionListener, ComponentListener
{
  private Timer timer;
  private ActionListener calculateFrame;
  private Timer frameTimer;
  private int resolution;
  private Engine engine;

  public CustomPanel()
  {
    timer = new Timer(15, this);
    calculateFrame = new ActionListener() 
    {
      public void actionPerformed(ActionEvent e)
      {
        engine.calculateFrame();
      }
    };
    frameTimer = new Timer(50, calculateFrame);
    resolution = 5;
    engine = new Engine(700 / resolution, 700 / resolution);
    engine.randomize();

    addComponentListener(this);
   
    setSize(700, 700);
    setFocusable(true); 
    requestFocusInWindow();

    timer.start();
    frameTimer.start();
  }
  public void actionPerformed(ActionEvent e)
  {
    repaint();
  }
  public void componentResized(ComponentEvent e)
  {
    engine.resize(getWidth() / resolution, getHeight() / resolution);
  }
  public void componentShown(ComponentEvent e) {}
  public void componentHidden(ComponentEvent e) {}
  public void componentMoved(ComponentEvent e) {}
  public void paintComponent(Graphics g)
  {
    for (int r = 0; r < engine.getMap().length; r++)
    {
      for (int c = 0; c < engine.getMap()[r].length; c++)
      {
        if (engine.getMap()[r][c])
          g.fillRect(c * resolution, r * resolution, resolution, resolution); 
      }
    }
  }
}

