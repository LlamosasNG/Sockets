package ECOTypeFunctionality;//Importación de bibliotecas
import java.net.*;
import java.io.*;


public class Server {
    public static void main(String args[]){

        try {
            //Obtenemos la dirección del servidor en terminal
            InetAddress direccionServidor = InetAddress.getLocalHost();
            System.out.println("Dirección del servidor: " + direccionServidor.getHostAddress());

            //Se define la clase ligada al puerto 1234
            ServerSocket s = new ServerSocket(1234);
            System.out.println("\n\nEsperando cliente...");

            //El servidor espera dentro infinito la solicitud de conexión de un cliente
            for(;;){
                Socket cl = s.accept();
                System.out.println("Conexión establecida desde " + cl.getInetAddress() + ":" + cl.getPort() + '\n');

                //Se define el mensaje enviar y ligamos un PrintWriter a un flujo de salida de carácter
                String mensaje = "Noe Rames Gonzalez Llamosas - 6CV4 - Aplicaciones para comunicaciones en red";
                PrintWriter pw = new PrintWriter(new OutputStreamWriter(cl.getOutputStream()));
                pw.println(mensaje);
                pw.flush();

                //Se crea un flujo de carácter ligado al socket para recibir el mensaje
                BufferedReader br1 = new BufferedReader(new InputStreamReader(cl.getInputStream()));

                //Lee el mensaje recibido por el cliente
                String mensajeServidor = br1.readLine();
                System.out.println("Recibimos un mensaje desde el cliente");
                System.out.println("Mensaje: " + mensajeServidor);

                //Se cierra el flujo, el socket, el ciclo for y el bloque try-catch
                pw.close();
                cl.close();
            }//for
        } catch (Exception e){
            e.printStackTrace();
        }//catch
    }//main
}