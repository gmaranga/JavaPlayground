package com.javawellgrounded.nio2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketOption;
import java.net.StandardSocketOptions;
import java.nio.channels.NetworkChannel;
import java.nio.channels.spi.SelectorProvider;
import java.util.Set;

public class PlayingWithSockets {

	/*
	 * java.nio.channels package Defines channels, which represent connections
	 * to entities that are capable of performing I/O operations, such as files
	 * and sockets; defines selectors, for multiplexed, non-blocking I/O
	 * operations. java.net.Socket class This class implements client sockets
	 * (also called just “sockets”).A socket is an endpoint for communication
	 * between two machines.
	 */

	public static void main(String[] args) {
		networkChannelOptions();
	}

	public static void networkChannelOptions() {

		/*
		 * The new java.nio.channels.NetworkChannel interface represents a
		 * mapping of a channel to a network socket.
		 */

		SelectorProvider provider = SelectorProvider.provider();

		try (NetworkChannel socketChannel = provider.openSocketChannel()) {

			SocketAddress address = new InetSocketAddress(3080);
			socketChannel.bind(address);

			Set<SocketOption<?>> socketOptions = socketChannel.supportedOptions();

			System.out.println(socketOptions);

			socketChannel.setOption(StandardSocketOptions.IP_TOS, 3);

			Boolean keepAlive = socketChannel.getOption(StandardSocketOptions.SO_KEEPALIVE);

			System.out.println("keepAlive: " + keepAlive);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
