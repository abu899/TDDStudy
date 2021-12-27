public class Dollar extends Money {
    public Dollar(int amount) {
        super(amount, "USD");
    }

    public Dollar(int amount, String currency) {
        super(amount, currency);
    }

    public void timesDirectToAmount(int multiplier) {
        amount *= multiplier;
    }

//    public Money times(int multiplier){
//        return new Money(amount * multiplier, currency);
////        return Money.dollar(amount * multiplier);
//    }
}
