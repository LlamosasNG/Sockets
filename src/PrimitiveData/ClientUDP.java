package PrimitiveData;

import java.net.*;
import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.util.*;

public class ClientUDP {
    public final static int PUERTO = 7;
    public final static int LIMITE = 100;

    public static void main(String[] args) {
        boolean bandera = false;
        SocketAddress remoto = new InetSocketAddress("127.0.0.1", PUERTO);
        try {
            DatagramChannel canal = DatagramChannel.open();
            canal.configureBlocking(false);
            canal.connect(remoto);
            Selector selector = Selector.open();
            canal.register(selector, SelectionKey.OP_WRITE);
            ByteBuffer buffer = ByteBuffer.allocateDirect(4);
            int n = 0;
            while (true) {
                selector.select(5000);
                Set sk = selector.selectedKeys();
                if (sk.isEmpty() && n == LIMITE || bandera) {
                    canal.close();
                    break;
                } else {
                    Iterator it = sk.iterator();
                    while (it.hasNext()) {
                        SelectionKey key = (SelectionKey) it.next();
                        it.remove();
                        if (key.isWritable()) {
                            buffer.clear();
                            buffer.putInt(n);
                            buffer.flip();
                            canal.write(buffer);
                            System.out.println("Escribiendo el dato: " + n);
                            n++;
                            if (n == LIMITE) {
                                buffer.clear();
                                buffer.putInt(1000);
                                buffer.flip();
                                canal.write(buffer);
                                bandera = true;
                                key.interestOps(SelectionKey.OP_READ);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
