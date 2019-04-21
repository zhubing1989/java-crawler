package org.zhubing.crawler;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class CrawlerFirst {
    public static void main(String[] args) throws Exception {
        //1.打开浏览器,创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址,创建HttpGet对象发起get请求
        HttpGet httpGet=new HttpGet("http://www.baidu.com");

        //3.按回车，发起请求,返回响应，使用HttpClient对象发起请求
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);

        //4.解析响应,获取数据
        if (httpResponse.getStatusLine().getStatusCode()==200)
        {
            HttpEntity httpEntity = httpResponse.getEntity();
            String context = EntityUtils.toString(httpEntity, "utf8");
            System.out.println(context.length());
        }
    }
}
