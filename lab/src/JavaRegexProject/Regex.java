package JavaRegexProject;

public class Regex {
  private static int nextId = 0;
  private int id;
  private String pattern;
  private String description;
  private int rating;

  public Regex(String pattern, String description) {
    this.pattern = pattern;
    this.description = description;
    this.rating = 0;
    this.id = nextId++;
  }

  public int getId() {
    return id;
  }

  public String getPattern() {
    return pattern;
  }

  public String getDescription() {
    return description;
  }

  public int getRating() {
    return rating;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setRating(int rating) {
    this.rating += rating;
  }

  @Override
  public String toString() {
    return "id = " + id + " pattern = " + pattern + " description = " + description + " rating = " + rating;
  }
}
