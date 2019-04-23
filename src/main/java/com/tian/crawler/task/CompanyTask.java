package com.tian.crawler.task;

import com.tian.crawler.common.ParseHtml;
import com.tian.crawler.entity.CompanyEntity;
import com.tian.crawler.service.CompanyService;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 解析获取企业作业：设置优先级为1
 */
@Component
@Order(value = 1)
public class CompanyTask implements CommandLineRunner {

    private Logger logger = LoggerFactory.getLogger(CompanyTask.class);

    @Autowired
    private CompanyService companyService;
    /**
     * 执行任务的地址:http://testhui.com/lab/list?page=1
     */
    private final static String URL = "http://testhui.com/lab/list";

    @Override
    public void run(String... args) throws Exception {
        logger.info(">>>>>>>>>>>>>>>开始进行抓取作业");
        int num = 9277;
//        int num = 10;
        for (int i = 252; i <= num; i++) {
//            Thread.sleep(1000);
            this.getCompanys(URL+"?page="+i);
        }
//        this.getCompanys(URL);
        logger.info(">>>>>>>>>>>>>>>结束抓取作业");
    }

    /**
     * 获取企业的列表信息
     */
    public void getCompanys(String url) {
        try {
            if (StringUtils.isEmpty(url)) {
                logger.error("请输入需要抓取的地址");
            }
            //将String类型的html转换为Document
            Document doc = ParseHtml.parseHtml(url);
            //获取所有class为pl2的元素，即包含所有<a>的div
            Elements nodes = doc.getElementsByClass("lab");
            //循环进入每个<a>，访问影片的详细信息
            for (Element node : nodes) {
                // 获取标题
                Element link_title = node.select("a[href]").first();
                System.out.println("link_title:" + link_title.text());
                // 获取公司能力简介
                Element link_text = node.select("div.lab_infor left,div.lab_text").first();
                System.out.println("link_text:" + link_text.text());
                // 获取url
                Element link_url = node.select("a[href]").first();
                System.out.println("link_url:" + (url + link_url.attr("href")));
                // 获取img_url
                Element img_url = node.select("div.lab_img img").first();
                System.out.println("img_url:" + img_url.attr("src"));
                companyService.save(new CompanyEntity(img_url.attr("src"), url+link_url.attr("href"), link_title.text(),link_text.text()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
