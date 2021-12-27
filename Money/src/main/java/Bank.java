public class Bank {
    public Money reduce(Expression source, String to) {
        return source.reduce(to);
     /*
        if (source instanceof Money) {
            return ((Money) source).reduce(to);
        }
        Sum sum = (Sum) source; //casting
//        int amount = sum.augend.amount + sum.addend.amount; // access public memeber variable
        return sum.reduce(to);
    */
    }
}
