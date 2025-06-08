import entities.Account;
import entities.AtmMachine;
import entities.Card;
import enums.TransactionType;
import enums.CashType;

public class Main {
    public static void main(String[] args) {
        AtmMachine atm = AtmMachine.getInstance();
        atm.addMoneyInAtm(CashType.CASH_500, 10); // Rs.5000
        atm.addMoneyInAtm(CashType.CASH_100, 20); // Rs.2000
        System.out.println("ATM is initialised\n");

        // Setup: Add account and card
        Account acc1 = new Account("12345", 10000, "Raghu", 987654321);
        atm.addAccount(acc1);
        Card card1 = new Card(12345, 2506, 789, "Raghu", 1234, acc1);

        // First transaction
        atm.getAtmState().insertCard(atm, card1);
        atm.getAtmState().authenticatePin(atm, 1234);
        atm.getAtmState().selectOperation(atm, TransactionType.WITHDRAWAL);
        atm.getAtmState().performCashWithdrawal(atm, 500); // Should succeed

        System.out.println("\n---- Attempting second withdrawal without reinserting card ----");
        atm.getAtmState().selectOperation(atm, TransactionType.WITHDRAWAL); // Should fail or do nothing if ATM is in idle

        System.out.println("\n---- Second session: Insert card again and withdraw more ----");
        atm.getAtmState().insertCard(atm, card1);
        atm.getAtmState().authenticatePin(atm, 1234);
        atm.getAtmState().selectOperation(atm, TransactionType.WITHDRAWAL);
        atm.getAtmState().performCashWithdrawal(atm, 9500); // Should fail due to insufficient account balance

        System.out.println("\n---- Third session: Withdraw exact remaining amount ----");
        atm.getAtmState().insertCard(atm, card1);
        atm.getAtmState().authenticatePin(atm, 1234);
        atm.getAtmState().selectOperation(atm, TransactionType.WITHDRAWAL);
        atm.getAtmState().performCashWithdrawal(atm, 9500); // Balance was 9500 now, try again

        System.out.println("\n---- Fourth session: Exceeding ATM cash ----");
        atm.getAtmState().insertCard(atm, card1);
        atm.getAtmState().authenticatePin(atm, 1234);
        atm.getAtmState().selectOperation(atm, TransactionType.WITHDRAWAL);
        atm.getAtmState().performCashWithdrawal(atm, 100000); // ATM doesn’t have this much
    }
}
