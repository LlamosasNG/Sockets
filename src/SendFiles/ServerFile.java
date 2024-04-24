package SendFiles;

import java.net.*;
import java.io.*;

public class ServerFile {
    public static void main(String[] args) {
        try {
            InetAddress direccionServidor = InetAddress.getLocalHost();
            System.out.println("Dirección del servidor: " + direccionServidor.getHostAddress());

            ServerSocket s = new ServerSocket(7000);

            for (;;) {
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde " + cl.getInetAddress() + ":" + cl.getPort());

                DataInputStream dis = new DataInputStream(cl.getInputStream());
                int numArchivos = dis.readInt(); // Leemos la cantidad de archivos que el cliente enviará

                for (int i = 0; i < numArchivos; i++) {
                    byte[] b = new byte[1024];
                    String nombre = dis.readUTF();
                    System.out.println("Recibimos el archivo: " + nombre);
                    long tam = dis.readLong();
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream(nombre));

                    long recibidos = 0;
                    int n, porcentaje;

                    while (recibidos < tam) {
                        n = dis.read(b);
                        dos.write(b, 0, n);
                        dos.flush();
                        recibidos += n;
                        porcentaje = (int) (recibidos * 100 / tam);
                        if (porcentaje == 100) {
                            System.out.println("\nArchivos recibidos");
                        }
                    }
                    dos.close();
                }
                dis.close();
                cl.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
