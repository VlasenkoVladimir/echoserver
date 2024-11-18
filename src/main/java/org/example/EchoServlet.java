package org.example;

import org.eclipse.jetty.websocket.server.JettyWebSocketServlet;
import org.eclipse.jetty.websocket.server.JettyWebSocketServletFactory;

import java.time.Duration;

import static org.example.EchoServer.logger;
import static org.example.EchoServer.properties;

/**
 * Servlet class for JettyWebSocketServer
 */
public class EchoServlet extends JettyWebSocketServlet {

    /**
     * Configuring servlet
     * @param factory the JettyWebSocketServletFactory
     */
    @Override
    protected void configure(JettyWebSocketServletFactory factory) {

        logger.info("Start configuring servlet");
        factory.setInputBufferSize(Integer.parseInt(properties.getProperty("INPUT_BUFFER_SIZE")));
        factory.setIdleTimeout(Duration.ofMillis(Long.parseLong(properties.getProperty("IDLE_TIMEOUT"))));
        factory.register(EchoSocket.class);
        logger.info("Servlet have been configured");
    }
}