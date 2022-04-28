package com.demo.springbootreactivewebflux.router;

import com.demo.springbootreactivewebflux.handler.AlienHandler;
import com.demo.springbootreactivewebflux.handler.AlienStreamHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private AlienHandler handler;

    @Autowired
    private AlienStreamHandler streamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunctionConfig(){
        return RouterFunctions.route()
                .GET("/route/aliens", handler::loadAliens)
                .GET("/route/aliens/stream", streamHandler::loadAliens)
                .GET("/route/aliens/{input}", handler::findAlien)
                .POST("/route/aliens/save", handler::saveAlien)
                .build();
    }
}
