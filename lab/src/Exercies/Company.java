package Exercies;

public class Company {
    private String name;
    private String date;
    private String bulstat;

    public Company(String name, String date, String bulstat) {
        this.name = name;
        this.date = date;
        setBulstat(bulstat);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBulstat() {
        return bulstat;
    }

    public void setBulstat(String bulstat) {
        if (bulstat.length() != 10) {
            System.out.println("Bulstat should be at 10 characters long!");
        }
        this.bulstat = bulstat;
    }
}
