package com.github.tec9skanfa.stateofanarchy.server.init;

import java.io.IOException;

import com.github.tec9skanfa.stateofanarchy.server.Header;

public class ServerInit {

	public static void main(String[] args) throws IOException {
		Header.getServer().init();
		Header.getServer().start();
	}
}
