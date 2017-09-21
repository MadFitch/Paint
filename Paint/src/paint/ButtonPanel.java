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
import java.awt.event.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import java.awt.image.BufferedImage;

public class ButtonPanel extends JPanel
{
    PaintPanel pp;
    Color FILL;
    Color OUTLINE;
    ColorChooser tbw = new ColorChooser();
    
    private void fill()
    {
        FILL = tbw.fill;
    }
    private void outline()
    {
        OUTLINE = tbw.outline;
    }
   
    ButtonPanel(PaintPanel pp)
    {
       this.pp = pp;
       
       //Clear Button
       JButton clear = new JButton("Clear");
       add(clear);
       clear.addActionListener(new ButtonListener());
        
       //Enter Text Button
       JButton enterWord = new JButton("Enter Text");
       add(enterWord);
       enterWord.addActionListener(new ButtonListener());
       
       //Straight Line Button
       BufferedImage straight = new BufferedImage(180,180,2);
       Graphics2D straightL = straight.createGraphics();
       JButton line = new JButton("Line");
       straightL.setColor(Color.red);
       straightL.drawLine(0, 180, 180, 0);
       ImageIcon li = new ImageIcon(straight);
       line.setIcon(li);
       line.setPreferredSize(new Dimension(60,40));
       add(line);
       line.addActionListener(new ButtonListener());
       
       //Free Draw Button
       BufferedImage freeDrawing = new BufferedImage(180,180,2);
       Graphics2D freeD = freeDrawing.createGraphics();
       JButton freeDraw = new JButton("Draw");
       freeD.setColor(Color.red);
       freeD.drawOval(80,0,300,200);
       ImageIcon fd = new ImageIcon(freeDrawing);
       freeDraw.setIcon(fd);
       freeDraw.setPreferredSize(new Dimension(50,40));
       add(freeDraw);
       freeDraw.addActionListener(new ButtonListener());
       
       //Rectangle Button
       BufferedImage rectangle = new BufferedImage(180,180,2);
       Graphics2D empRec = rectangle.createGraphics();
       JButton rect = new JButton("Rectangle");
       empRec.setColor(Color.red);
       empRec.drawRect(60, 40, 70, 95);
       ImageIcon er = new ImageIcon(rectangle);
       rect.setIcon(er);
       rect.setPreferredSize(new Dimension(50,40));
       add(rect);
       rect.addActionListener(new ButtonListener());
       
       //Filled Rectangle Button
       BufferedImage fillRectangle = new BufferedImage(180,180,2);
       Graphics2D fillR = fillRectangle.createGraphics();
       JButton fillRect = new JButton("Filled Rectangle");
       fillR.setColor(Color.red);
       fillR.fillRect(60, 40, 70, 95);
       ImageIcon fr = new ImageIcon(fillRectangle);
       fillRect.setIcon(fr);
       fillRect.setPreferredSize(new Dimension(50,40));
       add(fillRect);
       fillRect.addActionListener(new ButtonListener());
       
       //Empty Oval Button
       BufferedImage emptyOval = new BufferedImage(180,180,2);
       Graphics2D emptyO = emptyOval.createGraphics();
       JButton oval = new JButton("Oval");
       emptyO.setColor(Color.red);
       emptyO.drawOval(60,40,70,95);
       ImageIcon eo = new ImageIcon(emptyOval);
       oval.setIcon(eo);
       oval.setPreferredSize(new Dimension(50,40));
       add(oval);
       oval.addActionListener(new ButtonListener());
       
       //Filled Oval Button
       BufferedImage filledOval = new BufferedImage(180,180,2);
       Graphics2D fillO = filledOval.createGraphics();
       JButton fillOval = new JButton("Filled Oval");
       fillO.setColor(Color.red);
       fillO.fillOval(60, 40, 70, 95);
       ImageIcon fo = new ImageIcon(filledOval);
       fillOval.setIcon(fo);
       fillOval.setPreferredSize(new Dimension(50,40));
       add(fillOval);
       fillOval.addActionListener(new ButtonListener());
       
       //Fill Color Button
       JButton fillColor = new JButton("Fill Color");
       add(fillColor);
       fillColor.addActionListener(new ButtonListener());
       
       //Outline Color Button
       JButton outlineColor = new JButton("Outline Color");
       add(outlineColor);
       outlineColor.addActionListener(new ButtonListener());
       
       //Thin Stroke Button
       JButton thinStroke = new JButton("Thin Stroke");
       add(thinStroke);
       thinStroke.addActionListener(new ButtonListener());
       
       //Thick Stroke Button
       JButton thickStroke = new JButton("Thick Stroke");
       add(thickStroke);
       thickStroke.addActionListener(new ButtonListener());
       
       setLayout(new GridLayout(6, 1));
       setBackground(Color.black);
    }

	public class ButtonListener implements ActionListener //send integer across to figure out which button was pushed
	{
	   public void actionPerformed(ActionEvent ae)
	   {
	       String command = ae.getActionCommand();
	       switch(command)
	       {
	        case"Clear":
	    	    pp.clear();
	    	    break;
	        case"Rectangle":
                case"Line":
                case"Oval":
                case"Filled Rectangle":
                case"Filled Oval":
                case"Draw":
                    fill();
                    outline();
                    pp.setColor(FILL);
                    pp.setOutline(OUTLINE);
        	    pp.setN(command);
                    break;
                case"Fill Color":
                    tbw.fill(null);
        	    break;	    
                case"Outline Color":
                    tbw.outline(null);
                    break;
                case"Thin Stroke":
                    pp.setStroke(2);
                    break;
                case"Thick Stroke":
                    pp.setStroke(6);
                    break;
                case"Enter Text":
                    new Input().setVisible(true);
                    pp.setN(command);
                    break;
	       }
	   }
	 }
        
    public class Input extends JFrame
    {
        String userWord = "";
        JTextField userInput = new JTextField(30);
        JButton submit = new JButton("Submit");
        private Input()
        {
           super("Enter a word or string to be drawn");
            JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            setSize(900, 400);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null); // This center the window on the screen
            submit.addActionListener( (e)-> {
                submit();
                dispose();
            });
            centerPanel.add(userInput);
            JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            southPanel.add(submit);
            Box theBox = Box.createVerticalBox();
            theBox.add(Box.createVerticalStrut(75));
            theBox.add(centerPanel);
            theBox.add(Box.createVerticalStrut(0));
            theBox.add(southPanel);
            add(theBox);
        }

        private void submit()
        {
            userWord = userInput.getText();
            pp.setWord(userWord);
        }
    }
}