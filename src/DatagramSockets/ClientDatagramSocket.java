package DatagramSockets;

import java.io.*;
import java.net.*;

public class ClientDatagramSocket {
    public static void main(String[] args) {
        try {
            DatagramSocket cl = new DatagramSocket(); // Crea un socket de datagrama y lo liga a algún puerto de la computadora local

            // Obtención del mensaje y declaración de los datos correspodientes al servidor a utilizar
            System.out.println("Cliente iniciado, escriba un mensaje");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String mensaje = br.readLine();
            byte[] b = mensaje.getBytes();
            String dts = "127.0.0.1";
            int pto = 2000;

            // Calcula el número de paquetes necesarios
            int numPaquetes = (int) Math.ceil((double) b.length / 2000);

            // System.out.println(numPaquetes);

            // Envía cada fragmento como un paquete separado
            for (int i = 0; i < numPaquetes; i++) {
                int offset = i * 2000; // Variable de corrimiento en el buffer (Datos enviados)
                int length = Math.min(b.length - offset, 2000); // Calculo del tamaño del segmento a enviar
                DatagramPacket p = new DatagramPacket(b, offset, length, InetAddress.getByName(dts), pto); // Creación de un paquete de datagramas para envíar los segmentos
                cl.send(p); // Envío de segmentos

                // Recibe los segmentos del servidor
                DatagramPacket r = new DatagramPacket(new byte[2000], 2000); // Creación de un paquete de datagramas para recibir los segmentos
                cl.receive(r); // Recibir los segmentos
                String mensajeServidor = new String(r.getData(), 0, r.getLength()); // Obtención del mensaje de cada segmento
                System.out.println("Segmento " + (i + 1) + " recibido: " + mensajeServidor); // Impresión de los mensajes
            }
            cl.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
