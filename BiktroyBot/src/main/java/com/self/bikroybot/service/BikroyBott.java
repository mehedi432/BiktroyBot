package com.self.bikroybot.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.self.bikroybot.model.Product;
//TODO: understand XPath
//TODO: how to select a list of elements 
@Service
public class BikroyBott implements BikroyBotApi {
	private static final String URL = "https://bikroy.com/";
	private static final String CAT_EL_PATH = "//div[@class='home-categories']/div/div/h4";
	private static final String POST_EL_PATH = "//div[@class='home-categories']/div/div/p/span";
	private static WebClient client = getClient();
	
	@Override
	public void scrapeProduct() {		
		System.out.println("Total Category found: " + getProductAmount());
	}

	private int getProductAmount() {
		try {
			HtmlPage page = client.getPage(URL);
			// getByXPath() -> provide a list of selected XPath objects
			List<HtmlElement> catList = page.getByXPath(CAT_EL_PATH);	
			List<HtmlElement> postList = page.getByXPath(POST_EL_PATH);
			List<Product> productList = new ArrayList<>();
			
			int i=0;
			for (HtmlElement cat : catList) {
				Product product = new Product();
				product.setName(cat.getTextContent());
				String s = postList.get(i).getTextContent();
				//if(s.contains(",")) product.setPostAmount(getAmount(s));
				//else product.setPostAmount(Integer.parseInt(s));
				productList.add(product);
				i++;
			}
		return productList.size();
		} catch (FailingHttpStatusCodeException | IOException e) {
			System.out.println("HTTP ERROR !"+e.getMessage());
		}
		return 0;
	}

	// Total problem - don't have any idea
	private int getAmount(String text) {
		List<String> chs = Arrays.asList(text.trim().split(","));
		String s="";
		for (String c : chs) {
			s+=c;
		}
		return Integer.parseInt(s);
	}

	// Settings for webClient
	private static WebClient getClient() {
		if(client==null) {
			client=new WebClient(BrowserVersion.FIREFOX_52);
			client.setJavaScriptTimeout(10*1000);
			client.getOptions().setJavaScriptEnabled(true);
			client.setAjaxController(new NicelyResynchronizingAjaxController());
			client.getOptions().setCssEnabled(true);
		}
		return client;
	}

	@Override
	public void saveProduct() {
		// TODO Auto-generated method stub

	}

}
