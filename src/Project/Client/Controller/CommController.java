package Project.Client.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

/**
 * The purpose of this class is to communicate with the Server. The attributes and methods help with this purpose and process the data.
 * 
 * @author Muhammad Tariq, Haseeb Khan
 * @version 1.0
 * @since April 20, 2020
 */
public class CommController {

	/**
	 * output data stream socket
	 */
	private PrintWriter socketOut;
	
	/**
	 * client socket
	 */
	private Socket aSocket;

	/**
	 * input data stream socket
	 */
	private BufferedReader socketIn;

	/**
	 * input stream
	 */
	private InputStream oInputStream;

	/**
	 * object input stream
	 */
	private ObjectInputStream objectInputStream;

	/**
	 * This is this constructor. It creates data streams for all the sockets.
	 * @param serverName name of the server
	 * @param portNumber name of the port number
	 */
	public CommController(String serverName, int portNumber) {
		try {
			aSocket = new Socket(serverName, portNumber);
			socketIn = new BufferedReader(new InputStreamReader(aSocket.getInputStream()));
			socketOut = new PrintWriter((aSocket.getOutputStream()), true);
			oInputStream = aSocket.getInputStream();
	        setObjectInputStream(new ObjectInputStream(getoInputStream()));

		} catch (IOException e) {
			System.err.println(e.getStackTrace());
		}
	}

	/**
	 * the purpose of this method is to communicate with the server using string messages.
	 * @param message
	 */
	public void communicate(String message)  {
		socketOut.println(message);
	}
	
	/**
	 * the purpose of this method is to receive raw input.
	 * @return
	 */
	public String recieveRawInput() {
		try {
			return socketIn.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.exit(1);
		return "error!";
	}

	/**
	 * the purpose of this method is to receive integer inputs by parsing the strings.
	 * @return
	 */
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

	/**
	 * the purpose of this method is to recieve data from the server as strings.
	 * @return
	 */
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
				System.err.println("Error: Unable to process input!");
				e.printStackTrace();
				System.err.println("");
			}
		}
	}

	/**
	 * getter
	 * @return
	 */
	public InputStream getoInputStream() {
		return oInputStream;
	}

	/**
	 * setter
	 * @param oInputStream
	 */
	public void setoInputStream(InputStream oInputStream) {
		this.oInputStream = oInputStream;
	}

	/**
	 * getter
	 * @return
	 */
	public ObjectInputStream getObjectInputStream() {
		return objectInputStream;
	}

	/**
	 * setter
	 * @param objectInputStream
	 */
	public void setObjectInputStream(ObjectInputStream objectInputStream) {
		this.objectInputStream = objectInputStream;
	}

}