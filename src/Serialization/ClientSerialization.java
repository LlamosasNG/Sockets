package Serialization;

import java.net.*;
import java.io.*;

class Person implements Serializable {
    String name, lastname, user;
    boolean verified;

    transient int age;
    transient float cash;
    transient String password;

    public Person(String name, String lastname, int age, String user, String password, float cash, boolean verified ) {
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.user = user;
        this.password = password;
        this.cash = cash;
        this.verified = verified;
    }
}

public class ClientSerialization {
    public static void main(String[] args) {
        // Crear objeto Person a serializar
        Person person = new Person("Noe Ramses", "Gonzalez Llamosas", 22, "LlamosasNG", "123456", 758.21f, true);

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escriba la dirección del servidor: ");
            String host = br.readLine();
            System.out.println("Escriba el puerto: ");
            int pto = Integer.parseInt(br.readLine());

            Socket cl = new Socket(host, pto);

            //Serialización del objeto en un archivo
            String archivo = "person.txt";
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo));
            out.writeObject(person);
            System.out.println("\n\nObjeto serializado correctamente en " + archivo);

            // Impresión de los atributos del objeto despues de serializarlo
            System.out.println("Atributos del objeto a enviar:");
            System.out.println("Name: " + person.name);
            System.out.println("LastName: " + person.lastname);
            System.out.println("Age: " + person.age);
            System.out.println("User: " + person.user);
            System.out.println("Password: " + person.password);
            System.out.println("Cash: " + person.cash);
            System.out.println("Verified: " + person.verified);
            out.close();

            // Envío del archivo al servidor
            FileInputStream fileIn = new FileInputStream(archivo);
            OutputStream dos = cl.getOutputStream();
            byte[] b = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileIn.read(b)) != -1) {
                dos.write(b, 0, bytesRead);
            }
            System.out.println("\n\nArchivo enviado");
            fileIn.close();
            dos.close();

            cl.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

