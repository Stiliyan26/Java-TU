package JavaRegexProject;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
  private int clientCout;
  private final RegexServer regexServer;
  private static final int MAX_CLIENTS = 3;
  private ExecutorService threadPool;

  public Server() throws IOException {
    regexServer = new RegexServer();
    threadPool = Executors.newFixedThreadPool(MAX_CLIENTS);
    clientCout = 0;
  }

  private class ServerRunnable implements Runnable {
    private final Socket client;

    public ServerRunnable(Socket client) {
      this.client = client;
    }

    @Override
    public void run() {
      System.out.println("Accepted client.");
      clientCout++;

      try (Scanner in = new Scanner(client.getInputStream());
           PrintStream out = new PrintStream(client.getOutputStream());
      ) {
        userMenu(in, out);
        client.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void start() {
    try {
      System.out.println("Server is listening.");
      ServerSocket serverSocket = new ServerSocket(8080);

      while (true) {
        Socket client = serverSocket.accept();
        //Thread clientThread = new Thread(new ServerRunnable(client));
        //clientThread.start();
        if (clientCout < MAX_CLIENTS) {
          threadPool.submit(new ServerRunnable(client));
        } else {
          PrintStream rejectionOut = new PrintStream(client.getOutputStream());
          rejectionOut.println("Regex server is full. Try again later.");
          rejectionOut.close();
          client.close();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void userMenu(Scanner in, PrintStream out) throws IOException {
    while (true) {
      out.println("Choose between `create`, `search` or `end`:");

      String choice = in.nextLine().trim();

      switch (choice) {
        case "create" -> handleServerCreate(in, out);
        case "search" -> handleServerSearch(in, out);
        case "end" -> {
          clientCout--;
          out.println("Goodbye!");
          return;
        }
      }
    }
  }

  private void handleServerCreate(Scanner in, PrintStream out) throws IOException {
    //Creating regex
    out.println("Enter regex pattern:");
    String pattern = in.nextLine();

    out.println("Enter description:");
    String description = in.nextLine();

    Regex newRegex = new Regex(pattern, description);

    //Creating strings to test
    out.println("Enter strings to test separated with single space - ` `:");
    String[] stringsToTest = in.nextLine()
      .trim()
      .split(" ");
    //Testing strings
    List<Boolean> matchesResult = RegexTester.test(newRegex, stringsToTest);
    out.println("Matching results: " + matchesResult);

    out.println("Enter `yes` if you want to save your regex to the file:");
    String answer = in.nextLine().trim();

    if (answer.equals("yes")) {
      List<Regex> regexList = regexServer.loadRegexList();

      if (!regexList.isEmpty()) {
        int newId = regexList.get(regexList.size() - 1).getId() + 1;
        newRegex.setId(newId);
      } else {
        newRegex.setId(0);
      }

      regexList.add(newRegex);
      regexServer.saveRegexList(regexList);
    }
  }

  private void handleServerSearch(Scanner in, PrintStream out) throws IOException {
    out.println("Enter a keyword: ");
    String keyword = in.nextLine();
    List<Regex> filteredRegexList = regexServer.filterRegexByKeyword(keyword);

    out.println(filteredRegexList.size());

    if (filteredRegexList.isEmpty()) {
      out.println("No matching pattern!");
      return;
    }

    for (Regex regex : filteredRegexList) {
      out.println("Id: " + regex.getId() + " Pattern: " + regex.getPattern());
    }

    out.println("Choose id from the listed once:");

    int id = Integer.parseInt(in.nextLine());

    Optional<Regex> optionalRegexById = filteredRegexList.stream()
      .filter(r -> r.getId() == id)
      .findFirst();

    if (!optionalRegexById.isPresent()) {
      out.println("Pattern with this Id does not exist!");
      return;
    }

    Regex regexById = optionalRegexById.get();
    out.println("Chosed pattern: " + regexById.getPattern());

    out.println("Enter strings to test separated with single space - ` `:");
    String[] stringsToTest = in.nextLine()
      .trim()
      .split(" ");
    //Testing strings
    List<Boolean> matchesResult = RegexTester.test(regexById, stringsToTest);
    out.println("Matching results: " + matchesResult);

    int rating = 0;

    while (true) {
      out.println("Give rating of the regex. Enter 1 or -1:");
      rating = Integer.parseInt(in.nextLine());

      if (rating == 1 || rating == -1) {
        regexById.setRating(rating);
        break;
      }

      out.println("Rating should be 1 or -1!");
    }

    regexServer.updateRegexFile(regexById);

    out.println(regexById.getPattern() + " new rating is " + regexById.getRating());
  }
}
