import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.prakash.controller.GreetingGetController;
import com.sun.net.httpserver.HttpServer;
@SuppressWarnings("restriction")
public class Application {

	private static final int SERVER_PORT=8000;
	private static final String CONTEXT_ROOT="/api/";
	public static void main(String[] args) throws IOException {
		HttpServer httpServer=HttpServer.create(new InetSocketAddress(SERVER_PORT),0);
		
			
		httpServer.createContext(CONTEXT_ROOT,(exchange -> {
			final int INVALID_REQUEST_MTHOD=405;
			if("GET".equals(exchange.getRequestMethod())){
					new GreetingGetController().getGreeting(exchange);
			}else {
				exchange.sendResponseHeaders(INVALID_REQUEST_MTHOD, -1);
			}
			
		}));
		//Default Executor
		httpServer.setExecutor(null);
		httpServer.start();
	}

}
