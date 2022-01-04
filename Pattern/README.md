# 테스트 주도 개발 패턴

기본적인 전략에 관한 질문에 답해야한다.
- 테스트 한다는 것은 무엇을 뜻하는가?
- 테스트를 언제 해야할 것인가?
- 테스트할 로직을 어떻게 고를 것인가?
- 테스트할 데이터를 어떻게 고를 것인가?

## 격리된 테스트

- 테스트는 충분히 빨라서 **직접, 자주 실행**할 수 있게 만들자
- 앞 부분에서 실패한 테스트가 다음 테스트에 영향을 끼치지 않아야 한다
  - 테스트 **실행 순서에 독립적**이여야 한다.
  - 결과적으로 시스템이 **_응집도는 높고 결합도는 낮은 객체의 모음_** 으로 구성되도록 유도한다.

## 테스트 목록

- 구현해야 할 필요가 있는 모든 오퍼레이션의 사용 예를 적는다.
- 존재하지 안흔 오퍼레이션에 대해서는 해당 오퍼레이션의 널버전을 리스트에 적는다.
- 작업을 끝내기 전 반드시 해야할 리팩토링 목록을 적는다.

> 테스트를 한번에 다 만들지 않는 이유
>- 한번에 만든 테스트는 리팩토링에 대해서 관성을 가지는 경우가 생김
>  - 모든 테스트들에 대해 리팩토링을 일일히 해주는 귀찮은 상황이 생기면 안하는 경우가 생길 수 있다.
>- 열 개의 테스트가 전부 실패한다면 모든 테스트를 성공시키는데 오랜 시간이 걸릴 수 있다.
>  - 즉, 오랫동안 실패한 상태로 두는 것보단 단계적이고 빠른 속도로 성공을 확인하는 것이 적절하다.

## 단언 우선(Assertion)

단언을 제일 먼저 쓰고 시작하자
- 시스템의 경우, 완료된 시스템이 어떤 결과를 낼거라는 걸 정하고 시작하는 것이다.
- 메소드의 경우, 기능이 완료되면 통과할 수 있는 테스트 부터 작성하자.
- 테스트를 경우, 테스트가 완료되었을 때, 통과해야 되는 **_assertion_** 부터 작성한다.

## 테스트 데이터
- 테스트를 읽을 때 쉽고 따라가기 좋을 만한 데이터를 사용하자.
- 테스트 데이터를 사용할 때, 동일한 상수 쓰지말자
<br/> ex) 1+1, 2+2는 상관없지만, 인자의 순서가 뒤집히는 경우를 상정.
- 실제 데이터는 다음 경우에 사용
  - 실제 실행을 통해 수집한 결과를 이용해 실시간 시스템을 테스트 하는 경우.
  - 예전 시스템의 출력과 현재 시스템의 출력을 비교하는 경우(병렬 테스팅).
  - 리팩토링 한 후와 기존 결과가 동일한지 확인.

---

# 테스팅 패턴

## 자식 테스트

큰 테스트 케이스를 작성할 시
- 큰 테스트 케이스에서 실패하는 작은 테스트 케이스를 작성.
- 작은 테스트 케이스가 실행되도록 코드 작성.
- 이 후 큰 테스트 케이스를 추가

## 모의 객체

비용이 많이 들거나 복잡한 리소스에 의존하는 객체 테스트 시
- 상수를 반환하게 하는 속임수 객체를 생성
- 복잡한 객체 실행의 자원을 아끼고 결과에 대한 견고함을 얻을 수 있음
- 가독성이 증진되며, 설계의 커플링이 감소

## 셀프 션트(self shunt)

어떤 객체가 다른 객체와 제대로 메세지를 주고 받는지 확인할 때
- 객체가 원래의 대상이 아닌, 테스트 케이스와 연결
- 테스트 케이스가 구현할 인터페이스르를 위한 인터페이스 추출이 필요

## 크래시 테스트 더미

