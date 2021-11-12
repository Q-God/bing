package org.gqdemo.bing.service;

import org.gqdemo.bing.common.ResponseInfo;
import org.gqdemo.bing.dao.BingRepository;
import org.gqdemo.bing.domian.Bing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @version v1.0
 * @ClassName BingHandler
 * @Description TODO
 * @Author Q
 */
@Service
public class BingHandler {

    @Autowired
    private BingRepository bingRepository;

    public Mono<ServerResponse> getAllBings(ServerRequest serverRequest) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(bingRepository.getTotal(), Bing.class);
    }


    /**
     * 获取当日bing数据 JSON
     *
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> getToday(ServerRequest serverRequest) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(ResponseInfo.success(bingRepository.getBingToday(), "最新一天的数据"), Bing.class);
    }

    /**
     * 获取指定天的Bing数据
     *
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> getBingByDay(ServerRequest serverRequest) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(ResponseInfo.success(bingRepository.getBingByDay(-Integer.valueOf(serverRequest.pathVariable("day"))), "前" + serverRequest.pathVariable("day") + "天的数据"), Bing.class);
    }

    /**
     * 获取指定天前至今的Bing数据
     *
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> getBingsByDay(ServerRequest serverRequest) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(bingRepository.getBingsByDay(-Integer.valueOf(serverRequest.pathVariable("day"))), Bing.class);
    }

    /**
     * 添加
     *
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        return ok().contentType(MediaType.APPLICATION_JSON)
                .body(bingRepository.saveAll(serverRequest.bodyToMono(Bing.class)), Bing.class);
    }

    public void save(Iterable<? extends Bing> iterable) {
        bingRepository.saveAll(iterable);
    }
//
//    public Mono<ServerResponse> getWeek(ServerRequest serverRequest) {
//        return ok().contentType(MediaType.APPLICATION_JSON)
//                .body(bingRepository.getBingBy)
//    }

}
