package ex3;

public class Main {
    public static void main(String[] args) {
       try {
           Payable[] creditAccounts = new Payable[2];

           creditAccounts[0] = new CreditAccount(50, 12, 0.1f);
           creditAccounts[1] = new DebitAccount(50);

           for (Payable p : creditAccounts) {
               System.out.println(p.pay());
           }
       } catch (BankException exception) {
           exception.printStackTrace();
       }
    }
}
