package Project.Server.Controller;

import java.io.IOException;
import java.util.ArrayList;
import Project.Server.Model.Backend;

public class DBController {
    private ServerCommController communicator;
    private Backend backend;

    public DBController (ServerCommController communicator, Backend backend) {
		this.communicator = communicator;
		this.backend = backend;
	}
    public static void main (String [] args) throws IOException{
		ServerCommController myServer = new ServerCommController (9898);
		DBController controller = new DBController(myServer, new Backend());
		
		controller.communicator.runServer();
        
        while (true) {
			ArrayList<String> data = controller.communicator.getInput();
			
			switch (Integer.parseInt(data.get(0))) {
			case 1:
				//Search catalogue
				controller.communicator.communicateWithClient(controller.backend.searchCatalogue(data.get(1), Integer.parseInt(data.get(2))));
				break;
			case 2:
				//Add course
				controller.backend.addCourse(data.get(1),data.get(2),Integer.parseInt(data.get(3)),Integer.parseInt(data.get(4)));
				break;
			case 3:
				//Remove course
				controller.communicator.communicateWithClient(controller.backend.removeCourse(data.get(1), data.get(2),Integer.parseInt(data.get(3))));
				break;
			case 4:
				//View Course Catalogue/List
				controller.communicator.communicateWithClient(controller.backend.viewCourseCatalogue());
				break;
			case 5:
				//View Course taken (Student: what course they took) Basically student list
				controller.communicator.communicateWithClient(controller.backend.viewCourseTaken());
				break;
			case 6:
				//Information request
				switch (Integer.parseInt(data.get(1))) {
				case 1:
					if (data.size()<4) {
						controller.communicator.communicateWithClient("1");
						break;
					}
					controller.communicator.communicateWithClient(controller.backend.courseExist(data.get(2), Integer.parseInt(data.get(3))));
					break;
				case 2:
					if (data.size()<3) {
						controller.communicator.communicateWithClient("1");
						break;
					}
					controller.communicator.communicateWithClient(controller.backend.studentExist(data.get(2)));
					break;
				case 3:
					if (data.size()<3) {
						controller.communicator.communicateWithClient("1");
						break;
					}
					controller.communicator.communicateWithClient(controller.backend.studentCanEnroll(data.get(2)));
					break;
				case 4:
					//REMOVED CODE
					break;
				case 5:
					controller.communicator.communicateWithClient(controller.backend.canEnroll(data.get(2), data.get(3), Integer.parseInt(data.get(4)),Integer.parseInt(data.get(5))));
					break;
				}
				break;
			default:
				//closes server if input is none of the above
				System.out.println("\nServer Closed.");
			}
		}
	}
}