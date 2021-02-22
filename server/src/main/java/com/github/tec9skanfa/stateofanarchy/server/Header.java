package com.github.tec9skanfa.stateofanarchy.server;

import java.io.File;

import com.github.tec9skanfa.stateofanarchy.data.StaticData;
import com.github.tec9skanfa.stateofanarchy.server.config.ServerConfig;
import com.github.tec9skanfa.stateofanarchy.server.network.handler.ClientsHandler;

import lombok.Getter;
import lombok.Setter;

public final class Header {
	private Header() {
	}

	@Getter
	private static Server server = new Server();
	@Getter
	@Setter
	private static ServerConfig config = new ServerConfig();

	@Getter
	private static final File SERVERDIR = new File(StaticData.getROOTDIR(), "server");
	@Getter
	private static final File CONFIGFILE = new File(SERVERDIR, "serverconfig.yml");

	@Getter
	private static final ClientsHandler CLIENTSHANDLER = new ClientsHandler();
}
