package Class_1_2;

import edu.princeton.cs.algs4.Date;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/************************************************
 * @description
 * @author jtchen
 * @date 2020/11/23 16:20
 * @version 1.0
 ************************************************/
public class Transaction implements Comparable<Transaction> {
    private final String who;      // customer
    private final Date when;     // date
    private final double amount;

    public Transaction(String who, Date when, double amount) {
        if (Double.isNaN(amount) || Double.isInfinite(amount))
            throw new IllegalArgumentException("Amount cannot be NaN or infinite");
        this.amount = amount;
        this.when = when;
        this.who = who;
    }

    public String getWho() {
        return who;
    }

    public Date getWhen() {
        return when;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public int compareTo(Transaction that) {
        return Double.compare(amount, that.getAmount());
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "who='" + who + '\'' +
                ", when=" + when +
                ", amount=" + amount +
                '}';
    }

    @Override
    public boolean equals(Object that) {
        if (that == this) return true;
        if (that == null) return false;
        if (that.getClass() != this.getClass()) return false;
        Transaction tmp = (Transaction) that;
        return amount == tmp.getAmount()
                && when.equals(tmp.getWhen())
                && who.equals(tmp.getWho());
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = 31 * hash + who.hashCode();
        hash = 31 * hash + when.hashCode();
        hash = 31 * hash + ((Double) amount).hashCode();
        return hash;
    }

}
