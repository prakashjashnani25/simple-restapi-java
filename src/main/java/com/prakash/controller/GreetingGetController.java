package com.prakash.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

@SuppressWarnings("restriction")
/*
 * Controller For all Request /hello
 */
public class GreetingGetController implements HttpHandler {

	private static final int REQUEST_METHOD_NOT_SUPPORTED = 405;

	private static final int SUCCESS_RESPONSE_CODE = 200;

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		if ("GET".equals(exchange.getRequestMethod())) {
			String responseTest = getGreeting(getQueryMap(exchange.getRequestURI().getRawQuery()));
			exchange.sendResponseHeaders(SUCCESS_RESPONSE_CODE, responseTest.getBytes().length);
			OutputStream os = exchange.getResponseBody();
			os.write(responseTest.getBytes());
			os.flush();
			os.close();
			exchange.close();

		} else {
			exchange.sendResponseHeaders(REQUEST_METHOD_NOT_SUPPORTED, -1);
		}

	}

	public String getGreeting(Map<String, List<String>> queryMap) throws IOException {

		String user = queryMap.get("name") == null ? "Annonymous!" : queryMap.get("name").get(0);

		final String responseTest = "Welcome " + user + " To First Rest Api With out Any frameowk!!!";
		return responseTest;

	}

	private Map<String, List<String>> getQueryMap(String query) {
		if (query == null || query.trim().equals(""))
			return Collections.emptyMap();

		Map<String, List<String>> queryStringMap = Pattern.compile("&").splitAsStream(query)
				.map(s -> Arrays.copyOf(s.split("="), 2))
				.collect(Collectors.groupingBy(s -> s[0], Collectors.mapping(s -> s[1], Collectors.toList())));

		/*
		 * Map<String, List<String[]>> collect =
		 * Pattern.compile("&").splitAsStream(query) .map(s ->
		 * Arrays.copyOf(s.split("="), 2)) .collect(Collectors.groupingBy(s->s[0]));
		 * 
		 * collect.forEach((k,v)->{ System.out.print("\n\n\n KEY "+k); v.forEach(arr->{
		 * System.out.print("Values - Length "+arr.length+" first value "+arr[0]); });
		 * });
		 * 
		 * 
		 * queryStringMap.forEach((x,y)->{ System.out.print("\n\n\n Key "+x +" Value ");
		 * y.forEach(s->{ System.out.print(" "+s); }); });
		 */

		return queryStringMap;
	}

}
