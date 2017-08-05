package test;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.tabchanj.aladdin.crawler.MyWebCrawler;
import org.tabchanj.aladdin.domain.PicUrl;
import org.tabchanj.aladdin.service.IPicUrlService;
import org.tabchanj.aladdin.util.common.ResourceUtil;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/webapp/WEB-INF/applicationContext.xml" })
public class CrawlerTest {

	@Resource
	private IPicUrlService picUrlService;

	@Test
	public void get() throws Exception {
		System.out.println(picUrlService.getAll().size());
	}

	@Test
	public void CrawlerpPic() throws Exception {
		String crawlStorageFolder = ResourceUtil.getString("system", "ceawlerinfo");
		int numberOfCrawlers = 7;
		int maxDepthOfCrawling = 7;
		int maxPagesToFetch = 115;

		CrawlConfig config = new CrawlConfig();
		config.setCrawlStorageFolder(crawlStorageFolder);
		config.setMaxDepthOfCrawling(maxDepthOfCrawling);
		config.setMaxPagesToFetch(maxPagesToFetch);
		if ("true".equals(ResourceUtil.getString("system", "useproxy"))) {
			config.setProxyHost("127.0.0.1");
			config.setProxyPort(8087);
		}
		config.setUserAgentString("Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0");
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

	@Test
	public void Dowload() throws Exception {
		List<PicUrl> list = picUrlService.getAll();
		if (list != null && list.size() > 0) {
			for (PicUrl picUrl : list) {
				if (picUrl.getUrl().endsWith("mp4")) {
					URL t_url = new URL(picUrl.getUrl());
					 InetSocketAddress addr = new InetSocketAddress("127.0.0.1",8087);  
		             Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理  
		             HttpsURLConnection connec = (HttpsURLConnection)t_url.openConnection(proxy);
					InputStream is = connec.getInputStream();
					File file = new File(ResourceUtil.getString("system", "localfileprefix_img"));
					if (!file.exists()) {
						file.mkdirs();
					}
					byte[] bs = new byte[1024];
					int len;
					FileOutputStream fos = new FileOutputStream(file.getPath() + "/" + UUID.randomUUID() + ".mp4");
					BufferedOutputStream out = new BufferedOutputStream(fos);
					while ((len = is.read(bs)) != -1) {
						out.write(bs, 0, len);
					}
					out.close();
					fos.close();
					is.close();
					Thread.sleep(5000);
				}
			}
		}

	}

	@Test
	public void Dowload1() {
		InputStream is = null;
		FileOutputStream fos = null;
		BufferedOutputStream out = null;
		try {
			List<PicUrl> list = picUrlService.getAll();
			if (list != null && list.size() > 0) {
				for (PicUrl picUrl : list) {
					if (picUrl.getUrl().endsWith("jpg")) {
						URL t_url = new URL(picUrl.getUrl());
						URLConnection connec = t_url.openConnection();
						is = connec.getInputStream();
						File file = new File(ResourceUtil.getString("system", "localfileprefix_img"));
						if (!file.exists()) {
							file.mkdirs();
						}
						byte[] bs = new byte[] {};
						int len;
						fos = new FileOutputStream(file.getPath() + "/" + UUID.randomUUID() + ".jpg");
						out = new BufferedOutputStream(fos);
						while ((len = is.read(bs)) != -1) {
							out.write(bs, 0, len);
						}
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				is.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

    private static final String METHOD_POST = "GET";  
        private static final String DEFAULT_CHARSET = "utf-8";  
        
        @Test
		public void DoP() throws Exception {
        	doPost("https://storage.googleapis.com/project-parnas/realitykings/17_09_2016_Cute_And_Curvy.mp4",null,"utf-8",300000000,300000000);
			
		}
        
        public static String doPost(String url, String params, String charset, int connectTimeout, int readTimeout) throws Exception {  
            String ctype = "application/octet-stream";  
            byte[] content = {};  
            if(params != null){  
                content = params.getBytes(charset);  
            }  
              
            return doPost(url, ctype, content, connectTimeout, readTimeout);  
        }  
        public static String doPost(String url, String ctype, byte[] content,int connectTimeout,int readTimeout) throws Exception {  
            HttpsURLConnection conn = null;  
            OutputStream out = null;  
            String rsp = null;  
            try {  
                try{  
                    SSLContext ctx = SSLContext.getInstance("TLS");  
                    ctx.init(new KeyManager[0], new TrustManager[] {new DefaultTrustManager()}, new SecureRandom());  
                    SSLContext.setDefault(ctx);  
      
                    conn = getConnection(new URL(url), METHOD_POST, ctype);   
                    conn.setHostnameVerifier(new HostnameVerifier() {  
                        @Override  
                        public boolean verify(String hostname, SSLSession session) {  
                            return true;  
                        }  
                    });  
                    conn.setConnectTimeout(connectTimeout);  
                    conn.setReadTimeout(readTimeout);
//                    DataOutputStream os = new DataOutputStream(conn.getOutputStream() );
//                    os.write( "".getBytes("UTF-8"), 0, 0);
//                    os.flush();
//                    os.close();
                    conn.connect();
                }catch(Exception e){  
                    throw e;  
                }  
                try{  
                	InputStream is = conn.getInputStream();
					File file = new File(ResourceUtil.getString("system", "localfileprefix_img"));
					if (!file.exists()) {
						file.mkdirs();
					}
					byte[] bs = new byte[1024*1024];
					int len;
					FileOutputStream fos = new FileOutputStream(file.getPath() + "/" + UUID.randomUUID() + ".mp4");
					BufferedOutputStream out1 = new BufferedOutputStream(fos);
					while ((len = is.read(bs)) != -1) {
						out1.write(bs, 0, len);
					}
					out1.close();
					fos.close();
					is.close();
					Thread.sleep(10000);  
                }catch(IOException e){  
                    throw e;  
                }  
                  
            }finally {  
                if (out != null) {  
                    out.close();  
                }  
                if (conn != null) {  
                    conn.disconnect();  
                }  
            }  
              
            return rsp;  
        }  
      
          
        private static HttpsURLConnection getConnection(URL url, String method, String ctype)  
                throws IOException { 
        	InetSocketAddress addr = new InetSocketAddress("127.0.0.1",8087);  
            Proxy proxy = new Proxy(Proxy.Type.HTTP, addr); // http 代理  
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection(proxy);  
            conn.setRequestMethod(method);  
            conn.setDoInput(true);  
            conn.setDoOutput(true);  
            conn.setRequestProperty("Content-Length","0");
            conn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:53.0) Gecko/20100101 Firefox/53.0");  
            conn.setRequestProperty("Content-Type", ctype);  
            return conn;  
        }  
      
      
        private static String getStreamAsString(InputStream stream, String charset) throws IOException {  
            try {  
                BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charset));  
                StringWriter writer = new StringWriter();  
      
                char[] chars = new char[256];  
                int count = 0;  
                while ((count = reader.read(chars)) > 0) {  
                    writer.write(chars, 0, count);  
                }  
      
                return writer.toString();  
            } finally {  
                if (stream != null) {  
                    stream.close();  
                }  
            }  
        }  
        
}

