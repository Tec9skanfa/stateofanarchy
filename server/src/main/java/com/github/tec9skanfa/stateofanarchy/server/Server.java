package com.github.tec9skanfa.stateofanarchy.server;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.InetSocketAddress;

import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.tec9skanfa.stateofanarchy.server.config.ConfigUtils;

import lombok.Getter;
import lombok.Setter;

public class Server extends Thread {

	static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
	@Getter
	@Setter
	private Thread starterClientsThread;

	public void init() throws IOException {
		ConfigUtils.readConfig();
		initGame();
		initNetwork();
	}

	private void initNetwork() {
		ObjectSerializationCodecFactory oscf = new ObjectSerializationCodecFactory();
		oscf.setDecoderMaxObjectSize(Integer.MAX_VALUE);

		Header.getCLIENTSHANDLER().getAcceptor().setHandler(Header.getCLIENTSHANDLER());
		Header.getCLIENTSHANDLER().getAcceptor().getFilterChain().addLast("codec", new ProtocolCodecFilter(oscf));
		Header.getCLIENTSHANDLER().getAcceptor().getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
	}

	@Override
	public void run() {
		startGame();
		try {
			startNetwork();
		} catch (Exception e) {
			logger.error("error ", e);
		}
	}

	// GAME
	private void initGame() {
	}

	private void startGame() {
	}
	// GAME

	// NETWORK
	private void startNetwork() throws InterruptedException {
		logger.info("startNetwork-start");
		startClientThread();
		starterClientsThread.join();
		logger.info("startNetwork-end");
	}

	private void startClientThread() {
		this.starterClientsThread = new Thread(() -> {
			boolean errorClients = false;
			do {
				try {
					int port = Header.getConfig().getPort();
					Header.getCLIENTSHANDLER().getAcceptor().bind(new InetSocketAddress(port));
					logger.info("Server:startNetwork:STARTED");
					errorClients = false;
				} catch (Exception e) {
					logger.info("startNetwork-error: [{}] by [{}]", e.getMessage(), e.getCause().getMessage());
					errorClients = true;
					try {
						Thread.sleep(5000);
					} catch (Exception e2) {
						// IGNORE
					}
				}
			} while (errorClients);

		}, "Starter:ClientListener");
		starterClientsThread.start();
	}
	// NETWORK
}
