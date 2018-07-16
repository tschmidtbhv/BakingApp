package de.naturalsoft.bakingapp.utils;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * BackingApp
 * Created by Thomas Schmidt on 11.07.2018.
 */
public class Connection {

    /**
     * Check is the user online or not
     * Ping to Google DNS
     *
     * @return
     */
    public static boolean isOnline() {

        try {

            int timeoutMs = 1500;
            Socket sock = new Socket();
            SocketAddress sockaddr = new InetSocketAddress("8.8.8.8", 53);

            sock.connect(sockaddr, timeoutMs);
            sock.close();

            return true;
        } catch (IOException e) {
            return false;
        }
    }

}
