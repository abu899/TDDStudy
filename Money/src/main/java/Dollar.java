public class Dollar extends Money {
    public Dollar(int amount) {
        this.amount = amount;
    }

    public void timesDirectToAmount(int multiplier) {
        amount *= multiplier;
    }

    public Dollar times(int multiplier){
        return new Dollar(amount * multiplier);
    }
}
