package com.self.bikroybot.service;

import java.io.IOException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BikroBotCategories implements BikroyBotApi {
	
	private static final String URL = "https://bikroy.com/";
	private static final String CAT_EL_PATH = "//div[@class='home-categories']/div/div/h4";
	private static final String LINK_EL_PATH = "//div[@class='home-categories']/div/div/p";
	private static final String catPath = "//div/[@class='ui-accordion-content']/";
	private static WebClient client = getClient();
	
	
	// Mine
	private String baseUrl = "https://bikroy.com";
	private String path = "//div[@class='home-categories']/div/div/h4/a";

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	@Override
	public void scrapeProduct() {
		System.out.println(getAllLinks());	
	}
	
	private int getAllLinks() {
		try {
			HtmlPage page = client.getPage(URL);
			List<HtmlElement> links = page.getByXPath(path);
			
			for (HtmlElement link : links) {
				if(link.getAttribute("href").equals("https://bikroy.com/bn/jobs")) continue;
				String category = getBaseUrl() + link.getAttribute("href").toString();
				
				HtmlPage categoryPage = client.getPage(category);
				categoryPage.getByXPath();
				System.out.println(categoryPage);
			}
			
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	// Settings for the client
	private static WebClient getClient() {
		if(client == null) {
			client= new WebClient(BrowserVersion.FIREFOX_52);
			client.setJavaScriptTimeout(10*1000);
			client.setAjaxController(new NicelyResynchronizingAjaxController());
			client.getOptions().setThrowExceptionOnScriptError(false);
		}
		return client;
	}

	@Override
	public void saveProduct() {
		// TODO Auto-generated method stub
		
	}

}
