package org.gqdemo.bing.dao;

import org.gqdemo.bing.domian.Bing;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BingRepository extends ReactiveCrudRepository<Bing, Long> {

    //SELECT id, startdate, fullstartdate, enddate,url, urlbase, copyright, copyrightlink, title, quiz, wp, hsh, drk, top, bot from t_bing WHERE startdate = :date
    @Query("SELECT id, startdate, fullstartdate, enddate,url, urlbase, copyright, copyrightlink, title, quiz, wp, hsh, drk, top, bot from t_bing WHERE DATE(enddate) = CURRENT_DATE()")
    Mono<Bing> getBingToday();


    @Query("SELECT id, startdate, fullstartdate, enddate,url, urlbase, copyright, copyrightlink, title, quiz, wp, hsh, drk, top, bot from t_bing")
    Flux<Bing> getTotal();

    //select * from t_bing where DateDiff(startdate,NOW()) <=0 AND DateDiff(startdate,NOW()) > -7;

    @Query("SELECT id, startdate, fullstartdate, enddate,url, urlbase, copyright, copyrightlink, title, quiz, wp, hsh, drk, top, bot from t_bing where DateDiff(enddate,NOW()) = :day")
    Mono<Bing> getBingByDay(Integer day);

    @Query("SELECT id, startdate, fullstartdate, enddate,url, urlbase, copyright, copyrightlink, title, quiz, wp, hsh, drk, top, bot from t_bing where DateDiff(enddate,NOW()) <=0 AND DateDiff(enddate,NOW()) > :day")
    Flux<Bing> getBingsByDay(Integer day);

    @Modifying
    @Query("INSERT INTO t_bing(startdate, fullstartdate, enddate,url, urlbase, copyright, copyrightlink, title, quiz, wp, hsh, drk, top, bot) VALUES( #{bing.startdate}, #{bing.fullstartdate}, #{bing.enddate},#{bing.url}, #{bing.urlbase},  #{bing.copyright}, #{bing.copyrightlink}, #{bing.title}, #{bing.quiz}, #{bing.wp}, #{bing.hsh}, #{bing.drk}, #{bing.top}, #{bing.bot})")
    Flux<Bing> add(@Param("bing") Bing bing);
}

