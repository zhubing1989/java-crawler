package org.zhubing.crawler;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpConfigTest {
    public static void main(String[] args) {
        //创建HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //创建HttpGet对象，设置URL访问地址
        HttpGet httpGet=new HttpGet("http://www.baidu.com");

        //配置请求信息
        RequestConfig config=RequestConfig.custom().setConnectTimeout(1000)
                .setConnectionRequestTimeout(500)
                .setSocketTimeout(10*1000).build();

        //给请求设置请求信息
        httpGet.setConfig(config);

        CloseableHttpResponse response=null;
        try {
            //使用HttpClient对象发起请求，获取response
            response = httpClient.execute(httpGet);

            //解析响应
            if (response.getStatusLine().getStatusCode()==200)
            {
                String context= EntityUtils.toString(response.getEntity(),"utf8");
                System.out.println(context.length());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭response
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
