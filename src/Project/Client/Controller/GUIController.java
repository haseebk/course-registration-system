package Project.Client.Controller;

import java.io.IOException;

import Project.Client.View.FrontEnd;

public class GUIController {
	private CommController communicator;
	private FrontEnd frontEnd;

	public GUIController(CommController communicator, FrontEnd frontEnd) {
		this.communicator = communicator;
		this.frontEnd = frontEnd;
	}

	public void runClient() {
		frontEnd.runClient();
	}

	public static void main(String[] args) throws IOException {
		CommController communicator;
		do {
			try {
				communicator = new CommController("localhost", 9898);
			} catch (Exception e) {
				System.err.println("Error occured while creating CommController");
				e.printStackTrace();
				System.err.println();
				communicator = null;
			}
		} while (communicator == null);
		GUIController controller = new GUIController(communicator, new FrontEnd());
		controller.runClient();
	}

}