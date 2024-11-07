package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Server {
    private static final int PORT = 2003;
    private static final int CORE_POOL_SIZE = 10;
    private static final int MAX_POOL_SIZE = 100;
    private static final int QUEUE_CAPACITY = 8;
    private static final long KEEP_ALIVE_TIME = 10L;
    public static boolean isShutDown = false;
    public static ArrayList<ClientThread> clientThreads;

    public static void main(String[] args) {
        clientThreads = new ArrayList<ClientThread>();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
                    CORE_POOL_SIZE,
                    MAX_POOL_SIZE,
                    KEEP_ALIVE_TIME,
                    TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(QUEUE_CAPACITY)
            );

            System.out.println("Server started on port " + PORT);

            while (!isShutDown) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);
                ClientThread clientThread = new ClientThread(clientSocket);
                clientThreads.add(clientThread);
                threadPool.submit(clientThread);
            }

            threadPool.shutdownNow();
        } catch (IOException e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}
