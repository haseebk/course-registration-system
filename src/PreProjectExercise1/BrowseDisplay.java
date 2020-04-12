package PreProjectExercise1;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BrowseDisplay extends JPanel {
	/**
	 * This pane is the scroll-able list on the main window in response to the
	 * browse button being pressed
	 */
	private JScrollPane scrollPane;
	/**
	 * This will be used to supply the scroll-able list with data, which is then
	 * displayed upon Browse button being pressed
	 */
	private JList<String> list;
	/**
	 * This member variable is the Binary Search Tree that will hold student records
	 */
	private BinSearchTree theTree;

	/**
	 * Create the panel.
	 */
	public BrowseDisplay(JPanel contentPane, JList<String> theList, BinSearchTree theTree) {
		this.list = theList;
		this.theTree = theTree;
		theList.setFont(new Font("Courier New", Font.BOLD, 15));

		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(60, 75, 592, 288);

		scrollPane.setVisible(true);
		MouseListener mouseListener = new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					String selectedItem = (String) list.getSelectedValue();
					int spacePos = selectedItem.indexOf(" ");
					if (spacePos > 0) {
						String searchID = selectedItem.substring(0, spacePos);
						Node result = theTree.find(theTree.root, searchID);
						
						if (result == null) {
							JOptionPane.showMessageDialog(null, "\nAn error occurred!",
									" Warning", JOptionPane.PLAIN_MESSAGE);

						} else {
							JOptionPane.showMessageDialog(null,
									"Student ID: " + result.data.id + "\nFaculty: " + result.data.faculty + "\nMajor: "
											+ result.data.major + "\nYear: " + result.data.year,
									" Result", JOptionPane.INFORMATION_MESSAGE);
						}
					}
				}
			}
		};
		list.addMouseListener(mouseListener);

		contentPane.add(scrollPane);

	}

}
