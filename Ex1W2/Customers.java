package Demo.Ex1W2;

public class Customers {
    private int ID;
    private String Username;
    private int Balance;

    public Customers(int ID, String username, int balance) {
        this.ID = ID;
        Username = username;
        Balance = balance;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getBalance() {
        return Balance;
    }

    public void setBalance(int balance) {
        Balance = balance;
    }

}
