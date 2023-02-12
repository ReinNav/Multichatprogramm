import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public static void main(String[] args) throws IOException{
        ServerSocket serverSocket = new ServerSocket(1233);
        Server server = new Server(serverSocket);
        server.startServer();
    }

    public void startServer() {

        try {

            while(!serverSocket.isClosed()) {

                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                System.out.println(clientHandler.getClientUsername() + " has entered the server!");

                Thread thread = new Thread(clientHandler);
                thread.start();

            }
        } catch (IOException e) {

        }

    }

    // Shuts down server if error occurs
    public void closeServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
