package com.github.tec9skanfa.stateofanarchy.client;

import java.io.File;

import org.apache.mina.core.session.IoSession;

import com.github.tec9skanfa.stateofanarchy.client.config.ClientConfig;
import com.github.tec9skanfa.stateofanarchy.client.network.handler.ClientsHandler;
import com.github.tec9skanfa.stateofanarchy.data.StaticData;

import lombok.Getter;
import lombok.Setter;

public final class Header {
	private Header() {
	}

	@Getter
	private static Client client = new Client();
	@Getter
	@Setter
	private static ClientConfig config = new ClientConfig();

	@Getter
	private static final File CLIENTDIR = new File(StaticData.getROOTDIR(), "client");
	@Getter
	private static final File CONFIGFILE = new File(CLIENTDIR, "clientconfig.yml");

	// network
	@Getter
	private static ClientsHandler handler = new ClientsHandler();

	@Getter
	@Setter
	private static IoSession session;
}
