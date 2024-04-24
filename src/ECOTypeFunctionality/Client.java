package ECOTypeFunctionality;//Importación de bibliotecas
import java.net.*;
import java.io.*;

public class Client {
    public static void main(String args[]){
        //Se crea un bloque try-catch y se define un flujo de lectura de la entrada estándar
        try{
            BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));

            //Se obtiene la dirección y puerto del servidor del usuario
            System.out.println("Escriba la dirección del servidor: ");
            String host = br1.readLine();
            System.out.printf("\n\nEscriba puerto: ");
            int pto = Integer.parseInt(br1.readLine());

            //Se crea el socket
            Socket cl = new Socket(host, pto);

            //Se crea un flujo de carácter ligado al socket para recibir el mensaje
            BufferedReader br2 = new BufferedReader(new InputStreamReader(cl.getInputStream()));

            //Lee el mensaje recibido por el servidor
            String mensaje = br2.readLine();
            System.out.println("Recibimos un mensaje desde el servidor");
            System.out.println("Mensaje: " + mensaje);

            //Se define el mismo mensaje enviar y ligamos un PrintWriter a un flujo de salida de carácter
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
            pw.println(mensaje);
            pw.flush();

            //Se cierra los flujos, el socket y finaliza el programa
            br1.close();
            br2.close();
            pw.close();
            cl.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
