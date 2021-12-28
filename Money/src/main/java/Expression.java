/*
    Money와 비슷한 외부프로토콜을 가지지만
    내부 구현은 다른 새로운 객체 (imposter 객체)
    사실은 Money 들의 합을 나타내는 객체
 */
public interface Expression {
    Money reduce(Bank bank, String to);

    Expression plus(Expression addend);
}
