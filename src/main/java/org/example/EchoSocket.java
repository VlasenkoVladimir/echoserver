package org.example;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketListener;

/**
 * Socket listener class
 */
public class EchoSocket implements WebSocketListener {

    private Session session;

    @Override
    public void onWebSocketConnect(Session session) {
        this.session = session;
    }

    @Override
    public void onWebSocketText(String message) {
        try {
            if (session != null && session.isOpen()) {
                session.getRemote().sendString(message);
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        this.session = null;
    }

    @Override
    public void onWebSocketError(Throwable cause) {
        cause.printStackTrace(System.err);
    }
}