package com.training.service.cache;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CacheEventObserver implements CacheEventListener<Object, Object> {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void onEvent(CacheEvent<? extends Object, ? extends Object> event) {
		logger.info("Some activity in the cache {} for element with key {}. Old value = {}, New Value = {}",
			event.getType(), event.getKey(), event.getOldValue(), event.getNewValue());
	}
}
