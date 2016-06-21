package com.shika.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCrawler {

	static OkHttpClient client = new OkHttpClient();
	/**
	 * @param args
	 * @throws IOException 
	 */
	// static Set<String> hash = new HashSet<>();
	// static Set<String> temp = new HashSet<>();

	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		
		
		
		
		Thread cnn = new Thread(new ThreadOfCrawler("https://de.wikisource.org/wiki/Unterhaltungen_%C3%BCber_deutsche_Classiker"));
		cnn.start();
		Thread bbc = new Thread(new ThreadOfCrawler("http://www.bbc.co.uk/radio4#blq-content"));
		bbc.start();
		Thread wikipedia = new Thread(new ThreadOfCrawler("http://www.bbc.co.uk/accessibility/best_practice/"));
		wikipedia.start();
	//WebCrawler we = new WebCrawler();
		
	//System.out.println(run("http://en.wikipedia.org/")); http://www.yallakora.com/
		
		
		
		
		//URL url = new URL("http://en.wikipedia.org/");
		/*URL oracle = new URL("http://en.wikipedia.org/");
        BufferedReader in = new BufferedReader(
        new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
       /* HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        InputStream inputStream = conn.getInputStream();
        StringBuilder sb=new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String read;

        while((read=br.readLine()) != null) {
            //System.out.println(read);
            sb.append(read);   
        }
        br.close();
        String content = sb.toString();
        System.out.println(content);*/
		
		
		

	}
	
	
}