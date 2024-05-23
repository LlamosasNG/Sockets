package DatagramSockets;

import java.net.*;
import java.io.*;

public class ClientDatagramSocketExample {
    public static void main(String[] args) {
        try{
            DatagramSocket cl = new DatagramSocket();
            System.out.println("Cliente iniciado, escriba un mensaje");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String mensaje = br.readLine();
            byte[] b = mensaje.getBytes();
            String dts = "127.0.0.1";
            int pto = 2000;

            DatagramPacket p = new DatagramPacket(b, b.length, InetAddress.getByName(dts), pto);
            cl.send(p);

            DatagramPacket r = new DatagramPacket(new byte[2000], 2000);
            cl.receive(r);
            System.out.println("\nDatagrama recibido desde " + r.getAddress() + ":" + r.getPort());
            String mensajeServidor  = new String(r.getData(), 0, r.getLength());
            System.out.println("Con el mensaje: " + mensajeServidor);

            cl.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
