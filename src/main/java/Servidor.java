import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    public static void main(String[] args) {
        ServerSocket servidor = null;
        Socket sc = null;
        DataInputStream in;
        DataOutputStream out;

        int PUERTO = 5000;

        // El contador me ayudara a que en caso de que el cliente introduzca una frase cuando no le toca la tome como mala
        int contador = 0;

        try {

            servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado");

            while (true) {

                sc = servidor.accept();

                System.out.println("CLIENTE CONECTADO");

                in = new DataInputStream(sc.getInputStream());
                out = new DataOutputStream(sc.getOutputStream());

                while (true) {

                    String mensajeCliente = in.readUTF();
                    String respuesta = "";

                    // Este switch es para responder a las preguntas del cliente
                    switch (mensajeCliente) {
                        case "¿Quién es?":
                            respuesta = "Soy yo";
                            contador = 1;
                            break;
                        case "¿Qué vienes a buscar?":
                            if (contador == 1) {
                                respuesta = "A ti";
                                contador = 2;
                            } else {
                                respuesta = "Error";
                            }
                            break;
                        case "Ya es tarde":
                            if (contador == 2) {
                                respuesta = "¿Por qué?";
                                contador = 3;
                            } else {
                                respuesta = "Error";
                            }
                            break;
                        case "Porque ahora soy yo la que quiere estar sin ti":
                            if (contador == 3) {
                                respuesta = "Por eso vete, olvida mi nombre, mi cara, mi casa y pega la vuelta";
                                out.writeUTF(respuesta);
                                out.writeUTF("FIN");
                                break;
                            } else {
                                respuesta = "Error";
                            }
                            break;
                        default:
                            respuesta = "Error";
                            break;
                    }

                    out.writeUTF(respuesta);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
}