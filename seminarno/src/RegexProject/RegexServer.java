package RegexProject;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RegexServer {
  private final String filePath = "lab/src/RegexProject/patterns.txt";
  private final String separator = ", ";
  private final List<String> writtenRegexList;

  public RegexServer() {
    this.writtenRegexList = new ArrayList<>();
  }

  private static <T> String Stringify(T value) {
    return String.valueOf(value);
  }

  public void writeRegexListToFile(List<Regex> listOfRegex) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
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
      throw new IOException();
    }
  }

  public List<Regex> readRegexListFromFile() throws RuntimeException, IOException {
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
            System.out.println(pattern + "is already in the file!");
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
    List<Regex> regexFromFileList = readRegexListFromFile().stream()
      .map(regex -> regex.getPattern().equals(regexToUpdate.getPattern()) ? regexToUpdate : regex)
      .collect(Collectors.toList());

    writeRegexListToFile(regexFromFileList);
  }
  public List<Regex> filterRegexByKeyword(String keyword) throws RuntimeException, IOException {
    return readRegexListFromFile().stream()
      .filter(r -> r.getDescription().contains(keyword.trim()))
      .sorted(Comparator.comparingInt(Regex::getRating).reversed())
      .collect(Collectors.toList());
  }
}