호출되지 않을 것 같은 에러코드 테스트
- 실제 작업을 수행하는 것이 아닌 예외만을 발생시키는 특수 객체 생성
- 수 많은 에러 상황에서는 작동하길 원하는 부분에 대해서만 진행
- Java의 익명 내부 클래스(Anonymous inner class)를 이용하면 원하는 대상 메소드에 편리하게 오류를 발생 시킬 수 있음

---

# 빨간 막대 패턴

- 테스트를 언제 어디에 작성할 것인가?
- 테스트 작성을 언제 멈출지? 에 대한 패턴.

## 한 단계 테스트

다음 테스트를 고를 때 무엇을 기준으로 할 것인가? <br/>
&gt; **새로운 무언가**를 가르쳐 주며, **구현할 수 있다는 확신**이 드는 테스트를 고를 것.

상향식, 하향식 둘다 TDD의 프로세스를 효과적으로 설명할 수 없다. <br/>
&gt; 이러한 수직적 메타포는 프로그램이 시간에 따라 어떻게 변해 가는지에 대한 단순화 시각화 일 뿐.

따라서, 우리는 ***아는 것에서 모르는 것으로*** 방향성을 가져야 한다.

## 시작 테스트

어떤 테스트를 먼저 시작하는게 좋을까? <br/>
&gt; 오퍼레이션이 **아무 일도 하지 않는 경우**를 먼저 테스트하자!

시작으로 현실적인 테스트를 하나 작성한다면 아래 문제에 대한 해결책을 찾을 수 있다.
- 이 오퍼레이션을 어디에 두어야하나?
- 적절한 입력 값은 무엇인가?
- 입력에 대한 적절한 출력이 무엇인가?

우리는 테스트, 실행, 리팩토링의 순환을 간결하고 신속하게 가져가야하므로, **쉬운 입력과 출력**을 사용해 각 
단계의 간격을 줄일 수 있다.

```java
Socket socket = new Socket();
String msg = "hello";
socket.write(msg);
AssertEquals(msg, socket.read);
```

## 설명 테스트

자동화된 테스트를 널리쓰이게 하고싶다면? <br/>
&gt; 테스트를 통해 설명을 요청하고 테스트를 통해 설명하라!

## 학습 테스트

외부에서 만든 소프트웨어에 대한 테스트를 작성해야할 때는 어떨까?
- 바로 사용하는 대신 API가 우리 예상대로 실행된다는 것을 확인할 만한 테스트를 만들자
- 우리가 제대로 이해했다면 간단한 테스트가 통과될 것이며 우리는 그 API를 사용하면 된다.
- 또한 패키지의 새로운 버젼이 업데이트 된다면, 기존 API 테스트가 통과되는지 확인해 언제나 어플리케이션에 제대로 동작함을 보장할 수 있다.

## 회귀 테스트

시스템 장애가 보고될 경우에 우리가 해야할 일은
1. 장애로 인해 실패하는 테스트를 작성
2. 통과할 경우엔 장애가 수정되었다고 볼 수 있는 테스트를 작성

---

# 초록 막대 패턴

- 코드가 테스트를 통과하게 만들기 위해 사용하는 패턴
- 비록 코드에 악취가 가득할지라도

## 가짜로 구현하기

실패하는 테스트를 만든 후 첫 번째 구현은 ***상수***를 반환하게 하자!
- 일단 테스트가 통과하면 단계적으로 상수를 변수로 사용하는 수식으로 변형.
- 무언가 돌아가는건 그렇지 않은 것보단 무조건 좋다
- 코드 구조가 완성되지 않았지만, 일단 도달하면 동작하는 것을 보장하기 때문이다.
```java
return "0 run, 0 failed";
return String.format("0 run, %d failed", failCount);
return String.format(%d run, %d failed", runCount, failCount);
```

## 삼각측량

추상화 과정을 테스트할 때 보수적으로 할 수 있는 방법은, **예시가 두개 이상일 때만 추상화**하는 것이다.
- 삼각측량의 장점은 규칙이 명확하다는 것이다.
- 두개의 assertion이 있고 하나의 함수에 대해 올바른 추상화가 된다면, 반드시 하나를 삭제할 수 있다.]
```java
public void testSum(){
    assertEquals(4, plus(3, 1));
}
public int plus(int augend, int addend){
    return 4;
}
```
```java
public void testSum(){
    assertEquals(4, plus(3, 1));
    assertEquals(7, plus(4, 3));
}
public int plus(int augend, int addend){
    return augend + addend;
}
```

