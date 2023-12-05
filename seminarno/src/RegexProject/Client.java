package RegexProject;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {
  public static void main(String[] args) {
    try (
      Socket server = new Socket("localhost", 8080);
      Scanner console = new Scanner(System.in);
      Scanner sc = new Scanner(server.getInputStream());
      PrintStream out = new PrintStream(server.getOutputStream());
    ) {
      userMenu(console, sc, out);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private static void userMenu(Scanner console, Scanner sc, PrintStream out) {
    while (true) {
      //"Enter `create`, `search` or `end`: "
      System.out.println(sc.nextLine());

      //Enter choice
      out.println(console.nextLine());

      String next = sc.nextLine();
      System.out.println(next);
      if (next.equals("end")) {
        return;
      } else if (next.equals("create")) {
        out.println(console.nextLine());
      }
    }
  }
}
