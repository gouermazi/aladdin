package org.tabchanj.aladdin.controller;

import org.tabchanj.aladdin.crawler.MyWebCrawler;
import org.tabchanj.aladdin.util.common.ResourceUtil;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class CrawlerController {
    public static void main(String[] args) throws Exception {
        String crawlStorageFolder = ResourceUtil.getString("system", "ceawlerinfo");
        int numberOfCrawlers = 7;
        int maxDepthOfCrawling = 17;
        int maxPagesToFetch = 115;

        CrawlConfig config = new CrawlConfig();
        config.setCrawlStorageFolder(crawlStorageFolder);
        config.setMaxDepthOfCrawling(maxDepthOfCrawling);
        config.setMaxPagesToFetch(maxPagesToFetch);
        config.setIncludeHttpsPages(true);
        if ("true".equals(ResourceUtil.getString("system", "useproxy"))) {
            config.setProxyHost("127.0.0.1");
            config.setProxyPort(8087);
        }
        /*
         * Instantiate the controller for this crawl.
		 */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

		/*
		 * For each crawl, you need to add some seed urls. These are the first
		 * URLs that are fetched and then the crawler starts following links
		 * which are found in these pages
		 */
        controller.addSeed(ResourceUtil.getString("system", "seed"));
        // controller.addSeed("http://www.taoche.com/");
        // controller.addSeed("http://www.taoche.com/");

		/*
		 * Start the crawl. This is a blocking operation, meaning that your code
		 * will reach the line after this only when crawling is finished.
		 */
        controller.start(MyWebCrawler.class, numberOfCrawlers);
    }
}
