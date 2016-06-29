package com.intertech.lab8;

import java.util.concurrent.Future;

public interface PigLatinService {

	//Use a Future to make the gateway asynchronous
	Future<String> translate(String english);
}
