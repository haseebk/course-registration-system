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

/**
 * The purpose of this class is to handle multiple clients through
 * multithreading. The class is runnable and performs the run method when
 * executed.
 * 
 * @author Muhammad Tariq, Haseeb Khan
 * @version 1.0
 * @since April 19, 2020
 */
public class ClientHandler implements Runnable {
	/**
	 * The socket for client communication
	 */
	private Socket theSocket;

	/**
	 * The socket for sending data to client
	 */
	private PrintWriter socketOut;

	/**
	 * The socket for receiving data from client
	 */
	private BufferedReader socketIn;
	
	private Backend backend;

	/**
	 * Constructor for client Handler that connects to backend processor and
	 * connects to a client
	 * 
	 * @param clientSocket
	 * @param backend
	 * @throws IOException
	 */
	public ClientHandler(Socket clientSocket, Backend backend) throws IOException {
		this.theSocket = clientSocket;
		this.backend = backend;
		readFromDatabase();

		socketIn = new BufferedReader(new InputStreamReader(theSocket.getInputStream()));
		socketOut = new PrintWriter(theSocket.getOutputStream(), true);
		OutputStream objectSocketOut = theSocket.getOutputStream();
		ObjectOutputStream objectOutputStream = new ObjectOutputStream(objectSocketOut);
		objectOutputStream.writeObject(backend);
	}
	void readFromDatabase() {
		MySQLJDBC reader = new MySQLJDBC();
		reader.initializeConnection();
		reader.obtainBackendData(backend);
		reader.closeConnection();
	}
	/**
	 * Run method that is executed when a thread is created. Receives input from client for processing
	 */
    @Override
    public void run() {
    	startServer();
    }

	public void startServer() {
		operateServer();
	}

	public void operateServer() {

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

	

	/**
	 * Receives data as a string from client and adds it to an array of String.
	 * 
	 * @return
	 */
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

	/**
	 * The purpose of this method is to communicate with the Client via string
	 * messages to further process them.
	 * 
	 * @param message
	 */
	public void communicateWithClient(String message) {
		socketOut.println(message);
	}

}