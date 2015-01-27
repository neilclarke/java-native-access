package com.neilclarke.tech;

import java.io.IOException;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class EmbeddedJetty {

	private static final int DEFAULT_PORT = 8080;
	private static final String CONTEXT_PATH = "/";
	private static final String CONFIG_LOCATION = "com.neilclarke.config";
	private static final String MAPPING_URL = "/*";

	public static void main(String[] args) throws Exception {
		
		System.getProperties().put("jna.library.path", "build/binaries/incSharedLibrary");
		
		new EmbeddedJetty().startJetty(getPortFromArgs(args));
	}

	private static int getPortFromArgs(String[] args) {
		
		if (args.length > 0) {
			try {
				return Integer.valueOf(args[0]);
			} catch (NumberFormatException ignore) {
			}
		}
		
		return DEFAULT_PORT;
	}

	/**
	 * @param port
	 * @throws Exception
	 */
	private void startJetty(int port) throws Exception {
		
		Server server = new Server(port);
		
		server.setHandler(getServletContextHandler(getContext()));
		server.start();
		server.join();
	}

	/**
	 * @param context
	 * @return
	 * @throws IOException
	 */
	private static ServletContextHandler getServletContextHandler(
			WebApplicationContext context) throws IOException {
		
		ServletContextHandler contextHandler = new ServletContextHandler();
		
		contextHandler.setErrorHandler(null);
		contextHandler.setContextPath(CONTEXT_PATH);
		contextHandler.addServlet(new ServletHolder(new DispatcherServlet(
				context)), MAPPING_URL);
		contextHandler.addEventListener(new ContextLoaderListener(context));
		
		return contextHandler;
	}

	/**
	 * @return
	 */
	private static WebApplicationContext getContext() {
		AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
		context.setConfigLocation(CONFIG_LOCATION);
		return context;
	}
}