package RegexProject;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ServerMain {
  private static final RegexServer regexServer = new RegexServer();
  private static final List<Regex> globalRegexList = new ArrayList<>();
  private static final Object mutex = new Object();
  private static ServerSocket server;

  public static void main(String[] args) throws RuntimeException, IOException {
    start();
  }

  private static void start() throws IOException {
    System.out.println("Server listening.");
    server = new ServerSocket(8080);

    while (true) {
      Socket client = server.accept();

      Thread clientThread = new Thread(() -> {
        System.out.println("Accepted client.");

        try (Scanner sc = new Scanner(client.getInputStream());
             PrintStream out = new PrintStream(client.getOutputStream())) {
          userMenu(sc, out);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      });

      clientThread.start();
    }
  }
  private static void userMenu(Scanner sc, PrintStream out) throws IOException {
    final String choiceMessage = "Enter `create`, `search` or `end`: ";

    out.print(choiceMessage);
    String choice = sc.nextLine().toLowerCase();

    while (!choice.equals("end")) {
      switch (choice) {
        case "create" -> handleCreateAction(sc, out);
        case "search" -> handleSearchAction(sc, out);
        default -> out.println("No valid action was chosen!");
      }

      out.print(choiceMessage);
      choice = sc.nextLine().toLowerCase();

      if (!choice.equals("create") && !globalRegexList.isEmpty()) {
        regexServer.writeRegexListToFile(globalRegexList);
        globalRegexList.clear();
      }
    }
  }

  private static List<Boolean> testRegex(Regex regex, Scanner sc, PrintStream out) {
    out.print("Enter strings to test separated with single space - ` ` : ");
    String line = sc.nextLine();
    String[] stringsToTest = line.split(" ");

    return RegexTester.test(regex, stringsToTest);
  }

  //Create
  private static void handleCreateAction(Scanner sc, PrintStream out) {
    Regex currentRegex = createRegex(sc, out);

    List<Boolean> matches = testRegex(currentRegex, sc, out);
    out.println("Matching results: " + matches);

    out.print("Enter `yes` if you want to save your regex to the file: ");
    String shouldSave = sc.nextLine().toLowerCase();

    if (shouldSave.equals("yes")) {
      if (!globalRegexList.contains(currentRegex)) {
        globalRegexList.add(currentRegex);
        out.println("Saved " + currentRegex);
      } else {
        out.println("It was not saved because pattern already exists!");
      }
    }
  }

  private static Optional<Regex> findRegexByPattern(String pattern) {
    return globalRegexList.stream()
      .filter(existingRegex -> existingRegex.getPattern().equals(pattern))
      .findFirst();
  }

  private static Regex createRegex(Scanner sc, PrintStream out) {
    out.print("Enter regex pattern: ");
    String pattern = sc.nextLine();

    Optional<Regex> regex = findRegexByPattern(pattern);

    if (regex.isPresent()) {
      out.println("Pattern already exists!");
      out.println("I am giving you the existing one.");

      return regex.get();
    }

    out.print("Enter description: ");
    String description = sc.nextLine();

    return new Regex(pattern, description);
  }

  //Search
  private static void handleSearchAction(Scanner sc, PrintStream out) throws IOException {
    out.print("Enter a keyword: ");
    String keyword = sc.nextLine();

    Optional<Regex> optionalRegexById = getRegexByKeywordAndIdFromFile(keyword, sc, out);

    if (optionalRegexById.isPresent()) {
      Regex regexById = optionalRegexById.get();

      List<Boolean> matches2 = testRegex(regexById, sc, out);
      out.println("Matching results: " + matches2);

      updateRating(regexById, sc, out);
    } //add else add message
  }

  private static Optional<Regex> getRegexByKeywordAndIdFromFile(String keyword, Scanner sc, PrintStream out) throws RuntimeException, IOException {
    List<Regex> regexList = regexServer.filterRegexByKeyword(keyword);

    if (regexList.isEmpty()) {
      out.println("No matching regex found for the given keyword.");

      return Optional.empty();
    }

    for (Regex regex : regexList) {
      out.println("Id: " + regex.getId() + " Pattern: " + regex.getPattern());
    }

    out.print("Choose id from the listed once: ");
    int chosenId = Integer.parseInt(sc.nextLine());

    return regexList.stream()
      .filter(r -> r.getId() == chosenId)
      .findFirst();
  }

  private static void updateRating(Regex regexById, Scanner sc, PrintStream out) throws IOException {
    out.print("Give rating of the regex. Enter 1 or -1: ");
    int rating = Integer.parseInt(sc.nextLine());

    if (rating != 1 && rating != -1) {
      out.println("Rating is invalid!");
      return;
    } else if (regexById.getRating() == 0 && rating == -1) {
      out.println("Rating can't be below 0");
      return;
    }

    regexById.setRating(rating);
    regexServer.updateRegexFile(regexById);
    out.println("New rating: " + regexById.getRating());
  }
}
