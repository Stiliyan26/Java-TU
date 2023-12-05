package RegexProject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTester {
  public static List<Boolean> test(Regex regex, String[] strings) {
    List<Boolean> results = new ArrayList<>();
    Pattern pattern = Pattern.compile(regex.getPattern());
    Matcher matcher;

    for (String strToTest : strings) {
      matcher = pattern.matcher(strToTest);

      if (matcher.matches()) {
        results.add(true);
      } else {
        results.add(false);
      }
    }

    return results;
  }
}
