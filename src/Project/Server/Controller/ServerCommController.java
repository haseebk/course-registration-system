package Project.Server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Project.Server.Model.Backend;

/**
 * The purpose of this class is to communicate with the client
 * 
 * 
 * @author Muhammad Tariq, Haseeb Khan
 * @version 1.0
 * @since April 19, 2020
 */
public class ServerCommController {
	private Socket theSocket;
	private ServerSocket serverSocket;
	private ExecutorService pool;
	private String testString;

	/**
	 * constructor that sets the port for the server socket
	 * and created a cached thread pool
	 * 
	 * @param port
	 */
	public ServerCommController(int port){	
		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
		pool = Executors.newCachedThreadPool();
		
	}

	/**
	 * establish connection
	 * @param backend
	 * @throws IOException
	 */
	public void establishConnection(Backend backend) throws IOException {
		while(true) {
			System.out.println("[SERVER] Waiting for client connection...");
			theSocket = serverSocket.accept();
			setTestString("[SERVER] Successfully connected to client!");
			System.out.println("[SERVER] Successfully connected to client!");
			ClientHandler clientThread = new ClientHandler(theSocket, backend);

			pool.execute(clientThread);
//			break; UNCOMMENT FOR JUNIT TEST
		}
	}
	public String getTestString() {
		return testString;
	}


	public void setTestString(String testString) {
		this.testString = testString;
	}
		
	public static void main(String[] args) throws IOException {
		Backend backend = new Backend();
		
		ServerCommController myServer = new ServerCommController(8089);
		myServer.establishConnection(backend);
	}
}