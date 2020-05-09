package com.prakash.controller;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

@SuppressWarnings("restriction")
public class GreetingGetController {

	public void getGreeting(HttpExchange exchange) throws IOException {
		final String responseTest="Welcome  To First Rest Api With out Any frameowk!!!";
		final int SUCCESS_RESPONSE_CODE=200;
		exchange.sendResponseHeaders(SUCCESS_RESPONSE_CODE, responseTest.getBytes().length);
		OutputStream os  =exchange.getResponseBody();
		os.write(responseTest.getBytes());
		os.flush();
		os.close();
		exchange.close();	// TODO Auto-generated method stub
		
	}

}
