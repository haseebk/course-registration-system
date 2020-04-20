package Project.Client.Controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import Project.Client.View.FrontEnd;
import Project.Server.Model.Backend;

public class GUIController {
	private CommController communicator;
	private FrontEnd frontEnd;

	public GUIController(CommController communicator, FrontEnd frontEnd) {
		this.communicator = communicator;
		this.frontEnd = frontEnd;

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

	public void runClient() {
		frontEnd.displayGUI();
	}

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

}