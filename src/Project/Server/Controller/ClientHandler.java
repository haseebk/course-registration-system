package Project.Server.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import Project.Server.Model.Backend;

public class ClientHandler implements Runnable {
    private Socket theSocket;
    private PrintWriter socketOut;
    private BufferedReader socketIn;

    public ClientHandler(Socket clientSocket, Backend backend) throws IOException {
        this.theSocket = clientSocket;
        socketIn = new BufferedReader (new InputStreamReader (theSocket.getInputStream()));
		socketOut = new PrintWriter (theSocket.getOutputStream(), true);	
		OutputStream objectSocketOut = theSocket.getOutputStream();
		ObjectOutputStream  objectOutputStream  = new ObjectOutputStream(objectSocketOut);
		objectOutputStream.writeObject(backend);
    }

    @Override
    public void run() {
        ArrayList<String> data = getInput();

        while (true) {
			switch (Integer.parseInt(data.get(0))) {
			case 1:
				// Login
				communicateWithClient("1");
				break;
			case 200:
				// View User Courses
				communicateWithClient("2");
				break;
			case 3:
				// View Course Catalog
				communicateWithClient("3");
				break;
			case 4:
				// Any back button
				communicateWithClient("4");
				break;
			case 5:
				communicateWithClient("5");
				break;
			}
		}
    }

    public ArrayList<String> getInput() {
		while (true) {
			try {
				String input = socketIn.readLine();
				ArrayList<String> data = new ArrayList<String>();
				data.clear();
				data.add(input);
				return data;
			} catch (IOException e) {
				System.err.println("Error: Unable to process input!");
				System.exit(1);
			}
		}
    }
    
    public void communicateWithClient(String message) {
		socketOut.println(message);
	}

}