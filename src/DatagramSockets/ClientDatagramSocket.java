package DatagramSockets;

import java.net.*;
import java.io.*;

public class ClientDatagramSocket {
    public static void main(String[] args) {
        try{
            DatagramSocket cl = new DatagramSocket();
            System.out.println("Cliente iniciado, escriba un mensaje");
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String mensaje = br.readLine();
            byte[] b = mensaje.getBytes();
            String dts = "localhost";
            int pto = 2000;

            DatagramPacket p = new DatagramPacket(b, b.length, InetAddress.getByName(dts), pto);

            cl.close();
            cl.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
