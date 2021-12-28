import java.util.Hashtable;

public class Bank {
    private Hashtable rates = new Hashtable();
    public Money reduce(Expression source, String to) {
        return source.reduce(this, to);
     /*
        if (source instanceof Money) {
            return ((Money) source).reduce(to);
        }
        Sum sum = (Sum) source; //casting
//        int amount = sum.augend.amount + sum.addend.amount; // access public memeber variable
        return sum.reduce(to);
    */
    }

    int rate(String from, String to) {
        if(from.equals(to)){
            return 1;
        }
        return (int)rates.get(new Pair(from, to));
    }

    public void addRate(String from, String to, int rate) {
        rates.put(new Pair(from, to), rate);
    }
}
