package com.wsexample.sib;

import java.util.Random;

import javax.jws.HandlerChain;
import javax.jws.WebService;

import com.wsexample.sei.RandService;

//Service Implementation Bean
@WebService(endpointInterface="com.wsexample.sei.RandService")
@HandlerChain (file = "serviceHandler.xml")
public class RandImpl implements RandService {

	private static final int maxRands = 16;
	
	@Override
	public int next1() {
		return new Random().nextInt();
	}

	@Override
	public int[] nextN(int n) {
		final int k = (n > maxRands) ? maxRands : Math.abs(n);
		int[ ] rands = new int[k];
		Random r = new Random();
		for (int i = 0; i < k; i++) rands[i] = r.nextInt();
		return rands;
	}

}
