package com.intertech.lab3;

import java.io.File;

import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;

public class FileSelector implements MessageSelector {

	public boolean accept(Message<?> arg0) {

		if(arg0.getPayload() instanceof File && ((File) arg0.getPayload()).getName().startsWith("msg")){
			return false;
		}
		return true;
	}

}
