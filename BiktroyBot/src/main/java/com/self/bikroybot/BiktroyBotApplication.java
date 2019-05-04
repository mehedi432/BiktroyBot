package com.self.bikroybot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.self.bikroybot.service.BikroBotCategories;
import com.self.bikroybot.service.BikroyBotApi;
import com.self.bikroybot.service.BikroyBott;
import com.self.bikroybot.service.BikroyScraper;

@SpringBootApplication
public class BiktroyBotApplication {
	
	private static BikroyBotApi bot;
	
	public static void main(String[] args) {
		SpringApplication.run(BiktroyBotApplication.class, args);
		bot = new BikroyScraper();
		bot.scrapeProduct();
	}
}
