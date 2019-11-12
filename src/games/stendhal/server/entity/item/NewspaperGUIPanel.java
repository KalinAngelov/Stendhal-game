package games.stendhal.server.entity.item;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class NewspaperGUIPanel extends JFrame
{
  public NewspaperGUIPanel()
  {
	  setTitle("Newspaper");
	  Container contents = getContentPane();
	  contents.setLayout(new FlowLayout());
	  contents.add(new JLabel("Lorem ipsum dolor sit amet, sed leo et. Quam malesuada ultrices sit" 
	                    + "imperdiet, maecenas tempus. Proin in, wisi aenean, lacus porttitor, quis"
			            + "libero vehicula. ."));
	  JButton button = new JButton("Close");
	  button.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	dispose();
	        }
	    });
	  contents.add(button);
	  setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	  pack();
	  setVisible(true);
  }
  
  
}
