package com.github.tec9skanfa.stateofanarchy.data;

import java.io.File;

import lombok.Getter;

public final class StaticData {
	private StaticData() {
	}

	@Getter
	private static final File ROOTDIR = new File("stateofanarchy");

	@Getter
	public static final int PORT = 24565;
}
