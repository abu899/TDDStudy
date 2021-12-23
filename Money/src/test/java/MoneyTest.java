import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/*
    TODO:
        $5 + 10CHF = $10(환율이 2:1일 경우)
        Money 반올림
        Equal null
        Equal object
        hasCode()
    COMPLETE:
        amount를 private로
        $5 * 2 = $10
        Dollar 부작용
        5CHF * 2 = 10CHF


 */
class MoneyTest {

    @Test
    void multiplicationTest(){
        //$5 * 2 = $10
        //Dollar의 부작용
        Dollar five = new Dollar(5);
        five.timesDirectToAmount(2);
        assertEquals(10, five.amount);
        five.timesDirectToAmount(3);
        assertNotEquals(15, five.amount);
    }

    @Test
    void multiplicationWithoutSideEffectTest(){
        Dollar five = new Dollar(5);
//        Dollar product = five.times(2);
        assertEquals(new Dollar(10), five.times(2));
//        product = five.times(3);
        assertEquals(new Dollar(15), five.times(3));
    }

    @Test
    void equalityTest(){
        //equals
        assertEquals(new Dollar(5), new Dollar(5));
        assertNotEquals(new Dollar(5), new Dollar(6));
    }

    @Test
    void francMultiplicationTest(){
        Franc five = new Franc(5);
        assertEquals(new Franc(10), five.times(2));
        assertEquals(new Franc(15), five.times(3));
    }
}
