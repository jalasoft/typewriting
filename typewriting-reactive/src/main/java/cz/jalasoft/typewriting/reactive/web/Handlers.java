package cz.jalasoft.typewriting.reactive.web;

import cz.jalasoft.typewriting.reactive.application.ApplicationService;
import cz.jalasoft.typewriting.reactive.web.resource.LessonResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class Handlers {

	private final ApplicationService applicationService;

	@Autowired
	public Handlers(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	public Mono<ServerResponse> getLessonByNumber(ServerRequest request) {
		int number = Integer.parseInt(request.pathVariable("number"));
		Mono<LessonResource> lessonMono = applicationService.lessonByNumber(number).map(LessonResource::new);

		return ServerResponse.ok().body(lessonMono, LessonResource.class);
	}
}
