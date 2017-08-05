package org.tabchanj.aladdin.crawler;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.tabchanj.aladdin.domain.PicUrl;
import org.tabchanj.aladdin.service.impl.PicUrlService;
import org.tabchanj.aladdin.util.common.ResourceUtil;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.crawler.WebCrawler;
import edu.uci.ics.crawler4j.parser.HtmlParseData;
import edu.uci.ics.crawler4j.url.WebURL;

@Component
public class MyWebCrawler extends WebCrawler implements ApplicationContextAware {
	private static ApplicationContext applicationContext;
	private static Logger logger = LoggerFactory.getLogger(MyWebCrawler.class);
	private final static Pattern FILTERS = Pattern.compile(".*(\\.(css|js" + "|mp3|mp3|zip|gz))$");

	/**
	 * This method receives two parameters. The first parameter is the page in
	 * which we have discovered this new url and the second parameter is the new
	 * url. You should implement this function to specify whether the given url
	 * should be crawled or not (based on your crawling logic). In this example,
	 * we are instructing the crawler to ignore urls that have css, js, git, ...
	 * extensions and to only accept urls that start with
	 * "http://www.ics.uci.edu/". In this case, we didn't need the referringPage
	 * parameter to make the decision.
	 */
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		String href = url.getURL().toLowerCase();
		try {
			return !FILTERS.matcher(href).matches()
					&& href.startsWith(ResourceUtil.getString("system", "seedstartswith"));
		} catch (IOException e) {
			logger.info(e.getMessage(), e);
		}
		return false;
	}

	/**
	 * This function is called when a page is fetched and ready to be processed
	 * by your program.
	 */
	@Override
	public void visit(Page page) {
		String url = page.getWebURL().getURL();
		System.out.println("URL: " + url);

		if (page.getParseData() instanceof HtmlParseData) {
			HtmlParseData htmlParseData = (HtmlParseData) page.getParseData();
			String text = htmlParseData.getText();
			String html = htmlParseData.getHtml();
			System.err.println(html);
			Set<WebURL> links = htmlParseData.getOutgoingUrls();
			Map<String, String> meta = htmlParseData.getMetaTags();

			System.out.println("Text length: " + text.length());
			System.out.println("meta: " + meta.toString());
			System.out.println("Html length: " + html.length());
			System.out.println("Number of outgoing links: " + links.size());
			for (WebURL webURL : links) {
				// System.out.println("链接是:" + webURL);
				if (webURL.toString().endsWith("mp4")) {
					System.out.println("视频链接:" + webURL);
					// 存储到表
					PicUrl picUrl = new PicUrl();
					picUrl.setUrl(webURL.toString());
					getBean(PicUrlService.class).save(picUrl);
				}
			}
		}
	}

	public void downloadPic(String url) throws IOException {
		if (url.endsWith("jpg")) {
			URL t_url = new URL(url);
			URLConnection connec = t_url.openConnection();
			InputStream is = connec.getInputStream();
			File file = new File(ResourceUtil.getString("system", "localfileprefix_img"));
			if (!file.exists()) {
				file.mkdirs();
			}
			byte[] bs = new byte[1024];
			int len;
			FileOutputStream fos = new FileOutputStream(file.getPath() + "/" + UUID.randomUUID() + ".jpg");
			BufferedOutputStream out = new BufferedOutputStream(fos);
			while ((len = is.read(bs)) != -1) {
				out.write(bs, 0, len);
			}
			is.close();
			fos.close();
			out.close();
		}

	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		System.out.println("------------------感知-----------------------------------");
		this.applicationContext = applicationContext;
	}

	public <T> T getBean(Class<T> classname) {
		return applicationContext.getBean(classname);

	}
}
