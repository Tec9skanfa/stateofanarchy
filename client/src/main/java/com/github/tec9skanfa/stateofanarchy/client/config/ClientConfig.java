package com.github.tec9skanfa.stateofanarchy.client.config;

import com.github.tec9skanfa.stateofanarchy.data.StaticData;

import lombok.Getter;
import lombok.Setter;

public class ClientConfig {

	@Getter
	@Setter
	private boolean debug;

	@Getter
	@Setter
	private boolean prepare;
	@Getter
	@Setter
	private String host = "example.com";
	@Getter
	@Setter
	private int port = StaticData.PORT;
}
