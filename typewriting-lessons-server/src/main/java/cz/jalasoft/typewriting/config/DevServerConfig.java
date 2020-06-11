package cz.jalasoft.typewriting.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

/**
 * @author Jan Lastovicka
 * @since 10/05/2020
 */
@EnableWebMvc
@Configuration
@Primary
@Profile("DEV")
public class DevServerConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String path = "file:" + System.getProperty("user.dir") + "/typewriting-lessons-server/web/dist/";

        registry
                .addResourceHandler("/**")
                .addResourceLocations(path)
                .setCachePeriod(0)
                .setCacheControl(CacheControl.noCache())
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
