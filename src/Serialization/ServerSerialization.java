package Serialization;

import java.net.*;
import java.io.*;

public class ServerSerialization {
    public static void main(String[] args) {
        try {
            InetAddress direccionServidor = InetAddress.getLocalHost();
            System.out.println("Dirección del servidor: " + direccionServidor.getHostAddress());

            ServerSocket s = new ServerSocket(7000);

            for (;;) {
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde " + cl.getInetAddress() + ":" + cl.getPort());

                // Deserializar el objeto desde el InputStream del socket
                Person personDes;
                ObjectInputStream des = new ObjectInputStream(cl.getInputStream());
                personDes = (Person) des.readObject();
                System.out.println("Archivo recibido correctamente");

                // Mostrar los datos del objeto deserializado
                System.out.println("Objeto deserializado:");
                System.out.println("Nombre: " + personDes.getName());
                System.out.println("Apellido: " + personDes.getLastName());
                System.out.println("Edad: " + personDes.getAge());
                System.out.println("Usuario: " + personDes.getUser());
                System.out.println("Contraseña: " + personDes.getPassword());

                cl.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
