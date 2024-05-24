package PrimitiveData;

import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class ServerUDP {
    public final static int PUERTO = 7;
    public final static int TAM_MAX = 65501;

    public static void main(String[] args) {
        int port = PUERTO;

        try {
            DatagramChannel canal = DatagramChannel.open();
            canal.configureBlocking(false);
            DatagramSocket socket = canal.socket();
            SocketAddress dir = new InetSocketAddress(port);
            socket.bind(dir);
            Selector selector = Selector.open();
            canal.register(selector, SelectionKey.OP_READ);
            ByteBuffer buffer = ByteBuffer.allocate(TAM_MAX);

            while (true) {
                selector.select(5000);
                Set sk = selector.selectedKeys();
                Iterator it = sk.iterator();

                while (it.hasNext()) {
                    SelectionKey key = (SelectionKey) it.next();
                    it.remove();
                    if (key.isReadable()) {
                        buffer.clear();
                        SocketAddress client = canal.receive(buffer);
                        buffer.flip();
                        int eco = buffer.getInt();
                        if (eco == 1000) {
                            canal.close();
                            System.exit(0);
                        } else {
                            System.out.println("Dato leído: " + eco);
                            buffer.flip();
                            canal.send(buffer, client);
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
