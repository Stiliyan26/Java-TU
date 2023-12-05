package JavaRegexProject;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    start();
  }

  private static void start() {
    Socket server = null;
    Scanner console = null;
    Scanner in = null;

    try {
      server = new Socket("localhost", 8080);

      console = new Scanner(System.in);

      in = new Scanner(server.getInputStream());
      PrintStream out = new PrintStream(server.getOutputStream());

      userMenu(console, in, out);
    }
    catch (IOException e) {
      throw new RuntimeException(e);
    }
    finally {
      if (server != null) {
        try {
          server.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
      }

      if (console != null) {
        console.close();
      }

      if (in != null) {
        in.close();
      }
    }
  }

  private static void userMenu(Scanner console, Scanner in, PrintStream out) {
    while (true) {
      String entryMessage = in.nextLine();

      if (entryMessage.equals("Regex server is full. Try again later.")) {
        System.out.println(entryMessage);
        return;
      }

      //Choose between `create` or `search`:
      System.out.println(entryMessage);

      //Enter choice
      String choice = console.nextLine().toLowerCase();
      out.println(choice);

      switch (choice) {
        case "create" -> handleClientCreate(console, in, out);
        case "search" -> handleClientSearch(console, in, out);
        case "end" -> {
          System.out.println(in.nextLine());
          return;
        }
      }
    }
  }
  private static void handleClientCreate(Scanner console, Scanner in, PrintStream out) {
    //Enter regex pattern:
    System.out.println(in.nextLine());
    String pattern = console.nextLine();
    out.println(pattern);

    //"Enter description:"
    System.out.println(in.nextLine());
    String description = console.nextLine();
    out.println(description);

    //Enter string to test
    System.out.println(in.nextLine());
    String lineOfStringsToTest = console.nextLine();
    out.println(lineOfStringsToTest);

    //Result of strings to test
    System.out.println(in.nextLine());

    //Saving regex confirmation msg
    System.out.println(in.nextLine());
    String answer = console.nextLine().toLowerCase();
    out.println(answer);
  }

  private static void handleClientSearch(Scanner console, Scanner in, PrintStream out) {
    //Enter keyword:
    System.out.println(in.nextLine());
    String keyword = console.nextLine();
    out.println(keyword);
    //Count of filtered
    int countOfFilteredRegexList = Integer.parseInt(in.nextLine());

    if (countOfFilteredRegexList == 0) {
      //Pattern not found!
      System.out.println(in.nextLine());
      return;
    }

    for (int i = 0; i < countOfFilteredRegexList; i++) {
      //Patterns matching the keyword result
      System.out.println(in.nextLine());
    }
    //Choose id from the listed once
    System.out.println(in.nextLine());
    String id = console.nextLine();
    out.println(id);

    String patternByIdResultMsg = in.nextLine();

    if (patternByIdResultMsg.equals("Pattern with this Id does not exist!")) {
      System.out.println(patternByIdResultMsg);
      return;
    }
    //Chosed pattern result
    System.out.println(patternByIdResultMsg);

    //Enter strings to test separated with single space - ` `:
    System.out.println(in.nextLine());
    String lineOfStringsToTest = console.nextLine();
    out.println(lineOfStringsToTest);

    //Matching results:
    System.out.println(in.nextLine());

    while (true) {
      //Give rating of the regex. Enter 1 or -1:
      System.out.println(in.nextLine());
      String rating = console.nextLine().trim();
      out.println(rating);

      if (rating.equals("1") || rating.equals("-1")) {
        break;
      }
      //Rating should be 1 or -1!
      System.out.println(in.nextLine());
    }
    //New rating message
    System.out.println(in.nextLine());
  }
}
