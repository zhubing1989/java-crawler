package jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Set;

public class JsoupFirstTest {

    @Test
    public void testUrl() throws Exception {
        //解析URL,第一个参数是访问的url,第二个参数是访问的超时时间
        Document document = Jsoup.parse(new URL("http://www.baidu.com"), 1000);

        //使用标签选择器，获取title标签的内容
        String title = document.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    public void testString() throws Exception{
        //使用工具类读取文件，获取字符串
        String content=FileUtils.readFileToString(new File("c:\\xxx\\xxx.html"),"utf8");

        //解析字符串
        Document document=Jsoup.parse(content);
        String title=document.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    @Test
    public void testFile() throws Exception{
        //解析文件
        Document document=Jsoup.parse(new File("c:\\xxx\\xxx.html"),"utf8");

        String title=document.getElementsByTag("title").first().text();
        System.out.println(title);
    }

    /**
     * 使用Dom方式遍历文档
     * 元素获取
     * 1.根据id 查询元素getElementById
     * 2.根据tag查询元素geyElementByTag
     * 3.根据class查询元素getElementByClass
     * 4.根据属性获取元素getElementByAttribute
     *
     */
    @Test
    public void testDOM()throws Exception{
        //解析文件，获取document对象
        Document document = Jsoup.parse(new File("c:\\xxx\\xxx.html"), "utf8");

        //获取的是一个元素
        Element element = document.getElementById("zhubing");


        //tag 获取多个元素
        //Elements elements = document.getElementsByTag("span");
        //Element element = document.getElementsByTag("span").first();

        //class 获取元素

        Element elementClass = document.getElementsByClass("class a class b").first();
        Element elementClassA = document.getElementsByClass("class a").first();
        Element elementClassB = document.getElementsByClass("class b").first();

        //根据属性获取元素

        Element elementAttribute = document.getElementsByAttribute("xxxx").first();

        //根据属性名和属性值获取元素

        Element elementAttributeAndValue=document.getElementsByAttributeValue("xxx","abc").first();

        System.out.println(element.text());

    }

    /**
     * 元素中获取数据
     * 1.id
     * 2.className
     * 3.attr
     * 4.attribute
     * 5.text
     *
     */

    public void testData() throws Exception{
        //解析文件，获取document
        Document document = Jsoup.parse(new File("c:\\xxx\\aaa.html"), "utf8");
        Element element = document.getElementById("zhubing");

        String str="";

        //获取id
        str=element.id();

        //获取className
        str=element.className();

        //多个className分开
        Set<String> classNames= element.classNames();
        for (String s:classNames)
        {
            System.out.println(s);
        }

        //根据属性的名字，获取属性的值

        str=element.attr("class");
        str=element.attr("id");

        //从元素中获取所有的属性

        Attributes attributes = element.attributes();
        System.out.println(attributes.toString());

        //从属性中获取文本

        str=element.text();
    }


    /**
     * Selector选择器概述
     * tagname通过标签查找元素
     * #id通过ID查找元素，比如city_zibo
     * .class通过class查找元素，比如classA
     * [attribute] 利用属性查找元素
     * [atr=value] 利用属性值来查找元素，比如：[class=s_name]
     */

    @Test
    public void testSelector()throws Exception{
        //解析html文件，获取document
        Document document=Jsoup.parse(new File("c:\\xxx\\aaa.html"),"utf8");

        //tagName:通过标签查找元素，比如span
        Elements elements = document.select("span");
        for (Element element:elements)
        {
            System.out.println(element.text());
        }

        //#id:通过ID查找元素
        Element elementById = document.select("#aaa").first();
        System.out.println("通过ID获取的元素："+elementById.text());

        //.class：通过class名称查找元素，比如.classA

        Element elementByClass = document.select(".classA").first();
        System.out.println(elementByClass.text());

        //[attribute] 利用属性查找元素，比如[abc]
        Element elementByAttributor = document.select("[abc]").first();
        System.out.println(elementByAttributor.text());

        //[attr=value] 利用属性值来查找元素
        Elements elementsByAttrValue = document.select("[class=classA]");
        for (Element element : elementsByAttrValue) {
            System.out.println(element.text());
        }


    }
}
