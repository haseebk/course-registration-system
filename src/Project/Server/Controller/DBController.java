package Project.Server.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Project.Client.View.FrontEnd;
import Project.Server.Model.Backend;

public class DBController {
	private ServerCommController communicator;
	private static Backend backend;

	public DBController(ServerCommController communicator, Backend backend) {
		this.communicator = communicator;
		DBController.backend = backend;
	}
	private void runServer() {
		System.out.println("Server is running");
		communicator.establishConnection(backend);
		operateServer();
	}
	
	
	private void operateServer() {
		while (true) {
			ArrayList<String> data = communicator.getInput();

			switch (Integer.parseInt(data.get(0))) {
			case 1:
				// Search catalog
				communicator.communicateWithClient(backend.viewCourseCatalogue());
				break;
			case 2:
				// Add course
				DBController.backend.addCourse(data.get(1), data.get(2), Integer.parseInt(data.get(3)),
						Integer.parseInt(data.get(4)));
				break;
			case 3:
				// Remove course
				break;
			case 4:
				// View Course catalog
				break;
			case 5:
				// View Course taken
				break;
			case 6:
				// Information request
				switch (Integer.parseInt(data.get(1))) {
				case 1:

					break;
				case 2:

					break;
				case 3:

					break;
				case 4:

					break;
				case 5:

					break;
				}
				break;
			default:

			}
		}		
	}
	public static void main(String[] args) throws IOException {
		Backend backEnd = new Backend();

		ServerCommController myServer = new ServerCommController(8089);
		DBController controller = new DBController(myServer, backEnd);
		controller.runServer();

		
	}
}