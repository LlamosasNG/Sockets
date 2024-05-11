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

                // Deserializar el objeto
                Person personDes;
                ObjectInputStream des = new ObjectInputStream(cl.getInputStream());
                personDes = (Person) des.readObject();
                System.out.println("Archivo recibido");

                // Mostrar los datos del objeto deserializado
                System.out.println("\n\nObjeto deserializado correctamente:");
                System.out.println("Name: " + personDes.name);
                System.out.println("LastName: " + personDes.lastname);
                System.out.println("Age: " + personDes.age);
                System.out.println("User: " + personDes.user);
                System.out.println("Password: " + personDes.password);
                System.out.println("Cash: " + personDes.cash);
                System.out.println("Verified: " + personDes.verified);

                des.close();
                cl.close();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
