package com.training.message;

import java.time.ZonedDateTime;

import lombok.Getter;

@Getter
public class Message<K, T> {

	public enum Type {
		CREATE, UPDATE, DELETE
	}
	
	private Type messageType;
	private K key;
	private T data;
	private ZonedDateTime messageCreatedAt;
	
	public Message() {
	}

	public Message(Type messageType, K key, T data) {
		this.messageType = messageType;
		this.key = key;
		this.data = data;
		this.messageCreatedAt = ZonedDateTime.now();
	}
	
}
