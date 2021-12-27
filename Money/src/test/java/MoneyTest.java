import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.*;

/*
    TODO:
        $5 + 10CHF = $10(환율이 2:1일 경우)
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
}
