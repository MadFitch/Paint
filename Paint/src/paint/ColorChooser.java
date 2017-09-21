package paint;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
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
public class ColorChooser extends JFrame 
{
    static ColorChooser frame;
    
    private static Map<JRadioButton, Color> colors = new HashMap<JRadioButton, Color>();
    public static Color currentColor = Color.BLACK;
    public static Color fill = Color.BLACK;
    public static Color outline = Color.BLACK;
    public static boolean fillCalled = false;
    public static boolean outlineCalled = false;
    
    public static void fill(String [] args) { //for fill colors
        fillCalled = true;
        frame = new ColorChooser();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Fill Colors");
        frame.setVisible(true);
        frame.setSize(700,300);
    }
    
    public static void outline(String [] args) { //for outline colors
        outlineCalled = true;
        frame = new ColorChooser();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("Outline Colors");
        frame.setVisible(true);
        frame.setSize(700,300);
    }
    

    public ColorChooser() {
        JPanel jpRadioButtons = new JPanel();
        jpRadioButtons.setLayout(new GridLayout(3, 1));
        
        JRadioButton red = new JRadioButton("red");
        JRadioButton black = new JRadioButton("black");
        JRadioButton magenta = new JRadioButton("magenta");
        JRadioButton blue = new JRadioButton("blue");
        JRadioButton green = new JRadioButton("green");
        JRadioButton yellow = new JRadioButton("yellow");
        JRadioButton orange = new JRadioButton("orange");
        JRadioButton darkGrey = new JRadioButton("dark grey");
        JRadioButton lightGrey = new JRadioButton("light grey");
        JRadioButton white = new JRadioButton("white");
        JRadioButton pink = new JRadioButton("pink");
        

        red.addActionListener(new MyActionListener());
        black.addActionListener(new MyActionListener());
        magenta.addActionListener(new MyActionListener());
        blue.addActionListener(new MyActionListener());
        green.addActionListener(new MyActionListener());
        yellow.addActionListener(new MyActionListener());
        orange.addActionListener(new MyActionListener());
        darkGrey.addActionListener(new MyActionListener());
        lightGrey.addActionListener(new MyActionListener());
        white.addActionListener(new MyActionListener());
        pink.addActionListener(new MyActionListener());

        jpRadioButtons.add(red);
        jpRadioButtons.add(black);
        jpRadioButtons.add(magenta);
        jpRadioButtons.add(blue);
        jpRadioButtons.add(green);
        jpRadioButtons.add(yellow);
        jpRadioButtons.add(orange);
        jpRadioButtons.add(darkGrey);
        jpRadioButtons.add(lightGrey);
        jpRadioButtons.add(white);
        jpRadioButtons.add(pink);

        colors.put(red, Color.RED);
        colors.put(black, Color.BLACK);
        colors.put(magenta, Color.MAGENTA);
        colors.put(yellow, Color.YELLOW);
        colors.put(green, Color.GREEN);
        colors.put(blue, Color.BLUE);
        colors.put(orange, Color.ORANGE);
        colors.put(darkGrey, Color.darkGray);
        colors.put(lightGrey, Color.LIGHT_GRAY);
        colors.put(white, Color.WHITE);
        colors.put(pink, Color.PINK);

        add(jpRadioButtons, BorderLayout.WEST);

        ButtonGroup bg = new ButtonGroup();
        bg.add(red);
        bg.add(black);
        bg.add(magenta);
        bg.add(blue);
        bg.add(green);
        bg.add(yellow);
        bg.add(orange);
        bg.add(darkGrey);
        bg.add(lightGrey);
        bg.add(white);
        bg.add(pink);
    }

    Color getCurrentColor() {
        return currentColor;
    }

    private class MyActionListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            currentColor = colors.get(e.getSource());
            if(fillCalled == true)
            {
                fill = currentColor;
                fillCalled = false;
            }
            else if(outlineCalled == true)
            {
                outline = currentColor;
                outlineCalled = false;
            }
            frame.dispose();
        }
    }

    public boolean action(Event evtObj, Object arg) {
        if (evtObj.target instanceof Checkbox) {
            repaint();
            return true;
        }
        return false;
    }
}

