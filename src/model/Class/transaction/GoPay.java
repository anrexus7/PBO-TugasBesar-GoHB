package model.Class.transaction;

public class GoPay {
    private double saldo;
    private double coins;

    public GoPay(double saldo, double coins) {
        this.saldo = saldo;
        this.coins = coins;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }
}
