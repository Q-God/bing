package org.gqdemo.bing.util;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;

@Slf4j
public class JsoupUtils {


    public static String Crawler(String url) throws IOException {
        Connection.Response response = Jsoup.connect(url)
                .header("Accept", "*/*")
                .header("Accept-Encoding", "gzip, deflate")
                .header("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
                .header("Content-Type", "application/json;charset=UTF-8")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:48.0) Gecko/20100101 Firefox/48.0")
                .timeout(10000).ignoreContentType(true).execute();
        String body = response.body();
        //log.info("body: {}", body);
        //https://cn.bing.com/HPImageArchive.aspx?format=js&idx=1&n=4
        return body;
    }
}
