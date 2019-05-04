package com.self.bikroybot.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BikroyScraper implements BikroyBotApi {
	
	private static final String URL = "https://bikroy.com";
	
	private WebClient client = getClient();

	@Override
	public void scrapeProduct() {
		try {
			getCategories();
		} catch (FailingHttpStatusCodeException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void getCategories() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		final String catPath = "//div[@class='home-categories']";
		
		HtmlPage categories = client.getPage(URL);
		List<HtmlElement> categoryList = categories.getByXPath(catPath);
		
		categoryList.forEach(item -> {
			System.out.println(item);
		});
		//System.out.println(categories.asXml());
	}

	private WebClient getClient() {
		if(client == null) {
			client = new WebClient(BrowserVersion.FIREFOX_52);
			client.setJavaScriptTimeout(10 * 1000);
			client.setAjaxController(new NicelyResynchronizingAjaxController());
			client.getOptions().setThrowExceptionOnFailingStatusCode(true);
		}
		return null;
	}

	@Override
	public void saveProduct() {
		// TODO Auto-generated method stub
		
	}

}
