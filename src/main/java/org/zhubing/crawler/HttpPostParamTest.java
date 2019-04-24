package org.zhubing.crawler;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpPostParamTest {
    public static void main(String[] args) throws Exception {
        //创建HttpClient
        CloseableHttpClient httpClient = HttpClients.createDefault();

        //穿件HttpPost对象，设置URL访问地址
        HttpPost httpPost=new HttpPost("http://yun.itheima.com/search");

        //声明List<>集合，封装表单中的参数
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        //设置请求的地址是：http://yun.itheima.com/search?keys=java
        params.add(new BasicNameValuePair("keys","java"));

        //创建表单的Entity对象，第一个参数是封装好的表单数据，第二个是编码
        UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(params,"utf8");


        //设置表单的entity对象到post请求中
        httpPost.setEntity(formEntity);

        CloseableHttpResponse response=null;
        try {
            //使用HttpClient对象发起请求，获取response
            response = httpClient.execute(httpPost);

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
