package br.com.ws;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

/**
 * Servlet implementation class WsClientServlet
 * <p>
 * https://github.com/TooTallNate/Java-WebSocket
 * <p>
 * http://localhost:8080/websocket/wsclient
 * <p>
 * http://www.websocket.org/echo.html
 * <p>
 * http://localhost:8080/websocket/index.html
 */
@WebServlet(urlPatterns = "/wsclient")
public class WsClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WsClientServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			
			WebSocketClient wsc = new WebSocketClient(new URI("ws://localhost:8080/websocket/echo/wallace")) {
				
				@Override
				public void onOpen(ServerHandshake handshake) {
					System.out.println("Abrindo Conexao..."  + "ws://localhost:8080/websocket/echo/wallace");
				}
				
				@Override
				public void onMessage(String message) {
					System.out.println(message);
				}
				
				@Override
				public void onError(Exception msg) {
					System.out.println("Error: " + msg.getMessage());
				}
				
				@Override
				public void onClose(int code, String reason, boolean remote) {
					System.out.println("Fechando Conexao...");
					System.out.println(code);
					System.out.println(reason);
				}
			};
			
			wsc.connect();
			
			wsc.send("Eduardo Wallace da Silva");
		
		} catch(Exception e) {
			e.printStackTrace();
		}
			
		
	}

}
