package model.Class.transaction;

public class GoPay {
    private double saldo;
    private double coins;
    private int walletId;

    public GoPay() {
    }

    public GoPay(int walletId, double saldo, double coins) {
        this.walletId = walletId;
        this.saldo = saldo;
        this.coins = coins;
    }

    public int getWalletId() {
        return walletId;
    }

    public void setWalletId(int walletId) {
        this.walletId = walletId;
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
