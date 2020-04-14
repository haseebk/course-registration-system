package Project.Client.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class CommController {
    private PrintWriter socketOut;
	private Socket aSocket;
	private BufferedReader stdIn;
	private BufferedReader socketIn;

	public CommController(String serverName, int portNumber) {
		try {
			aSocket = new Socket(serverName, portNumber);
			stdIn = new BufferedReader(new InputStreamReader(System.in));
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}

	public void communicate(String message)  {
		socketOut.println(message);
	}

	public String reciveRawInput() {
		try {
			return socketIn.readLine().replace('_', '\n');
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(1);
		return "error!";
	}

	public int recieveIntegerInput() {
		try {
			return Integer.parseInt(socketIn.readLine().trim());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(1);
		return -1;
	}

	public ArrayList<String> getInput() {
		while (true) {
			try {
				String input = socketIn.readLine().trim();
				ArrayList<String> data = new ArrayList<String>();
				String[] text = input.split("****");
				for (int i=0; i < text.length; i++) {
					data.add(text[i]);
				}
				return data;
			} catch (IOException e) {
				System.err.println("Error occured in reciving or processing input");
				e.printStackTrace();
				System.err.println("");
			}
		}
	}

}