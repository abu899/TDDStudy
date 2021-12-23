public class Dollar {
    public int amount;
    public Dollar(int amount) {
        this.amount = amount;
    }

    public void timesDirectToAmount(int multiplier) {
        amount *= multiplier;
    }

    public Dollar times(int multiplier){
        return new Dollar(amount * multiplier);
    }

    public boolean equals(Object object){
        if(object == null){
            return false;
        }

        Dollar dollar = (Dollar) object;
        return amount == dollar.amount;
    }
}
