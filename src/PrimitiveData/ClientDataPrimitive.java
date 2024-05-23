package PrimitiveData;

import java.io.*;
import java.net.*;

public class ClientDataPrimitive {
    public static void main (String [] args) {
        try {
            // Crea las constantes con el puerto y dirección del servidor
            int pto = 2000;
            InetAddress dst = InetAddress.getByName("127.0.0.1");

            DatagramSocket cl = new DatagramSocket(); // Crea un socket de datagrama y lo liga a algún puerto de la computadora local
            ByteArrayOutputStream baos = new ByteArrayOutputStream(); // Crea un objeto de tipo ByteArrayOutputStream para escribir el arreglo de bytes al servidor
            DataOutputStream dos = new DataOutputStream(baos); //Crea el flujo de salida para el envío de datos primitivos

            // Escribe los datos primitivos en el flujo previamente creado
            dos.writeInt(4);
            dos.flush();
            dos.writeFloat(4.1f);
            dos.flush();
            dos.writeLong(7242521);
            dos.flush();

            byte[] b = baos.toByteArray(); // Se crea una los datos que han sido escritos en el ByteArrayOutputStream y se guardan en el arreglo de bytes b

            DatagramPacket p = new DatagramPacket(b, b.length, dst, pto); // Crea DatagramPacket para enviar paquetes de longitud length, a una dirección y puerto específico
            cl.send(p); // Envia el datagrama al servidor
            System.out.println("¡¡Datos enviado correctamente!!");
            cl.close(); // Se cierra el socket
        } catch (Exception e){
            e.printStackTrace();
        }
    }

}
