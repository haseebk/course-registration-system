package Project.Client.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import Project.Client.View.FrontEnd;
import Project.Server.Model.Backend;

/**
 * The purpose of this class is to start up the client with the graphical user interface and respond to 
 * commands activated by the user.
 * 
 * @author Muhammad Tariq, Haseeb Khan
 * @version 1.0
 * @since April 20, 2020
 */
public class GUIController {
	/**
	 * attribute for communication controller class
	 */
	private CommController communicator;
	
	/**
	 * attribute for the frontend class
	 */
	private FrontEnd frontEnd;

	/**
	 * the constructor of this class. creates mouse events for the GUI 
	 * and sets the communication controller and the front end to specified attributes.
	 * @param communicator communication controller
	 * @param frontEnd front end GUI
	 */
	public GUIController(CommController communicator, FrontEnd frontEnd) {
		this.setCommunicator(communicator);
		this.setFrontEnd(frontEnd);

		frontEnd.addSubmitLoginMouseClicked(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				communicator.communicate("1");
				System.out.println(communicator.recieveRawInput());
			}

		});

		frontEnd.addViewMyCoursesMouseClicked(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				communicator.communicate("2");
				System.out.println(communicator.recieveRawInput());
			}

		});
		frontEnd.addViewCourseCatalogMouseClicked(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				communicator.communicate("3");
				System.out.println(communicator.recieveRawInput());
			}

		});
		frontEnd.addBackMouseClicked(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				communicator.communicate("4");
				System.out.println(communicator.recieveRawInput());
			}

		});
	}
	
	/**
	 * displays GUI using the frontend class
	 */
	public void runClient() {
		getFrontEnd().displayGUI();
	}

	/**
	 * main function
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		CommController communicator;
		FrontEnd frontEnd = null;
		do {
			try {
				communicator = new CommController("localhost", 8089);
				Backend backendObject = (Backend) communicator.getObjectInputStream().readObject();
				frontEnd = new FrontEnd(backendObject);
			} catch (Exception e) {
				System.err.println("Error: Unable to run communication controller!");
				e.printStackTrace();
				System.err.println();
				communicator = null;
			}
		} while (communicator == null);
		GUIController controller = new GUIController(communicator, frontEnd);
		controller.runClient();
	}

	public CommController getCommunicator() {
		return communicator;
	}

	public void setCommunicator(CommController communicator) {
		this.communicator = communicator;
	}

	public FrontEnd getFrontEnd() {
		return frontEnd;
	}

	public void setFrontEnd(FrontEnd frontEnd) {
		this.frontEnd = frontEnd;
	}

}