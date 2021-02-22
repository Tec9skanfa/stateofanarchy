package com.github.tec9skanfa.stateofanarchy.client;

import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.tec9skanfa.stateofanarchy.client.config.ConfigUtils;
import com.github.tec9skanfa.stateofanarchy.network.ReadyPacket;

public class Client extends Thread {
	static Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	public void init() throws IOException {
		ConfigUtils.readConfig();
		initNetwork();
	}

	public void initNetwork() {
		ObjectSerializationCodecFactory oscf = new ObjectSerializationCodecFactory();
		oscf.setDecoderMaxObjectSize(Integer.MAX_VALUE);
		Header.getHandler().getNioSocketConnector().setHandler(Header.getHandler());
		Header.getHandler().getNioSocketConnector().getFilterChain().addLast("codec", new ProtocolCodecFilter(oscf));
		Header.getHandler().getNioSocketConnector().getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
	}

	@Override
	public void run() {
		try {
			reconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void reconnect() throws InterruptedException {
		if (Header.getConfig().isDebug()) {
			logger.warn("reconnect");
		}

		for (;;) {
			try {
				connect();
				break;
			} catch (org.apache.mina.core.RuntimeIoException e) {
				e.getClass();// IGNORE
			} catch (Exception e) {
				e.printStackTrace();
				if (Header.getConfig().isDebug()) {
					logger.error("Failed to connect.[{}] by {}", e.getMessage(), e.getCause().getMessage());
				}
				Thread.sleep(5000);
			}
		}
	}

	private void connect() {
		if (Header.getConfig().isDebug()) {
			logger.warn("connect");
		}
		String host = Header.getConfig().getHost();
		int port = Header.getConfig().getPort();
		ConnectFuture future = Header.getHandler().getNioSocketConnector().connect(new InetSocketAddress(host, port));
		future.awaitUninterruptibly();
		Header.setSession(future.getSession());

		ReadyPacket ready = new ReadyPacket();
		Header.getSession().write(ready);
	}
}
