package org.kj6682.library.controller;

import java.util.Set;

public class StoreReply<T> {
	private final String code;
	private final String reason;
	private final Set<T> payload;
	
	public StoreReply(String code, String reason, Set<T> payload) {
		super();
		this.code = code;
		this.reason = reason;
		this.payload = payload;
	}
	
	public String getCode() {
		return code;
	}
	
	public String getReason() {
		return reason;
	}
	
	public Set<T> getItems() {
		return payload;
	}
	
}
