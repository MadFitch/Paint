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
import java.awt.event.*;
import java.awt.*;
import java.io.IOException;
import javax.swing.JFileChooser;
import java.io.File;

public class Paint extends JFrame //frame
{
    JMenuBar menuBar;
    JMenu menu;
    JFileChooser fileChooser = new JFileChooser();
    
    Paint() 
    {
        super("Paint Program");
        initUI();
        setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PaintPanel pp = new PaintPanel();
        this.setLocationRelativeTo(null);
        getContentPane().add(pp, BorderLayout.CENTER);
        getContentPane().add(new ButtonPanel(pp), BorderLayout.WEST);
        setVisible(true);
    }

    public static void main(String [] args) throws IOException
    {
       //new Paint();
        EventQueue.invokeLater(() -> {
        Paint ex = new Paint();
        ex.setVisible(true);
    });
    }
    
    
    
    public void initUI()
    {
        createMenuBar();
    }
    private void createMenuBar() {
        JMenuBar menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon("exit.png");

        JMenu file = new JMenu("File");
        JMenu help = new JMenu("Help");
        
        file.setMnemonic(KeyEvent.VK_F);
        help.setMnemonic(KeyEvent.VK_F);
        
        JMenuItem about = new JMenuItem("About", icon);

        JMenuItem open = new JMenuItem("Open", icon);
        open.setMnemonic(KeyEvent.VK_E);
        open.setToolTipText("Open File");
        open.addActionListener((ActionEvent event) -> {
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
            int result = fileChooser.showOpenDialog(new JFrame());
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                System.out.println("Selected file: " + selectedFile.getAbsolutePath());
            }
        });
        
        JMenuItem exit = new JMenuItem("Exit", icon);
        exit.setMnemonic(KeyEvent.VK_E);
        exit.setToolTipText("Exit application");
        exit.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        about.addActionListener((ActionEvent event) -> {
            new About();
        });
        
        help.add(about);
        file.add(exit);
        file.add(open);
        
        menubar.add(file); //add functionality to add a file/picture
        menubar.add(help);

        setJMenuBar(menubar);
    }
}