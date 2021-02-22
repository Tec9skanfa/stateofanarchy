package com.github.tec9skanfa.stateofanarchy.client.network.handler;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;

public class ClientsHandler extends IoHandlerAdapter {
	Logger logger = LoggerFactory.getLogger(getClass());
	@Getter
	private NioSocketConnector nioSocketConnector = new NioSocketConnector();

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {

		logger.info("{}", message);
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		logger.info("session {} [{}] closed ", session.getId(), session.getRemoteAddress());
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		session.closeNow();
	}
}
