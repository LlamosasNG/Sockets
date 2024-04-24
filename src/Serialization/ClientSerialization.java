package Serialization;

import java.net.*;
import java.io.*;


public class ClientSerialization {
    public static void main(String[] args) {
        // Crear objeto Person a serializar
        Person person = new Person("Noe Ramses", "Gonzalez Llamosas", 22, "LlamosasNG", "123456");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Escriba la dirección del servidor: ");
            String host = br.readLine();
            System.out.println("Escriba el puerto: ");
            int pto = Integer.parseInt(br.readLine());

            Socket cl = new Socket(host, pto);

            //Serialización de un objeto en un archivo plano
            String archivo = "person.txt";
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(archivo));
            out.writeObject(person);
            System.out.println("\n\nObjeto serializado correctamente en " + archivo);
            // Imprimir los atributos del objeto despues de serializarlo
            System.out.println("Atributos del objeto a enviar:");
            System.out.println("Name: " + person.getName());
            System.out.println("Lastname: " + person.getLastName());
            System.out.println("Age: " + person.getAge());
            System.out.println("User: " + person.getUser());
            System.out.println("Password: " + person.getPassword());
            out.close();

            // Envío del archivo al servidor
            FileInputStream fileIn = new FileInputStream(archivo);
            OutputStream os = cl.getOutputStream();
            byte[] b = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileIn.read(b)) != -1) {
                os.write(b, 0, bytesRead);
            }
            System.out.println("Archivo enviado correctamente");
            fileIn.close();
            os.close();

            cl.close();
        }catch (Exception e) {
            e.printStackTrace();
        }



    }
}
