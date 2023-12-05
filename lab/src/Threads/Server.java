package Threads;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private List<PrintStream> clients;
    private final Object clientsLock;
    private static final int MAX_CLIENTS = 3;
    private ExecutorService threadPool;

    private class ServerRunnable implements Runnable {
        private final Socket client;

        public ServerRunnable(Socket client) {
            this.client = client;
        }

        @Override
        public void run() {
            try (Scanner in = new Scanner(client.getInputStream());
                 PrintStream out = new PrintStream(client.getOutputStream())) {
                synchronized (clientsLock) {
                    if (clients.size() >= MAX_CLIENTS) {
                        out.println("Chat is full. Try again later.");
                        return;
                    }
                    clients.add(out);
                }

                while (true) {
                    String line = in.nextLine();
                    if (line.contains("quit")) {
                        synchronized (clientsLock) {
                            clients.remove(out);
                            return;
                        }
                    }
                    synchronized (clientsLock) {
                        for (PrintStream c : clients)
                            c.println(line);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Server() {
        clients = new ArrayList<>();
        clientsLock = new Object();
        threadPool = Executors.newFixedThreadPool(MAX_CLIENTS);
    }

    public void start() {
        try {
            ServerSocket serverSocket = new ServerSocket(8080);

            while (true) {
                Socket client = serverSocket.accept();
                if (clients.size() < MAX_CLIENTS) {
                    threadPool.submit(new ServerRunnable(client));
                } else {
                    // Reject the connection when the maximum number of clients is reached.
                    PrintStream rejectionOut = new PrintStream(client.getOutputStream());
                    rejectionOut.println("Chat is full. Try again later.");
                    rejectionOut.close();
                    client.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Server().start();
    }
}