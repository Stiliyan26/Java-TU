package ex3;

public class DebitAccount implements Payable{
    private float amount;

    public DebitAccount(float amount) throws BankException {
        setAmount(amount);
    }

    @Override
    public float pay() {
        return getAmount();
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) throws BankException {
        if (amount < 0) {
            throw new BankException("Amount should be positive number");
        }
        this.amount = amount;

    }
}
