package com.training.utility;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AddressInfo {

	@Value("${server.port}")
	private int port;
	
	public String getServiceAddress() {
		try {
			return InetAddress.getLocalHost().getHostName() + "/" + InetAddress.getLocalHost().getHostAddress() + ":" + port;
		} catch (UnknownHostException e) {
			return "";
		}
	}
}
