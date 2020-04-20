package Project.Server.Controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Project.Server.Model.Backend;


public class ServerCommController {
	private Socket theSocket;
	private ServerSocket serverSocket;
	private ExecutorService pool;

	public ServerCommController(int port) throws IOException {	
		serverSocket = new ServerSocket(port);
		pool = Executors.newCachedThreadPool();
	}
	public void importDatabase(Backend backend) {
		MySQLJDBC importer = new MySQLJDBC();
		importer.initializeConnection();
		importer.obtainBackendData(backend);
		importer.closeConnection();
	}
	public void establishConnection(Backend backend) throws IOException {
		while(true) {
			importDatabase(backend);

			System.out.println("[SERVER] Waiting for client connection...");
			theSocket = serverSocket.accept();
			System.out.println("[SERVER] Successfully connected to client!");

			ClientHandler clientThread = new ClientHandler(theSocket, backend);
			pool.execute(clientThread);
		}
	}
		
	public static void main(String[] args) throws IOException {
		Backend backend = new Backend();
		ServerCommController myServer = new ServerCommController(8089);
		myServer.establishConnection(backend);
	}
}