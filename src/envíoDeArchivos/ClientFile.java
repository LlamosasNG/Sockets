package envíoDeArchivos;

// Se agregan las bibliotecas pertinentes, se define la clase, la función main() y se inicia el bloque try-catch
import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ClientFile {
    public static void main(String[] args) {
        try {
            //Se define un flujo de entrada para obtener los datos del servidor
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.printf("Escriba la dirección del servidor: ");
            String host = br.readLine();
            System.out.println("\n\nEscriba el puerto: ");
            int pto = Integer.parseInt(br.readLine());

            //Se define el socket
            Socket cl = new Socket(host, pto);

            //Se usa un JFileChooser() para elegir el archivo a enviar
            JFileChooser jf = new JFileChooser();
            int r = jf.showOpenDialog(null);

            //Una vez seleccionado, se obtienen sus datos principales
            if (r == JFileChooser.APPROVE_OPTION) {
                File f = jf.getSelectedFile(); //Manejador
                String archivo = f.getAbsolutePath(); //Dirección
                String nombre = f.getName(); //Nombre
                long tam = f.length(); //Tamaño

                //Se definen dos flujos orientados a bytes, uno se usa para leer el archivo y el otro para enviar los datos por el socket
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream());
                DataInputStream dis = new DataInputStream(new FileInputStream(archivo));

                //Enviamos los datos generales del archivo por el socket
                dos.writeUTF(nombre);
                dos.flush();
                dos.writeLong(tam);
                dos.flush();

                //Leemos los datos contenidos en el archivo en paquetes de 1024 y los enviamos por el socket
                byte[] b = new byte[1024];
                long enviados = 0;
                int porcentaje, n;

                while (enviados < tam) {
                    n = dis.read(b);
                    dos.write(b, 0, n);
                    dos.flush();
                    enviados += n;
                    porcentaje = (int) (enviados * 100 / tam);
                    System.out.println("Enviado: " + porcentaje + "%\r");
                }
                System.out.println("\n\nArchivo enviado");
                dos.close();
                dis.close();
                cl.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
