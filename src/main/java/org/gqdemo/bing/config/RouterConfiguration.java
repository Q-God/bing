package org.gqdemo.bing.config;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.gqdemo.bing.service.BingHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * @version v1.0
 * @ClassName RouterConfiguration
 * @Description TODO
 * @Author Q
 */
@Configuration
public class RouterConfiguration {

    @Bean
    RouterFunction<ServerResponse> userRouterFunction(BingHandler bingHandler) {
        return RouterFunctions.nest(RequestPredicates.path("/bing"),
                RouterFunctions.route(RequestPredicates.GET(""), bingHandler::getToday)
                        .andRoute(RequestPredicates.GET("/{day}"), bingHandler::getBingByDay)
                        .andRoute(RequestPredicates.POST(""), bingHandler::save))
                .andNest(RequestPredicates.GET("/bings"),
                        RouterFunctions.route(RequestPredicates.GET(""), bingHandler::getAllBings)
                                .andRoute(RequestPredicates.GET("/{day}"), bingHandler::getBingsByDay));
//                        .andRoute(RequestPredicates.GET("/{id}"), userHandler::getUserById)
//                        .andRoute(RequestPredicates.POST("/"), userHandler::addUser)
//                        .andRoute(RequestPredicates.PUT("/"), userHandler::updateUser)
//                        .andRoute(RequestPredicates.DELETE("/{id}"), userHandler::deleteUserById));
    }


    //    @Bean
//    public CustomConversions customConversions() {
//        List<Converter<?, ?>> converterList = new ArrayList<Converter<?, ?>>();
//        converterList.add(new LocalDateReaderConverter());
//        return new CustomConversions(converterList);
//    }
}
