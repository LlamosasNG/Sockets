package DatagramSockets;

import java.io.*;
import java.net.*;

public class ServerDatagramSocket {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(2000);
            System.out.println("Servidor iniciado, esperando cliente...");
            for (;;) {
                DatagramPacket p = new DatagramPacket(new byte[2000],  2000); // Creación de un paquete de datagramas para recibir los segmentos
                s.receive(p); // Recibir los segmentos

                // Obtención del mensaje y datos correspodientes al cliente en sesión
                System.out.println("Datagrama recibido desde " + p.getAddress() + ":" + p.getPort());
                String mensaje  = new String(p.getData(), 0, p.getLength());
                System.out.println("\nCon el mensaje: " + mensaje);

                System.out.println("Escriba un mensaje para enviar al cliente: ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String mensajeServidor = br.readLine();
                byte[] b = mensajeServidor.getBytes();

                // Calcula el número de paquetes necesarios
                int numPaquetes = (int) Math.ceil((double)b.length / 2000);

                //System.out.println(numPaquetes);

                // Envía cada fragmento como un paquete separado
                for (int i = 0; i < numPaquetes; i++) {
                    int offset = i * 2000; // Variable de corrimiento en el buffer (Datos enviados)
                    int length = Math.min(b.length - offset, 2000); // Calculo del tamaño del segmento a enviar
                    DatagramPacket e = new DatagramPacket(b, offset, length, p.getAddress(), p.getPort()); // Creación de un paquete de datagramas para envíar los segmentos
                    s.send(e); // Envío de segmentos
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
