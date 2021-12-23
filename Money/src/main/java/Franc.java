public class Franc {
    private int amount;
    public Franc(int amount) {
        this.amount = amount;
    }

    public Franc times(int multiplier) {
        return new Franc(amount * multiplier);
    }

    public boolean equals(Object object){
        if(object == null){
            return false;
        }

        Franc franc = (Franc) object;
        return amount == franc.amount;
    }
}
