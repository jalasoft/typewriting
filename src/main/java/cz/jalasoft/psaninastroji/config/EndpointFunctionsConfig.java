package cz.jalasoft.psaninastroji.config;

import cz.jalasoft.psaninastroji.application.ApplicationService;
import cz.jalasoft.psaninastroji.domain.model.lesson.excercise.ExerciseId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RouterFunctions.resources;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

/**
 * @author Jan Lastovicka
 * @since 15/10/2019
 */
@Configuration
public class EndpointFunctionsConfig {

    @Autowired
    private ApplicationService applicationService;

    @Bean
    public RouterFunction<ServerResponse> indexHtml(@Value("classpath:/static/index.html") Resource resource) {
        return route(GET("/index.html"), request -> {
           return ServerResponse.ok().contentType(MediaType.TEXT_HTML).syncBody(resource);
        });
    }

    @Bean
    public RouterFunction<ServerResponse> lessonHtml(@Value("classpath:/static/lesson.html") Resource resource) {
    	return route(GET("/lesson.html"), request -> {
    		return ServerResponse.ok().contentType(MediaType.TEXT_HTML).syncBody(resource);
		});
	}

    @Bean
    public RouterFunction<ServerResponse> staticResources() {
        return resources("/static/**", new ClassPathResource("/static/"));
    }


    /*
    @Bean
    public RouterFunction<ServerResponse> newExerciseByLessonNumber() {
        return route(POST("/lesson/{number}"), request -> {
            int lessonNumber = Integer.parseInt(request.pathVariable("number"));
            Mono<ExerciseId> exerciseMono = applicationService.newExercise(lessonNumber);
            return ServerResponse.ok().body(exerciseMono.map(ExerciseId::value), String.class);
        });
    }*/

    @Bean
    public RouterFunction<ServerResponse> getLessonByNumber() {
        return route(GET("/lesson/{number}"), request -> {
            int number = Integer.parseInt(request.pathVariable("number"));
            Mono<LessonResource> lessonMono = applicationService.lessonByNumber(number).map(LessonResource::new);

            return ServerResponse.ok().body(lessonMono, LessonResource.class);
        });
    }
}
