class Money implements Expression{
    protected int amount;
    protected String currency;

    Money(int amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money dollar(int amount) {
        return new Money(amount, "USD");
    }

    public static Money franc(int amount) {
        return new Money(amount, "CHF");
    }

    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        Money money = (Money) object;
        return amount == money.amount &&
                currency().equals(money.currency());
    }

    Money times(int multiplier) {
        return new Money(amount * multiplier, currency);
    }

    String currency() {
        return currency;
    }

    public String toString() {
        return amount + " " + currency;
    }

    public Expression plus(Money money) {
        return new Sum(this, money);
    }

    public Money reduce(String to) {
        return this;
    }
}
