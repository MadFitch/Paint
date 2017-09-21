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

import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class About {
{
	try
	{
		File file = new File("images/aboutLogo.gif");
		BufferedImage image = ImageIO.read(file);
		JFrame about = new JFrame("About");
		JLabel label = new JLabel(new ImageIcon(image));
		about.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		about.getContentPane().add(label);
		about.pack();
		about.setLocation(200, 200);
		about.setVisible(true);
	}
	catch(Exception e){}
}
}
