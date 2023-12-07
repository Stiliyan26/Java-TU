package JavaRegexProject;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RegexServer {
  private final String filePath = "lab/src/JavaRegexProject/patterns.txt";
  private final String separator = ", ";
  private final Object mutex;

  public RegexServer() {
    mutex = new Object();
  }

  private static <T> String Stringify(T value) {
    return String.valueOf(value);
  }

  public void saveRegexList(List<Regex> listOfRegex) {
    synchronized (mutex) {
      try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
        for (Regex regex : listOfRegex) {
          if (!isRegexPatternInTxtFile(regex.getPattern())) {
            String line = String.join(separator,
              Stringify(regex.getId()),
              Stringify(regex.getPattern()),
              Stringify(regex.getDescription()),
              Stringify(regex.getRating()));

            writer.write(line);
            writer.newLine();
            writer.flush();
          }
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private void preLoadRegexList() throws IOException {
    File file = new File(filePath);

    if (!file.exists()) {
      boolean isCreated = file.createNewFile();

      if (!isCreated) {
        throw new IOException("Failed to create file: " + filePath);
      }
    }
  }

  public List<Regex> loadRegexList() throws RuntimeException, IOException {
    preLoadRegexList();

    List<Regex> regexList = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String line;

      while ((line = reader.readLine()) != null) {
        String[] regexInfo = line.split(separator);

        if (regexInfo.length == 4) {
          int id = Integer.parseInt(regexInfo[0]);
          String pattern = regexInfo[1];
          String description = regexInfo[2];
          int rating = Integer.parseInt(regexInfo[3]);

          Regex newRegex = new Regex(pattern, description);
          newRegex.setRating(rating);
          newRegex.setId(id);

          regexList.add(newRegex);
        }
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      e.printStackTrace();
      throw new IOException();
    }

    return regexList;
  }

  public boolean isRegexPatternInTxtFile(String pattern) {
    try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
      String textLine;

      while ((textLine = reader.readLine()) != null) {
        String[] regexInfo = textLine.split(separator);

        if (regexInfo.length > 2) {
          String currentPattern = regexInfo[1];

          if (currentPattern.equals(pattern)) {
            return true;
          }
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    return false;
  }

  public void updateRegexFile(Regex regexToUpdate) throws IOException {
    List<Regex> regexFromFileList = loadRegexList().stream()
      .map(regex -> regex.getPattern().equals(regexToUpdate.getPattern()) ? regexToUpdate : regex)
      .collect(Collectors.toList());

    saveRegexList(regexFromFileList);
  }
  public List<Regex> filterRegexByKeyword(String keyword) throws RuntimeException, IOException {
    return loadRegexList().stream()
      .filter(r -> r.getDescription().toLowerCase()
        .contains(keyword.trim().toLowerCase()))
      .sorted(Comparator.comparingInt(Regex::getRating).reversed())
      .collect(Collectors.toList());
  }
}



