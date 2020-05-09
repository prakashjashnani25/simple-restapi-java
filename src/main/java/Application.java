import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;
@SuppressWarnings("restriction")
public class Application {

	private static final int SERVER_PORT=8000;
	private static final String CONTEXT_ROOT="/api/hello";
	public static void main(String[] args) throws IOException {
		HttpServer httpServer=HttpServer.create(new InetSocketAddress(SERVER_PORT),0);
		httpServer.createContext(CONTEXT_ROOT,(exchange -> {
			final String responseTest="Welcome  To First Rest Api With out Any frameowk!!!";
			final int SUCCESS_RESPONSE_CODE=200;
			exchange.sendResponseHeaders(SUCCESS_RESPONSE_CODE, responseTest.getBytes().length);
			OutputStream os  =exchange.getResponseBody();
			os.write(responseTest.getBytes());
			os.flush();
			os.close();
			exchange.close();
		}));
		//Default Executor
		httpServer.setExecutor(null);
		httpServer.start();
	}

}
