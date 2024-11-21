package org.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.websocket.server.config.JettyWebSocketServletContainerInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Main class
 */
public class EchoServer {

    public static Properties properties = new Properties();
    public static Logger logger = LoggerFactory.getLogger(EchoServer.class);

    public static void main(String[] args) {

        logger.info("Application started");

        try {
            properties.load(new FileReader(".env"));
            logger.info("Variables have been read");
        } catch (IOException e) {
            logger.error("Can't read variables file");
            System.out.println(e.getMessage());
        }

        Server server = new Server(Integer.parseInt(properties.getProperty("ECHOSERVER_PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        JettyWebSocketServletContainerInitializer.configure(context, null);
        ServletHolder wsHolder = new ServletHolder("echo", new EchoServlet());
        context.addServlet(wsHolder, "/echo");
        server.setHandler(context);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            logger.error("Can't start server");
            logger.error(e.getMessage());
        }
    }
}