package Project.Server.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerCommController {
	private Socket aSocket;
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

	public void runServer() {
		try {
			ServerCommController myServer = new ServerCommController(8099);
			myServer.aSocket = myServer.serverSocket.accept();
			System.out.println("Server is now running.");
			myServer.socketIn = new BufferedReader(new InputStreamReader(myServer.aSocket.getInputStream()));
			myServer.socketOut = new PrintWriter((myServer.aSocket.getOutputStream()), true);
			myServer.runServer();

			myServer.socketIn.close();
			myServer.socketOut.close();
		} catch (IOException e) {
			e.getStackTrace();
		}
	}

}