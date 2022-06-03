package SerClien;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import javax.swing.JOptionPane;

public class Cliente extends Thread {
    
    private Socket socket; 
    private ObjectOutputStream objectOutputStream;
 
    private ObjectInputStream objectInputStream;      
    private final Ventana ventana;    
    private String identificador;
    private boolean escuchando;
    private final String host;

    private final int puerto;

    Cliente(Ventana ventana, String host, Integer puerto, String nombre) {
        this.ventana=ventana;        
        this.host=host;
        this.puerto=puerto;
        this.identificador=nombre;
        escuchando=true;
        this.start();
    }

    public void run(){
        try {
            socket=new Socket(host, puerto);
            objectOutputStream=new ObjectOutputStream(socket.getOutputStream());
            objectInputStream=new ObjectInputStream(socket.getInputStream());
            System.out.println("¡¡CONEXIÓNJ EXITOSA!!");
            this.enviarSolicitudConexion(identificador);
            this.escuchar();
        } catch (UnknownHostException ex) {
            JOptionPane.showMessageDialog(ventana, "Conexión inválida, servidor desconocido,\n"
                                                 + "puede que haya ingresado una ip incorrecta\n"
                                                 + "o que el servidor no este activo.\n"
                                                 + "Esta aplicación se detendrá y cerrará.");
            System.exit(0);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(ventana, "Conexión inválida, error de entrada o salida,\n"
                                                 + "puede que haya ingresado una ip o un puerto\n"
                                                 + "incorrecto, o que el servidor no este activo.\n"
                                                 + "Esta aplicación se detendrá y cerrará.");
            System.exit(0);
        }

    }
    
    public void desconectar(){
        try {
            objectOutputStream.close();
            objectInputStream.close();
            socket.close();  
            escuchando=false;
        } catch (Exception e) {
            System.err.println("Error al cerrar los elementos de comunicación del cliente.");
        }
    }

    public void enviarMensaje(String cliente_receptor, String mensaje){
        LinkedList<String> lista=new LinkedList<>();

        lista.add("MENSAJE");
 
        lista.add(identificador);
 
        lista.add(cliente_receptor);

        lista.add(mensaje);
        try {
            objectOutputStream.writeObject(lista);
        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura al enviar mensaje al servidor.");
        }
    }
     
    public void escuchar() {
        try {
            while (escuchando) {
                Object aux = objectInputStream.readObject();
                if (aux != null) {
                    if (aux instanceof LinkedList) {
                        
                        ejecutar((LinkedList<String>)aux);
                    } else {
                        System.err.println("Se registró un objeto desconocido a través del socket");
                    }
                } else {
                    System.err.println("Se recibió un null a través del socket");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(ventana, "La conexión con el servidor se ha\n"
                                                 + "perdido, este chat finalizará el proceso.\n"
                                                 + "La aplicación se detendrá y cerrará.");
            System.exit(0);
        }
    }

    public void ejecutar(LinkedList<String> lista){
        String tipo=lista.get(0);
        switch (tipo) {
            case "CONEXION_ACEPTADA":
                identificador=lista.get(1);
                ventana.sesionIniciada(identificador);
                for(int i=2;i<lista.size();i++){
                    ventana.addContacto(lista.get(i));
                }
                break;
            case "NUEVO_USUARIO_CONECTADO":

                ventana.addContacto(lista.get(1));
                break;
            case "USUARIO_DESCONECTADO":

                ventana.eliminarContacto(lista.get(1));
                break;                
            case "MENSAJE":

                ventana.addMensaje(lista.get(1), lista.get(3));
                break;
            default:
                break;
        }
    }
    private void enviarSolicitudConexion(String identificador) {
        LinkedList<String> lista=new LinkedList<>();

        lista.add("SOLICITUD_CONEXION");

        lista.add(identificador);
        try {
            objectOutputStream.writeObject(lista);
        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura al enviar mensaje al servidor.");
        }
    }
    void confirmarDesconexion() {
        LinkedList<String> lista=new LinkedList<>();

        lista.add("SOLICITUD_DESCONEXION");

        lista.add(identificador);
        try {
            objectOutputStream.writeObject(lista);
        } catch (IOException ex) {
            System.out.println("Error de lectura y escritura al enviar mensaje al servidor.");
        }
    }
    String getIdentificador() {
        return identificador;
    }
}