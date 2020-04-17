package Project.Client.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

import Project.Client.View.FrontEnd;
import Project.Client.View.MyCourses;
import Project.Client.View.CourseCatalog;
import Project.Client.View.StandardUserHome;
import Project.Server.Model.Backend;
import Project.Client.View.Login;

public class GUIController {
	private CommController communicator;
	private FrontEnd frontEnd;

	public GUIController(CommController communicator, FrontEnd frontEnd) {
		this.communicator = communicator;
		this.frontEnd = frontEnd;

		frontEnd.addSubmitLoginMouseClicked(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}

		});

		frontEnd.addViewMyCoursesMouseClicked(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}

		});
		frontEnd.addViewCourseCatalogMouseClicked(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				communicator.communicate("1");

				System.out.println(communicator.recieveRawInput());
			}

		});
	}

	public void runClient() {
		frontEnd.displayGUI();
	}

	public static void main(String[] args) throws IOException {
		CommController communicator;
		DefaultListModel<String> theList = new DefaultListModel<String>();
		String s = "TESTING";
		String s2 = "TESTING2";
		theList.addElement(s);
		theList.addElement(s2);

		FrontEnd frontEnd = null;
		do {
			try {
				communicator = new CommController("localhost", 8089);
		        Backend test = (Backend) communicator.getObjectInputStream().readObject();
		        frontEnd = new FrontEnd(theList, test);
			} catch (Exception e) {
				System.err.println("Error occured while creating CommController");
				e.printStackTrace();
				System.err.println();
				communicator = null;
			}
		} while (communicator == null);
		GUIController controller = new GUIController(communicator, frontEnd);

		controller.runClient();
	}

}