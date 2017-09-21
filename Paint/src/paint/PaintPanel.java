package paint;
/*
 * authors@(Dennis Berg, Madison Fichtner)
 * date(December 1, 2016)
 * PartnerLab(GUI - Paint Program)
 * labDescription(Be able to change colors on your drawing objects. Color Palette chooser. A display of current colors. Outline and fill colors. 
 * JMenuBar with a File, Edit, Choices, Help pulldown menus, in Help you need a About choice that pops up a splash screen with your image logo on it. 
 * A freehand drawing button so you can draw like with a pencil in current color. Ability to change stroke thickness. 
 * Draw on the buttons instead of words, like the example I gave last week. 
 * Be able to change the stroke size....really easy. 
 * Make the buttons look good in the layout, spacing between them, same size, try to have them highlight in some way when mouse is over the button. 
 * Similar to the way my example program worked. 
 * Add the ability to add text to your image.
 * Re-sizing doesn't erase your image, minimize and re-sizing should not change the drawing done. 
 */
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Ellipse2D;


public class PaintPanel extends JPanel
{
    int STROKE = 3;
    String enteredWord = "";
    Color FILLCOLOR = Color.BLACK;
    Color COLOR = Color.BLACK;
    int startX, flag, startY, endX, endY;
    BufferedImage grid;
    Graphics2D gc;
    private ArrayList<Point> points = new ArrayList<Point>();
    BufferedImage i = new BufferedImage(200, 200, BufferedImage.TYPE_BYTE_INDEXED);
     int w;
     int h;
    
    public PaintPanel()
    {
       startX = startY =0;
       endX=100;
       endY=100;
       flag = 0;
       
       addMouseMotionListener(new MouseComp());
       addMouseListener(new MouseComp());
    }


    public void clear()
    {
        grid = null;
        repaint();
    }
        
    @Override
    public void paintComponent(Graphics g)
    {  
         super.paintComponent(g);
         Graphics2D g2 = (Graphics2D)g;
         if(grid == null)
         {
            int w = this.getWidth();
            int h = this.getHeight();
            grid = (BufferedImage)(this.createImage(w,h));
            gc = grid.createGraphics();
           
         }
         g2.drawImage(grid, null, 0, 0);  
     }
    
    //Shape Drawing Methods
    public void fillOval()
    {
        gc =  grid.createGraphics();
        gc.setStroke(new BasicStroke(STROKE));
        gc.setColor(FILLCOLOR);
        gc.fillOval(Math.min(startX, endX), Math.min(startY, endY), Math.abs(startX - endX), Math.abs(startY - endY));
        gc.setColor(COLOR);
        gc.setStroke(new BasicStroke(STROKE));
        gc.drawOval(Math.min(startX, endX), Math.min(startY, endY), Math.abs(startX - endX), Math.abs(startY - endY));
        repaint();
    }
    
    public void oval()
    {
        gc =  grid.createGraphics();
        gc.setStroke(new BasicStroke(STROKE));
        gc.setColor(COLOR);
        gc.drawOval(Math.min(startX, endX), Math.min(startY, endY), Math.abs(startX - endX), Math.abs(startY - endY));
        repaint();
    }
    
    public void fillRectangle()
    {
        gc =  grid.createGraphics();
        gc.setColor(FILLCOLOR);
        gc.fill(new Rectangle2D.Double(Math.min(startX, endX), Math.min(startY, endY), Math.abs(startX - endX), Math.abs(startY - endY)));
        gc.setColor(COLOR);
        gc.setStroke(new BasicStroke(STROKE));
        gc.drawRect(Math.min(startX, endX), Math.min(startY, endY), Math.abs(startX - endX), Math.abs(startY - endY));
        repaint();
    }   
    
    public void rectangle()
    {
        gc =  grid.createGraphics();
        gc.setStroke(new BasicStroke(STROKE));
        gc.setColor(COLOR);
        gc.drawRect(Math.min(startX, endX), Math.min(startY, endY), Math.abs(startX - endX), Math.abs(startY - endY));
        repaint();
    }
    
    public void line()
    {
        gc =  grid.createGraphics();
        gc.setStroke(new BasicStroke(STROKE));
        gc.setColor(COLOR);
        gc.drawLine(startX, startY, endX, endY);
        repaint();
    }
    
    public void freeLine()
    {
        gc =  grid.createGraphics();
        gc.setStroke(new BasicStroke(STROKE));
        gc.setColor(COLOR);
        for(int i = 0; i < points.size() - 2; i++)
        {
            Point p1 = points.get(i);
            Point p2 = points.get(i+1);
            gc.drawLine(p1.x, p1.y, p2.x, p2.y);
        }
        repaint();
    }
    
    public void drawWord()
    {
        gc =  grid.createGraphics();
        gc.setFont(new Font("TimesRoman", Font.PLAIN, 50));
        gc.drawString(enteredWord, startX, startY);
        repaint();
    }
    
    public void setWord(String s)
    {
        enteredWord = s;
    }
    public void setStroke(int i)
    {
        STROKE = i;
    }
    public void setColor(Color c)
    {
        FILLCOLOR = c;
    }
    public void setOutline(Color c)
    {
        COLOR = c;
    }
    String n;
    public void setN(String n)
    {
    	this.n=n;
    }
    
    public class MouseComp implements MouseListener, MouseMotionListener
    {
        public void mouseClicked(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        
        public void mousePressed(MouseEvent e)
        {
            startX = e.getX();
            startY = e.getY();
            points = new ArrayList<Point>();
            if(n == "Enter Text")
                drawWord();
        }
        
        public void mouseDragged(MouseEvent e)
        {
            points.add(e.getPoint());
        }
        
        public void mouseReleased(MouseEvent e)
        {
            //Graphics2D g2 = (Graphics2D)getGraphics();
            endX = e.getX();
            endY = e.getY();
            //Draws a shape according to what button was pressed
            if(n == null)
            {
                //do something?
            }
            switch(n)
            {
            case"Clear":
                clear();
            case"Rectangle":
            	rectangle();
            	break;
            case"Line":
            	line();
            	break;
            case"Oval":
            	oval();
            	break;
            case"Filled Rectangle":
            	fillRectangle();
            	break;
            case"Filled Oval":
                fillOval();
            	break;   
            case"Draw":
                freeLine();
                break;
            case"Enter String":
                drawWord();
                break;
            }
            System.out.println("Current Fill Color: " + FILLCOLOR);
            System.out.println("Current Border Color: " + COLOR + "\n");
        }
        public void mouseExited(MouseEvent evt){}
        public void mouseMoved(MouseEvent e){}
    }   
}	 


