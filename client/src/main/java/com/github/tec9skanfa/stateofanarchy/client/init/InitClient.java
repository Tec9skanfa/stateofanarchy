package com.github.tec9skanfa.stateofanarchy.client.init;

import java.io.IOException;

import com.github.tec9skanfa.stateofanarchy.client.Header;

public class InitClient {

	public static void main(String[] args) throws IOException {
		Header.getClient().init();
		Header.getClient().start();
	}

}
