import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

/*
    TODO:
        $5 + 10CHF = $10(환율이 2:1일 경우)
        $5 + $5 = $10
        $5 + $5에서 Money 반환하기
        Money 반올림
        Equal null
        Equal object
        hasCode()
        Dollar/Franc 중복
    COMPLETE:
        amount를 private로
        $5 * 2 = $10
        Money 부작용
        5CHF * 2 = 10CHF
        공용 equals
        Franc과 Dollar 비교
        통화?
        공용 times
        FrancMultiplicationTest 지워야하나?
        Bank.reduce(Money)




 */
class MoneyTest {
    @Test
    void multiplicationTest() {
        Money five = Money.dollar(5);
        assertThat(Money.dollar(10), is(five.times(2)));
        assertThat(Money.dollar(15), is(five.times(3)));
    }

    @Test
    void equalityTest() {
        //equals
        assertThat(Money.dollar(5), is(Money.dollar(5)));
        assertThat(Money.dollar(5), is(not(Money.dollar(6))));

        //equals to different currency
        assertThat(Money.franc(5), is(not(Money.dollar(5))));
    }

    @Test
    void currencyTest() {
        assertThat(Money.dollar(1).currency(), is("USD"));
        assertThat(Money.franc(1).currency(), is("CHF"));
    }

    @Test
    void simpleAdditionTest() {
//        Money sum = Money.dollar(5).plus(Money.dollar(5));
//        assertThat(Money.dollar(10), is(sum));

        Money five = Money.dollar(5);
        Expression sum = five.plus(five);
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertThat(reduced, is(Money.dollar(10)));

    }

    @Test
    void plusReturnSumTest(){
        Money five = Money.dollar(5);
        Expression result = five.plus(five);
        Sum sum = (Sum) result;
        assertThat(five, is(sum.augend));
        assertThat(five, is(sum.addend));
    }

    @Test
    void reduceSumTest() {
        Expression sum = new Sum(Money.dollar(4), Money.dollar(3));
        Bank bank = new Bank();
        Money reduced = bank.reduce(sum, "USD");
        assertThat(reduced, is(Money.dollar(7)));
    }

    @Test
    void reduceMoneyTest() {
        Bank bank = new Bank();
        Money reduced = bank.reduce(Money.dollar(1), "USD");
        assertThat(reduced, is(Money.dollar(1)));
    }

}
