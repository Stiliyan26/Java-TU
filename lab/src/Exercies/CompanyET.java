package Exercies;

public class CompanyET extends Company {
    private String owner;
    private double initialCapital;
    private double actualCapital;

    public CompanyET(String name, String date, String bulstat, String owner, double initialCapital, double actualCapital) {
        super(name, date, bulstat);
        this.owner = owner;
        this.initialCapital = initialCapital;
        this.actualCapital = actualCapital;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public double getInitialCapital() {
        return initialCapital;
    }

    public void setInitialCapital(double initialCapital) {
        this.initialCapital = initialCapital;
    }

    public double getActualCapital() {
        return actualCapital;
    }

    public void setActualCapital(double actualCapital) {
        this.actualCapital = actualCapital;
    }

    double profitCalculation() {
        return actualCapital - initialCapital;
    }
}
