package cz.jalasoft.typewriting;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

public final class Main {

	public static void main(String[] args) throws Exception {
		var server = new Server(8999);

		var ctx = new ServletContextHandler();
		server.setHandler(ctx);

		var initializer = new ServerApplicationInitializer();
		initializer.onStartup(ctx.getServletContext());

		server.start();
		server.join();
	}
}

