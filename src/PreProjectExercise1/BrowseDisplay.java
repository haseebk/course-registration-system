package PreProjectExercise1;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class BrowseDisplay extends JPanel {
	private JScrollPane scrollPane;
	private JList<String> list;

	/**
	 * Create the panel.
	 */
	public BrowseDisplay(JPanel contentPane, JList<String> theList) {
		this.list = theList;
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(60, 75, 592, 288);
		scrollPane.setVisible(true);
		contentPane.add(scrollPane);

	}

}
