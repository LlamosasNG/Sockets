package DatagramSockets;

import java.net.*;
import java.io.*;

public class ServerDatagramSocketExample {
    public static void main(String[] args) {
        try {
            DatagramSocket s = new DatagramSocket(2000);
            System.out.println("Servidor iniciado, esperando cliente...");
            for (;;){
                DatagramPacket p = new DatagramPacket(new byte[2000], 2000);
                s.receive(p);
                System.out.println("\nDatagrama recibido desde " + p.getAddress() + ":" + p.getPort());
                String mensaje  = new String(p.getData(), 0, p.getLength());
                System.out.println("Con el mensaje: " + mensaje);

                System.out.println("Escriba un mensaje para enviar al cliente: ");
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String mensajeServidor = br.readLine();
                byte[] b = mensajeServidor.getBytes();

                DatagramPacket e = new DatagramPacket(b, b.length, p.getAddress(), p.getPort());

                s.send(e);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
