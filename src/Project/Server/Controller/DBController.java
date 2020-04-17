package Project.Server.Controller;

import java.io.IOException;
import java.util.ArrayList;
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
		ArrayList<String> data = communicator.getInput();

		while (true) {
			switch (Integer.parseInt(data.get(0))) {
			case 1:
				// Login
				communicator.communicateWithClient("1");
				break;
			case 200:
				// View User Courses
				communicator.communicateWithClient("2");
				break;
			case 3:
				// View Course Catalog
				communicator.communicateWithClient("3");
				break;
			case 4:
				// Any back button
				communicator.communicateWithClient("4");
				break;
			case 5:
				communicator.communicateWithClient("5");
				break;
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