## 명백한 구현

단순한 연산은 그냥 구현해 버려라.
- 사실 plus와 같은 단순한 구현은 굳이 가짜로 구현하기나 삼각측량이 필요없을 수 있다.
- 그렇기에 그냥 명백한 구현을 통해 구현을 하는 것으로 작업을 진행한다.
- 하지만 **제대로 동작하는**, **깨끗한 코드**는 한번에 이루기 어려울 수 있으며,
명백한 구현은 제대로 동작하지 않는 주기에 빠질 수 있음을 명심하자.
- 그럴때는 다시 제대로 동작하는 코드를 만들고 깨끗한 코드를 만드는 작업 순서로 진행하자.

## 하나에서 여럿으로

객체 컬렉션(Collection)을 다루는 연산에서는 **일단 컬렉션 없이 구현** 후 컬렉션을 사용하자.
- 테스트 케이스에 인자를 추가 또는 변경하며 테스트 케이스에 영향을 주지 않으면서 자유로이 구현을 변경 할 수 있다.
```java
public void testSum(){
    assertEquals(5, sum(5));    
}
public void sum(int value){
    return value;    
}
```
```java
public void testSum(){
    assertEquals(5, sum(5));    
}
public void sum(int[] values){
    return value;    
}
```
```java
public void testSum(){
        assertEquals(5, sum(5));
}
public void sum(int[] values){
        int sum = 0 ;
        for(int i = 0 ; i < values.length(), ++i){
            sum += values[i];
        }
        return sum;
}
```
```java
public void testSum(){
        //assertEquals(5, sum(5));
        assertEquals(12, sum(new int[](5, 7)));
}
public void sum(int[] values){
        int sum = 0 ;
        for(int i = 0 ; i < values.length(), ++i){
            sum += values[i];
        }
        return sum;
}
```

---

# xUnit 패턴

## 단언(Assertion)

테스트가 제대로 동작하는 검사를 위해선 단언을 작성해야한다.
- 단언은 구체적으로 작성하자
```java
assertTrue(rectangle.area() != 0);
assertTrue(rectangle.area() == 50);
```
-코드가 제대로 작동하는지 판단하귀 위해 변수를 사용한다는건 설계를 향상할 수 있는 기회가 있다는 것이다.
```java
Contract contract = new Contract(); // default offered status
contract.begin(); // Running status로 변경
assertEquals(Running.class, contract.status.class); // 변수 사용
assertEquals("2022-01-03", contract.startDate()); // running 되었을 때의 가져올 수 있는 값 사용.
```

## 픽스쳐(Fixture)

공통으로 사용하는 객체들을 생성할 때는 어떻게 할까?
- 테스트 코드에서 객체를 세팅하는 코드는 동일한 경우가 있고 이럴때 **픽스쳐**를 사용한다.
  - JUnit의 BeforeAll, BeforeEach 
- 복사 붙여넣기의 시간 절약 및 인터페이스 변경에 따른 수정량이 적어진다.
- 외부 자원을 해제해야하는 경우 테스트가 끝나기전 반드시 이를 끝내야하므로, **외부 픽스쳐**를 사용한다.
  - Junit의 AfterAll, AfterEach
