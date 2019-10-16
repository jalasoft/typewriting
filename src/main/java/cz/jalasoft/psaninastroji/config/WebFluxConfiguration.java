package cz.jalasoft.psaninastroji.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.ViewResolverRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.view.freemarker.FreeMarkerConfigurer;

@EnableWebFlux
@Configuration
public class WebFluxConfiguration implements WebFluxConfigurer {

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.freeMarker();
	}

	@Bean
	public FreeMarkerConfigurer freeMarkerConfigurer() {
		FreeMarkerConfigurer configurer = new FreeMarkerConfigurer();

		return configurer;
	}
}
