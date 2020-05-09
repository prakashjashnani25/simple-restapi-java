import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.prakash.controller.GreetingGetController;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class Application {

	private static final int SERVER_PORT = 8000;
	private static final String CONTEXT_ROOT = "/api/";
	private static  final String GREETING_CONTROLLER="/api/hello";
	public static void main(String[] args) throws IOException {
		HttpServer httpServer = HttpServer.create(new InetSocketAddress(SERVER_PORT), 0);

		httpServer.createContext(CONTEXT_ROOT, (exchange -> {
			System.out.println("Deefault Cotext Root");
			final String responseMessage = createResponseMessage();
			exchange.sendResponseHeaders(200, responseMessage.getBytes().length);
			OutputStream os = exchange.getResponseBody();
			os.write(responseMessage.getBytes());
			os.flush();
			os.close();
			exchange.close();

		}));
		httpServer.createContext(GREETING_CONTROLLER,new GreetingGetController());
		
		// Default Executor
		httpServer.setExecutor(null);
		httpServer.start();
	}

	private static String createResponseMessage() {
		String response = "Welcome !!! ";
		response += "Available Options we have in our API are - \\n 1. \\api\\Hello?name=<name>";
		return response;
	}

}
