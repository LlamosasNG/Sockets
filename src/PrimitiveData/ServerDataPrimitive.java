package PrimitiveData;

import java.io.*;
import java.net.*;

public class ServerDataPrimitive {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(2000); // Crea un socket de datagrama y lo liga al puerto 2000
            System.out.println("Servidor iniciado, esperando cliente");

            for (; ; ) {
                DatagramPacket p = new DatagramPacket(new byte[2000], 2000); // Crea DatagramPacket para recibir paquetes de longitud 2000
                s.receive(p); // Se bloquea la ajecución del programa hasta que recibe un nuevo datagrama
                System.out.println("Datagrama recibido desde " + p.getAddress() + ":" + p.getPort());

                /*
                    Crea una variable de tipo DataInputStream para recibir los datos primitivos
                    Obtiene el arreglo de bytes enviado por el cliente mediante p.getData y lo almacena en el objeto ByteArrayInputStream
                 */
                DataInputStream dis = new DataInputStream(new ByteArrayInputStream(p.getData()));

                // Lee, guarda e imprime los datos primitivos recibidos
                int x = dis.readInt();
                float f = dis.readFloat();
                long z = dis.readLong();
                System.out.println("Entero: " + x + "\n" + "Flotante: " + f + "\n" + "Entero largo: " + z + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
