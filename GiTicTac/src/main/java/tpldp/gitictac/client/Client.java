package tpldp.gitictac.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Classe "Client" responsável pela comunicação com o servidor.
 */
public class Client {
    private final ObjectInputStream objectInputStream;
    private final ObjectOutputStream objectOutputStream;

    public Client(String ip, int port) throws IOException {
        Socket socket = new Socket(ip, port);
        objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.flush();
        objectInputStream = new ObjectInputStream(socket.getInputStream());
    }

    /**
     * Recebe um objeto para ser eviado para o servidor.
     * @param object
     * @throws IOException
     */
    public void sendMove(Object object) throws IOException {
        objectOutputStream.writeObject(object);
    }

    /**
     * Retorna o objeto que foi recebido do servidor.
     * @return
     * @throws IOException
     */
    public Object receiveMove() throws IOException {
        try {
            return objectInputStream.readObject();
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }
    }
}