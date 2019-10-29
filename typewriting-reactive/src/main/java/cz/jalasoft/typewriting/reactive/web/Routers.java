package cz.jalasoft.typewriting.reactive.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.resources;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class Routers {

	private final Handlers handlers;

	@Autowired
	public Routers(Handlers handlers) {
		this.handlers = handlers;
	}

	@Bean
    public RouterFunction<ServerResponse> indexHtml(@Value("classpath:/static/index.html") Resource resource) {
        return route(GET("/index.html"), request -> ServerResponse.ok().contentType(MediaType.TEXT_HTML).syncBody(resource));
    }

    @Bean
    public RouterFunction<ServerResponse> lessonHtml(@Value("classpath:/static/lesson.html") Resource resource) {
    	return route(GET("/lesson.html"), request -> ServerResponse.ok().contentType(MediaType.TEXT_HTML).syncBody(resource));
	}

    @Bean
    public RouterFunction<ServerResponse> staticResources() {
        return resources("/static/**", new ClassPathResource("/static/"));
    }

    @Bean
    public RouterFunction<ServerResponse> getLessonByNumber() {
		return route(GET("/lesson/{number}"), handlers::getLessonByNumber);
	}
}
