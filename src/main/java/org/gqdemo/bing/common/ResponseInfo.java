package org.gqdemo.bing.common;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Data
public class ResponseInfo<T> implements Serializable {
    //定义当前时间戳
    private long timestamp;

    //定义Response状态
    private Map<String, Object> status;

    //定义返回消息
    private String message;

    //定义返回数据
    private T data;

    public ResponseInfo() {
    }

    public static <T> Mono<ResponseInfo<T>> success(Mono<T> monoBody) {
        return success(monoBody, HttpStatus.OK, null);
    }

    public static <T> Mono<ResponseInfo<T>> success(Mono<T> monoBody, String message) {
        return success(monoBody, HttpStatus.OK, message);
    }

    public static <T> Mono<ResponseInfo<T>> success(Mono<T> monoBody, HttpStatus httpStatus, String message) {
        return responseBodyCreate(monoBody, httpStatus, message);
    }

    public static <T> Flux<ServerResponse> success(Flux<T> fluxBody) {
        return success(fluxBody, HttpStatus.OK, null);
    }

    public static <T> Flux<ServerResponse> success(Flux<T> fluxBody, String message) {
        return success(fluxBody, HttpStatus.OK, message);
    }

    public static <T> Flux<ServerResponse> success(Flux<T> fluxBody, HttpStatus httpStatus, String message) {
        return responseBodyCreateFlux(fluxBody, httpStatus, message);
    }

    public static <T> Mono<ResponseInfo<T>> failed(Mono<T> monoBody) {
        return responseBodyCreate(monoBody, HttpStatus.INTERNAL_SERVER_ERROR
                , null);
    }

    public static <T> Mono<ResponseInfo<T>> failed(Mono<T> monoBody, String message) {
        return responseBodyCreate(monoBody, HttpStatus.INTERNAL_SERVER_ERROR, message);
    }

    public static <T> Mono<ResponseInfo<T>> failed(Mono<T> monoBody, HttpStatus httpStatus, String message) {
        return responseBodyCreate(monoBody, httpStatus, message);
    }

    private static <T> Mono<ResponseInfo<T>> responseBodyCreate(Mono<T> monoData, HttpStatus httpStatus, String message) {
        return monoData.map(data -> {
            final ResponseInfo<T> responseInfo = new ResponseInfo<>();
            responseInfo.setTimestamp(System.currentTimeMillis());
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("code", httpStatus.value());
            map.put("msg", httpStatus.getReasonPhrase());
            responseInfo.setStatus(map);
            responseInfo.setData(data);
            responseInfo.setMessage(message);
            return responseInfo;
        });
    }

    private static <T> Flux<ServerResponse> responseBodyCreateFlux(Flux<T> fluxData, HttpStatus httpStatus, String message) {
        return fluxData.flatMap(
                data -> {
                    ResponseInfo responseInfo = new ResponseInfo();
                    responseInfo.setTimestamp(System.currentTimeMillis());
                    Map<String, Object> map = new LinkedHashMap<>();
                    map.put("code", httpStatus.value());
                    map.put("msg", httpStatus.getReasonPhrase());
                    responseInfo.setStatus(map);
                    responseInfo.setData(data);
                    responseInfo.setMessage(message);
                    return ok().contentType(MediaType.APPLICATION_JSON).body(Mono.just(responseInfo), ResponseInfo.class);
                }
        );
    }

}