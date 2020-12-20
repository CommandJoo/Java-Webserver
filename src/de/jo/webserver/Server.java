package de.jo.webserver;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {
	
	private HttpServer server;
	private ArrayList<String> getContexts;
	
	
	public Server(int port) {
		getContexts = new ArrayList<>();
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
			System.out.println("Server start succesfull!");
			server.setExecutor(null);
		} catch (IOException e) {
			System.err.println("An Error Occurred whilest creating the server!");
		}
	}
	
	public void start() {
		server.start();
	}
	
	public void stop() {
		server.stop(1);
		System.exit(1);
	}
	
	public void clearHTML() {
		try {
			for(String s : getContexts) {
				server.removeContext(s);
			}
		} catch (Exception e) {
		}
	}
	
	public void addSubElement(String directory, String htmlElement) {
		try {
			server.removeContext("/"+directory);
			getContexts.remove("/"+directory);
		} catch (Exception e) {
		}
		server.createContext("/"+directory, new HttpHandler() {
			
			@Override
			public void handle(HttpExchange he) throws IOException {
				String response = htmlElement;
		        he.sendResponseHeaders(200, response.length());
		        OutputStream os = he.getResponseBody();
		        os.write(response.getBytes());
		        os.close();
			}
		});
		getContexts.add("/"+directory);
	}
	
}
