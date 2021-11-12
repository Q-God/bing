package org.gqdemo.bing.job;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.gqdemo.bing.converter.BingVOAndBingConverter;
import org.gqdemo.bing.domian.Bing;
import org.gqdemo.bing.domian.vo.BingVO;
import org.gqdemo.bing.util.JsoupUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @version v1.0
 * @ClassName CrawlerBingSechedule
 * @Description TODO
 * @Author Q
 */
@Slf4j
@Component
public class CrawlerBingSechedule {

    private static final ObjectMapper om = new ObjectMapper();

    @Autowired
    private DatabaseClient databaseClient;


    @Scheduled(cron = "0 0 0 * * ?")
    public void task() throws Exception {
        String crawler = JsoupUtils.Crawler("https://cn.bing.com/HPImageArchive.aspx?format=js&idx=0&n=1");
        JsonNode jsonNode = om.readTree(crawler);
        JsonNode images = jsonNode.get("images");
        List<BingVO> bings = om.readValue(images.toString(), new TypeReference<List<BingVO>>() {
        });

        log.error("bing: {}", om.writeValueAsString(bings.get(0)));

        //BingVO bingVO = bings.get(0);
        //Bing bing = BingVOAndBingConverter.bingVOToBingCoverter(bingVO);
        List<Bing> bingList = bings.stream().map(bing -> BingVOAndBingConverter.bingVOToBingCoverter(bing)).collect(Collectors.toList());
        Bing bing = bingList.get(0);
        databaseClient.sql("INSERT INTO bing.t_bing (bot, copyright, copyrightlink, drk, enddate, fullstartdate, hsh, quiz, startdate, title, top,\n" +
                "                         url, urlbase, wp)\n" +
                "VALUES (:bot, :copyright, :copyrightlink, :drk, :enddate, :fullstartdate, :hsh, :quiz, :startdate, :title, :top, :url,\n" +
                "        :urlbase, :wp)")
                .bind("startdate", bing.getStartdate())
                .bind("fullstartdate", bing.getFullstartdate())
                .bind("enddate", bing.getEnddate())
                .bind("url", bing.getUrl())
                .bind("urlbase", bing.getUrlbase())
                .bind("copyright", bing.getCopyright())
                .bind("copyrightlink", bing.getCopyrightlink())
                .bind("title", bing.getTitle())
                .bind("quiz", bing.getQuiz())
                .bind("wp", bing.getWp())
                .bind("hsh", bing.getHsh())
                .bind("drk", bing.getDrk())
                .bind("top", bing.getTop())
                .bind("bot", bing.getBot())
// startdate, fullstartdate, enddate,url, urlbase, copyright, copyrightlink, title, quiz, wp, hsh, drk, top, bot
                .fetch()
                .first()
                .subscribe(
                        data -> log.info("inserted data : {}", data),
                        error -> log.info("error: {}", error)
                );
    }

}
