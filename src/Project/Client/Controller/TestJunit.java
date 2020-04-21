package Project.Client.Controller;
import org.junit.Test;

import Project.Client.View.FrontEnd;
import Project.Server.Model.Backend;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
public class TestJunit {
	
   @Test
   public void testChooseSection() throws IOException {
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
	  
	  
	  
	  
      String str= "Successfully added course!";
      while(true) {
          if(str.equals(controller.getFrontEnd().getCourseCatalogPanel().getChoiceWindow().getSuccess()))
              assertEquals(controller.getFrontEnd().getCourseCatalogPanel().getChoiceWindow().getSuccess(),str);
      }
   }
}
