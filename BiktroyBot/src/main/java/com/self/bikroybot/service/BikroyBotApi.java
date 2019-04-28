package com.self.bikroybot.service;

import org.springframework.stereotype.Component;

@Component
public interface BikroyBotApi {
	public void scrapeProduct();
	public void saveProduct();
}
