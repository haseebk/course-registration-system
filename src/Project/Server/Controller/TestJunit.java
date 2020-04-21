package Project.Server.Controller;
import org.junit.Test;
import Project.Server.Model.Backend;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
/**
 * The purpose of this class is to run the server for the purpose of a JUnit test
 * 
 * @author Muhammad Tariq, Haseeb Khan
 * @version 1.0
 * @since April 19, 2020
 */
public class TestJunit {
	Backend backend = new Backend();
	
	ServerCommController myServer = new ServerCommController(8089);
	
   @Test
   public void testServerCommController() throws IOException {
	  myServer.establishConnection(backend);
      String str= "[SERVER] Successfully connected to client!";
      assertEquals(myServer.getTestString(),str);
   }
}