- [Junit Annotation Link](https://junit.org/junit5/docs/current/user-guide/#writing-tests-annotations)

## 예외 테스트

예외가 발생하는 것이 정상인 경우에 대한 테스트
- OutOfMemory class 및 Crash test dummy 예제 참조

---

# 디자인 패턴

공통의 해결책을 가진 공통의 문제를 발견하는하는 것.<br/>
이 책은 설계를 독립된 단계로 보며, 예제를 이해할 수 있을 정도로만 패턴을 다룬다.

- 커맨드
  - 계산 작업에 대한 호출을 **메시지가 아닌 객체**로 표현한다.
- 값 객체
  - **객체가 생성된 이후에 그 값이 절대 변하지 않게**하여 별칭 문제가 발생하지 않게한다.
- 널 객체
  - 계산 작업의 기본 사례를 객체로 표현한다.
- 템플릿 메서드
  - 계산 **작업의 일관된 순서를 여러 추상 메서드로** 표현한다.
  - 추상 메서드들은 상속을 통해 각자 특별한 작업을 수행하게 구체화한다.
- 플러거블 객체
  - 둘 이상의 구현을 객체를 호출함으로써 다양성을 표현한다.
- 플러거블 셀렉터
  - 객체별로 다른 메서드가 **동적으로 호출**되게하여, 필요없는 **하위 클래스 생성을 피한다**.
- 팩토리 메서드
  - **생성자 대신 메서드를 호출**함으로써 객체를 생성
- 임포스터
  - 현존하는 프로토콜을 가지는 **다른 구현을 추가**해서 시스템에 변이를 도입한다.
- 컴포지트
  - 하나의 객체로 여러 객체의 행위 조합을 표현한다.
- 수집 매개 변수
  - 여러 개의 다른 객체에서 계산된 결과를 모으기 위해 매개 변수를 여러 곳으로 전달한다.

| 패턴       | 테스트 작성 | 리팩토링 |  
|:---------|:------:|:----:|
| 커맨드      |   O    |      |
| 값 객체     |   O    |      |
| 널 객체     |        |  O   |
| 템플릿 메서드  |        |  O   |
| 플러거블 객체  |        |  O   |
| 플러거블 셀렉터 |        |  O   |
| 팩토리메서드   |   O    |  O   |
| 임포스터     |   O    |  O   |
| 컴포지트     |   O    |  O   |
| 수집 매개 변수 |   O    |  O   |

## 커맨드

보통 복잡한 계산 작업 호출은 **비싼 메커니즘**을 필요로 하며, 대부분의 경우 이런 복잡함이 모두에게 요구되지 않는다.
- 메소드로 호출하는 것보다 구체적이고 조작하기 쉬우려면 객체가 해답이 된다.
- 호출 자체를 객체로 표현하고 계산에 필요한 모든 매개 변수들을 초기화한다.
- 호출할 준비가 되면 run과 같은 프로토콜을 이용해 계산을 호출한다
```java
interface Runnable{
    public abstract void run();
}
```

## 값 객체

널리 공유해야 하지만 동일성은 중요하지 않을 때는 어떻게 해야할까? <br/>
 &gt; 객체가 생성될 때 객체의 상태를 설정하고 절대 변할 수 없도록 하자.

고전적인 별칭문제로, 두 객체가 A 객체에 대한 참조를 공유하고 있을 때, 한 객체가 A 객체의 상태를 변화시킨다면 문제가 발생할 수 있다. <br/>
이 방법의 해결을 위해 다음 방법이 있다.
1. 의존하는 객체에 대한 참조를 외부로 알리지 않고 복사본을 제공.
  - 수행 시간이나 메모리 공간 측면에서 비싼 해결책
  - 공유 객체의 상태 변화를 공유받을 수 없음.

2. 옵저버 패턴을 사용해 객체의 상태가 변하면 통지 받는 방법
  - 제어 흐름을 이해하기 어렵게 할 수 있음
  - 의존성을 설정하고 제거하기위한 로직이 지저분해질 위험 
   
3. 객체를 덜 객체답게 취급하는 방법(값 객체)
  - 별칭 문제는 **시간의 흐름에 따라 객체가 변화하는 문제**에 대한 것이다.
  - 따라서 시간의 흐름에 따라 객체가 **변하지 않는 것을 보장**한다면, 참조를 어디든 넘겨줄 수 있다.
  - 값 객체는 동등성을 구현해야하며, 암묵적으로 해싱을 구현해야한다.
    - 둘이 서로 같은 객체가 아니라면 이 둘은 동등한 것이 아니라 다른 것이다.
    - 5달러 지폐가 두 장이 각각 존재하더라도 본질적으로 두개는 모두 5달러로 같은 것이여한다.
    - Money package의 fran과 dolloar 예제 참조
  - 교차하거나 합해지는 모양, 단위 값, 기호대수학 등에 대해 값 객체를 시도해보자.

## 널 객체

객체의 **특별한 상황**을 표현할 때 새로운 객체를 만든다.
- 이 객체는 정상적인 상황을 나타내는 객체와 **동일한 인터페이스**를 갖는다(또는 하위 클래스)
- 조건문 한 줄을 제거하기 위해 널 객체를 도입할 수 있지만 같은 인터페이스르 만들어줘야하므로, 코드량이 늘어날 수 있다.
- 예시는 readOnly를 설정할 때 SecurityManager를 확인하는 조건문을 LaxSecurity라는 널 객체를 통해 표현하는 것이다.
```java
// File.java
public boolean setReadOnly(){
        SecurityManager guard = System.getSecurityManager();
        if(guard != null){ //null 인 경우를 LaxSecurity null객체로 대체한다.
            guard.canWrite(path);
        }
        return fileSystem.setReadOnly(this);
}
```
```java
// LaxSecurity.java
public void canWrite(String path){
}
```
```java
// SecurityManger.java
public static SecurityManager getSecurityManager(){
    return security == null ? new LaxSecurity() : security;
}
```
```java
// File.java
public boolean setReadOnly(){
        SecurityManager guard = System.getSecurityManager();
        guard.canWrite(path);
        return fileSystem.setReadOnly(this);
}
```

## 템플릿 메서드

작업 순서는 변하지 않지만 미래의 작업에서 실제 구현을 통해 개선 가능성을 열어둬야할 경우 사용한다.
다른 메서드들을 호출하는 내용으로만 이루어진 메서드를 만든다.
- 작업의 순서는 상위 클래스에서 정하고 다른 메서드를 호출하는 내용으로만 이루어진 메서드를 만든다.
- 하위 크래스는 이 각각의 메서드를 상속받아 서로 다른 방식으로 구현한다.
- 한 가지 문제는 하위 클래스를 위한 기본 구현을 제공할지 정하는 것이다.
- 보통 템플릿 메서드는 초기 설계보다 구현 중 경험에 의해 변경되는 경우가 많다.
  - 따라서 리팩토링 시, 상세한 구현부분과 진짜로 변하는 부분을 분리하는 작업이 중요하다.
```java
// TestCase.java
public void runBare() throws Throwable{
    setUp();
    try{
        runTest();
    } finally {
        tearDown();
    }
}
```

## 플러거블 객체

어떤 **상태나 객체의 변화**를 표현하는 확인하는 가장 간단한 방법은 **조건문**을 사용하는 것이다.
하지만, 명시적인 조건문을 사용하게 되면 조만간 수많은 조건문들이 퍼져나가게 되고 가독성과 성능저하로 이어질 수 있다.<br/>
따라서, 조건문이 반복되는 상황에서 플러거블 객체를 활요할 수 있다.
```java
Figure selected;
public void mouseDown(){
    selected = findFigure();
    if(selected != null){
        select(selected);
    }
}

public void mouseMove(){
        if(selected != null){
            move(selected);
        } else {
            moveSelectionRectangle();
        }
}

public void mouseUp(){
        if(selected == null){
            selectAll();
        }
}
```
```java
SelectionMode mode;
public void mouseDown(){
    selected = findFigure();
    if(selected != null){
        mode = SingleSelection(selected);
    } else{
        mode = MultipleSelection();
    }
}

public void mouseMove(){
    mode.mouseMove();
}

public void mouseUp(){
    mode.mouseUp();
}
```
중복 조건문을 플러거블 객체인 SelectionMode로 해결하는 예제.
- SelectionMode는 SingleSelection과 MultipleSelection의 두가지 구현을 갖는다.
- Java의 경우 두 플러거블 객체가 동일한 인터페이스를 구현해야한다.