package com.smoothstack.counterAPI;

import com.smoothstack.counterAPI.entity.Counter;

public class CounterSingleton extends Counter {

	private static CounterSingleton instance;	
	
	private CounterSingleton() {
		
	}
	
	public static CounterSingleton getInstance() { 
		return instance == null ? new CounterSingleton() : instance;
	}
	
	public void setInstance(Counter counter) { instance = (CounterSingleton) counter; }
	
}
