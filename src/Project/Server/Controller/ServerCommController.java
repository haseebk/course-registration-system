package Project.Server.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import Project.Server.Model.Backend;

public class ServerCommController {
	private Socket theSocket;
	private ServerSocket serverSocket;
	private PrintWriter socketOut;
	private BufferedReader socketIn;


	public ServerCommController(int port) throws IOException {
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void establishConnection(Backend test) {
		System.out.println("Awaiting connection with client.");
		try {
			theSocket = serverSocket.accept();
			System.out.println("Successfully connected!");
			socketIn = new BufferedReader (new InputStreamReader (theSocket.getInputStream()));
			socketOut = new PrintWriter (theSocket.getOutputStream(), true);	
			OutputStream objectSocketOut = theSocket.getOutputStream();
			ObjectOutputStream  objectOutputStream  = new ObjectOutputStream(objectSocketOut);
			objectOutputStream.writeObject(test);
		} catch (IOException e) {
			e.getStackTrace();
		}
	}
		
	public void communicateWithClient(String message) {
		socketOut.println(message.replace('\n', '_'));
	}

	public ArrayList<String> getInput() {
		while (true) {
			try {
				String input = socketIn.readLine();
				ArrayList<String> data = new ArrayList<String>();
				String[] text = input.split("=");
				for (int i = 0; i < text.length; i++) {
					data.add(text[i]);
				}
				System.out.println(data);
				return data;
			} catch (IOException e) {
				System.err.println("Error occured in reciving or processing input");
				System.err.println("");
				System.exit(1);
			}
		}
	}
}