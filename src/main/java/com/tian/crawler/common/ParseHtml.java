package com.tian.crawler.common;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 解析http成为HTML文档对象
 */
public class ParseHtml {

    private static Logger logger = LoggerFactory.getLogger(ParseHtml.class);

    public static Document parseHtml(String url) throws Exception {
        // 解析的html
        String html = null;
        HttpClient client = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = client.execute(httpGet);
            logger.info("解析地址------URL:"+url);
            if(response != null && response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity entity = response.getEntity();
                html = EntityUtils.toString(entity);
//                logger.info("解析的结果--------html:"+html);
                return Jsoup.parse(html);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("解析HTML失败");
        }
        return null;
    }
}
