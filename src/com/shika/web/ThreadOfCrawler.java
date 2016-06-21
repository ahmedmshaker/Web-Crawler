package com.shika.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.plaf.SliderUI;

import org.apache.commons.validator.routines.UrlValidator;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ThreadOfCrawler implements Runnable {

	ArrayList<String> visited_url = new ArrayList<>();

	public ArrayList<String> toVisitUrl;

	static int row_num = 0;

	String IntialUrl;

	public ThreadOfCrawler(String IntialUrl) {
		this.IntialUrl = IntialUrl;

		toVisitUrl = new ArrayList<>();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		fetchIntialUrl();

		fetchqueueUrl();

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Error" + e.getMessage());
		}

	}

	public void fetchIntialUrl() {

		String page_content = gethtmlContent(IntialUrl);
		visited_url.add(IntialUrl);
		try {
			boolean b = DBConnection.insertUrl(IntialUrl, page_content);
			if (b) {
				System.out.println("saved");
				row_num++;

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(toVisitUrl.size());
		// hash.remove(0);
		// hash.remove(1);
		// hash.remove(2);
	}

	public String gethtmlContent(String url) {
		Document doc = null;
		try {

			Connection connection = Jsoup
					.connect(url)
					.ignoreContentType(true)
					.userAgent(
							"Mozilla/5.0 (Windows; U; Windows NT 6.0; en-US; rv:1.9.1.2) Gecko/20090729 Firefox/3.5.2 (.NET CLR 3.5.30729)")
					.header("Accept-Language", "en").ignoreHttpErrors(true);
			Connection.Response res = connection.execute();
			if (res.statusCode() == 200) {
				doc = connection.timeout(50000).get();
				Elements anchors = doc.select("a");
				for (Element element : anchors) {
					if (row_num < 3000) {
						UrlValidator uv = new UrlValidator();
						if (uv.isValid(element.attr("abs:href").trim())) {

							if (!toVisitUrl.contains(element.attr("abs:href")
									.trim())
									&& !element.attr("abs:href").trim()
											.equals(IntialUrl)
									&& isVisited(element.attr("abs:href")
											.trim())) {
								toVisitUrl.add(element.attr("abs:href").trim());
							}
						}

					} else {
						System.out.println("thats it xD");
						System.out.println("stop");
						System.exit(0);
					}
					// System.out.println(element.attr("href"));
				}

				return doc.html();

			}
			// System.out.println(doc.html());

			// System.out.println(newsHeadlines.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block

			System.out.println("Error here ->" + e.getMessage());
			// e.printStackTrace();
		}
		return "";

	}

	public boolean isVisited(String url) {

		if (!visited_url.contains(url)) {
			return true;
		} else
			return false;
	}

	public void fetchqueueUrl() {
		for (int i = 0; i < toVisitUrl.size(); i++) {
			// System.out.println(string);

			String url = (String) toVisitUrl.toArray()[i];
			if (!url.isEmpty()) {
				System.out.println("////////////// " + url);
				String htmlcontent = gethtmlContent(url);
				visited_url.add(url);
				try {
					if (!htmlcontent.equals("")) {

						boolean b = DBConnection.insertUrl(url, htmlcontent);
						row_num++;
						if (b) {
							System.out.println("saved");

						}
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
					System.out.println("Error here ->" + e.getMessage());
				}

			}

		}

	}
}
