package com.github.tec9skanfa.stateofanarchy.client.config;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ServiceConfigurationError;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.tec9skanfa.stateofanarchy.client.Header;

public final class ConfigUtils {
	private ConfigUtils() {
	}

	static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public static void readConfig() throws IOException {
		logger.info("initConfig-start");
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		// CHECK and create

		if (!Header.getCONFIGFILE().getParentFile().exists()) {
			Header.getCONFIGFILE().getParentFile().mkdirs();
		}
		if (!Header.getCONFIGFILE().exists()) {
			logger.info("File {} is created:{}", Header.getCONFIGFILE().getAbsolutePath(),
					Header.getCONFIGFILE().createNewFile());

			mapper.writeValue(Header.getCONFIGFILE(), Header.getConfig());
			throw new ServiceConfigurationError("please edit " + Header.getCONFIGFILE().getAbsolutePath());
		}
		// READ
		Header.setConfig(mapper.readValue(Header.getCONFIGFILE(), ClientConfig.class));
		if (!Header.getConfig().isPrepare()) {
			throw new ServiceConfigurationError("please edit " + Header.getCONFIGFILE().getAbsolutePath());
		}
		logger.info("initConfig-end");

	}
}
