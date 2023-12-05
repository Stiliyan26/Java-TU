package ex3;

public class CreditAccount extends DebitAccount {
    private int months;
    private float interest; // Lihva

    public CreditAccount(float amount, int months, float interest) throws BankException {
        super(amount);
        this.months = months;
        this.interest = interest;
    }

    @Override
    public float pay()  {
        float slojnaLihva = getAmount();
        for (int i = 0; i < months; i++) {
            slojnaLihva += getAmount() + getAmount() * interest;
        }

        return slojnaLihva;
    }
}
