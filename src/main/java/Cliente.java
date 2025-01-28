import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
public class Cliente {
    public static void main(String[] args) {

        String HOST="127.0.0.1";
        int PUERTO=5000;
        DataInputStream in;
        DataOutputStream out;
        Scanner scanner = new Scanner(System.in);

        try{

            Socket sc = new Socket(HOST, PUERTO);

            in = new DataInputStream(sc.getInputStream());
            out = new DataOutputStream(sc.getOutputStream());

            while (true) {
                System.out.print("C: ");
                String mensaje = scanner.nextLine();
                out.writeUTF(mensaje);
                out.flush();

                String respuesta = in.readUTF();
                System.out.println("S: " + respuesta);

                if (respuesta.equals("Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta")) {
                    String fin = in.readUTF();
                    if (fin.equals("FIN")) {
                        break;
                    }
                }
            }

        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}