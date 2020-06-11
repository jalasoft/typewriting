package cz.jalasoft.typewriting;

import cz.jalasoft.typewriting.config.DevServerConfig;
import cz.jalasoft.typewriting.config.InfrastructureConfig;
import cz.jalasoft.typewriting.config.ServerConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public final class ServerApplicationInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) {

        var context = new AnnotationConfigWebApplicationContext();
        context.register(ServerConfig.class, DevServerConfig.class, InfrastructureConfig.class);
        context.setServletContext(servletContext);

        var frontendController = new DispatcherServlet(context);

        var registration = servletContext.addServlet("frontendServlet", frontendController);
        registration.setLoadOnStartup(1);
        registration.addMapping("/");
    }
}
