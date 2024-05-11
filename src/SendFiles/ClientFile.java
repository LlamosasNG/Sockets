import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class ClientFile {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.printf("Escriba la dirección del servidor: ");
            String host = br.readLine();
            System.out.println("\n\nEscriba el puerto: ");
            int pto = Integer.parseInt(br.readLine());

            Socket cl = new Socket(host, pto);

            JFileChooser jf = new JFileChooser();
            jf.setMultiSelectionEnabled(true);
            int r = jf.showOpenDialog(null);

            if (r == JFileChooser.APPROVE_OPTION) {
                File[] files = jf.getSelectedFiles();
                DataOutputStream dos = new DataOutputStream(cl.getOutputStream()); //Se crea un flujo orientado a bytes para enviar los datos del archivo por el socket
                dos.writeInt(files.length); //Se envian los archivos seleccionados
                dos.flush();

                for (File file : files) {
                    String archivo = file.getAbsolutePath(); //Manejador
                    String nombre = file.getName(); //Nombre
                    long tam = file.length(); //Tamaño

                    DataInputStream dis = new DataInputStream(new FileInputStream(archivo)); //Se crea un flujo orientado a bytes para leer los datos del archivo
                    dos.writeUTF(nombre);
                    dos.flush();
                    dos.writeLong(tam);
                    dos.flush();

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

                        if(porcentaje == 100) {
                            System.out.println("\n\nArchivos enviados");
                        }
                    }
                    dis.close();
                }
                dos.close();
                cl.close();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
