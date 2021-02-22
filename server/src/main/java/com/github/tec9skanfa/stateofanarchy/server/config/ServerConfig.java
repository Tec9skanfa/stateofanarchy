package com.github.tec9skanfa.stateofanarchy.server.config;

import com.github.tec9skanfa.stateofanarchy.data.StaticData;

import lombok.Getter;
import lombok.Setter;

public class ServerConfig {
	@Getter
	@Setter
	private boolean prepare;
	@Getter
	@Setter
	private int port = StaticData.PORT;
}
