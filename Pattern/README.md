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
- 템플릿 메소드
  - 계산 **작업의 일관된 순서를 여러 추상 메소드로** 표현한다.
  - 추상 메소드들은 상속을 통해 각자 특별한 작업을 수행하게 구체화한다.
- 플러거블 객체
  - 둘 이상의 구현을 객체를 호출함으로써 다양성을 표현한다.
- 플러거블 셀렉터
  - 객체별로 다른 메소드가 **동적으로 호출**되게하여, 필요없는 **하위 클래스 생성을 피한다**.
- 팩토리 메소드
  - **생성자 대신 메소드를 호출**함으로써 객체를 생성
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
| 템플릿 메소드  |        |  O   |
| 플러거블 객체  |        |  O   |
| 플러거블 셀렉터 |        |  O   |
| 팩토리메소드   |   O    |  O   |
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

## 템플릿 메소드

작업 순서는 변하지 않지만 미래의 작업에서 실제 구현을 통해 개선 가능성을 열어둬야할 경우 사용한다.
다른 메소드들을 호출하는 내용으로만 이루어진 메소드를 만든다.
- 작업의 순서는 상위 클래스에서 정하고 다른 메소드를 호출하는 내용으로만 이루어진 메소드를 만든다.
- 하위 크래스는 이 각각의 메소드를 상속받아 서로 다른 방식으로 구현한다.
- 한 가지 문제는 하위 클래스를 위한 기본 구현을 제공할지 정하는 것이다.
- 보통 템플릿 메소드는 초기 설계보다 구현 중 경험에 의해 변경되는 경우가 많다.
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

## 플러거블 셀렉터

인스턴스 별로 서로 다른 메소드를 동적으로 호출하려면? <br/>
&gt; 메소드의 이름을 저장하고 있다가 그 이름에 해당하는 메소드를 동적으로 호출. <br/>
- 만약 단순한 메소드 하나만 구현하는 하위 클래스가 여러개 있다면 어떻게 해야할까?
- 이런 작은 변이를 상속으로 하기에는 너무 무거운 방법이다.
```java
abstract class Report{
    abstract void print();
}

class HTMLReport extends Report{
    void print() {}
}

class XMLReport extends Report{
    void print() {}
}
```
- 한가지 대안은 switch 문을 갖는 하나의 클래스를 이용하는 것으로, 필드의 값에 따라 다른 메소드를 호출하면 된다.
- 하지만, 메소드의 이름이 세곳에 나뉘어 존재한다는 문제가 있다
  - 인스턴스 생성하는 곳
  - swtich 문
  - 메소드 자체
- 새로운 종류의 출력을 추가할 때마다, 출력 메소드와 switch 문을 추가해줘야한다.
```java
abstract class Report{
    String printMessage;
    Report(String printMessage){
        this.printMessage = printMessage;
    }
    void print(){
        switch(printMessage){
          case "printHTML":
              printHTML();
              break;
          case "printXML":
              printXML();
              break;
        }
    }
    void printHTML(){}
    void printXML(){}
}
```
- 다른 대안으로는 플러거블 셀렉터을 사용하는 것이다.
- **리플렉션**을 이용한 이 방법으로 동적으로 메소드를 호출 할 수 있다.
- 물론 report를 생성하는 곳과 출력 메소드의 이름간의 의존관계가 있지만 switch문을 해결할 수 있다.
- 하지만 플러거블 셀렉터의 과용에 대해 주의해야한다
- 가장 큰 문제인 디버깅시 코드 추적의 어려움이 존재하기에 확실히 직관적인 상황에서 정리를 위한 용도로 사용되어야 한다.
```java
abstract class Report{
  String printMessage;
  Report(String printMessage){
    this.printMessage = printMessage;
  }
  void print(){
      Method runMethod = getCalss().getMethod(printMessage, null);
      renMethod.invoke(this, new Class[0]);
  }
  void printHTML(){}
  void printXML(){}
}
```

## 팩토리 메소드

새 객체를 만들 때 유연성을 원하는 경우 **생성자를 쓰는 대신 일반 메소드로 객체를 생성**하는 팩토리 메소드를 사용하자.
- 생성자는 객체를 생성하고 있다는 점을 명시적으로 보여주지만, 표현력과 유연함이 떨어진다.
- Moeny test를 작성할 때, Money 대신 Dollar를 도입하고자 했지만 Dollar 객체 생성에 대한 의존성 때문에 어려움이 있었다.
- 그 때, 메소드라는 한 단계의 인디렉션을 추가함으로써 추가적인 코드의 변화 없이 다른 클래스의 인스턴스를 얻는 유연함을 얻을 수 있었다.
```java
public void multiplicationTest(){
    Dollar five = new Dollar(5);
    assertEquals(new Dollar(10), five.times(2));
}
```
```java
public void multiplicationTest(){
    Dollar five = Money.dollar(5); // indirection
    assertEquals(new Dollar(10), five.times(2));
}

//Money.java
static Dollar dollar(int amount){
    return new Dollar(amount);
}
```
- 팩토리 메소드의 단점은 인디렉션으로, 메소드가 생성자 처럼 생기지 않았지만 내부에 객체 생성이 된다는 사실을 인지해야한다.
- 유연함이 필요할 때만 팩토리 메소드를 사용하고, 그렇지 않다면 생성자를 사용하자.

## 임포스터

기존 코드에서 **새로운 변화**를 도입하려면 기존 객체와 같은 인터페이스를 갖지만 다른 구현을 가진 객체를 도입하자.
TDD 작성 시, 테스트 케이스 하나를 작성하고 새로운 시나리오를 표현해야 하지만 기존 객체 중 어느 것도 내가 원하는걸 표현하지 못할 때,
임포스터가 사용된다.
```java
void rectangleTest(){
    Drawing d = new Drawing();
    d.addFigure(new RectangleFigure(0, 100, 50, 100));
    RecordingMedium brush = new RecordingMedium();
    d.display(brush);
    assertEquals("rectangle 0 10 50 100", brush.log());
}
```
```java
void ovalTest(){
    Drawing d = new Drawing();
    d.addFigure(new OvalFigure(0, 100, 50, 100));
    RecordingMedium brush = new RecordingMedium();
    d.display(brush);
    assertEquals("oval 0 10 50 100", brush.log());
}
```
- oval이라는 것을 표현할 때, 전혀 새로운 객체가 아닌 같은 인터페이스를 가진 임포스터를 사용함으로써, 로직의 수정 없이 사용 가능하다.
- 리팩토링 중 나타나는 임포스터의 두가지
  - 널 객체 : 데이터가 없는 상태를 데이터가 있는 상태와 동일하게 취급
  - 컴포지트 : 객체의 집합을 단일 객체처럼 취급
- 임포스터로 만들 부분을 찾아내는 것은 중복을 제거하는 작업을 통해 유도된다.

## 컴포지트

하나의 객체가 **다른 객체들의 목록의 행위를 조합한 것과 같이 동작**시키기 위한 방법은 무엇일까?
&gt; 객체 집합을 나타내는 객체를 **단일 객체에 대한 임포스터**로 구현한다!
```java
// Transaction.java
class Transaction{
    private Money value;
    Transaction(Money value){
        this.value = value;
    }
}
```
```java
// Account.java
class Account {
    private Transcation transactions[];
    Money balance() {
        Money sum = Money.zero();
        for(int i = 0 ; i < transcations.length; ++i){
            sum = sum.plus(transcations[i].value);
        }
        return sum;
    }
}
```
Transaction은 값을 갖고 Account는 잔액을 갖는다. 만약 고객이 여러 계좌를 가지고 있으며 전체 계좌에 대한
잔액(Balance)을 알고 싶어 한다면?
- 가장 간단한 방법은 OverallAccount라는 Account를 모두 가진 새로운 클래스를 만드는 것.
- 이 경우 Account가 하는 일과 또 다시 중복되므로 좋은 선택은 아니다.
- 그렇다면 Account와 전체 잔액인 Balance가 같은 인터페이스를 가진다면 어떨까?
```java
// Holding.java
interface Holding {
    Money balance();
}
```
```java
// Transaction.java
class Transaction extends Holding {
  private Money value;
  Transaction(Money value){
    this.value = value;
  }
  
  @Override
  Money balance(){
      return value;
  }
}
```
```java
// Account.java
class Account extends Holding {
  private Holding holdings[];
  
  @Override
  Money balance() {
    Money sum = Money.zero();
    for(int i = 0 ; i < holdings.length; ++i){
      sum = sum.plus(holdings[i].balance());
    }
    return sum;
  }
}
```
- Account는 더이상 Transaction이 아닌 Holding의 컴포지트로 만들 수 있다.
- Account는 이제 Account를 담을 수 있는 또다른 Account가 된 것이다.(OverallAccount)
- 실제 세계에서는 transaction에 balance가 존재하지 않지만, 프로그램 설계에서 이러한 개념적 단절은 엄청난 이득이 된다.
  - 예를들어, Folder는 Folder를 포함하고 TestSuite는 TestSuite를 포함하는 등
  - 실세계와는 잘 어울리지 않지만 코드를 단순하게 만들 수 있다.
- 객체의 컬렉션으로 사용하거나 컴포지트로 사용하거나에 대한 기준이 모호하고 적용에 따라 복잡함이 사라짐을 관찰해야한다.

## 수집 매개 변수

여러 객체에 존재하는 결과를 수집할 때 매개 변수로 수집될 객체를 건넬 수 있다.
수집 매개 변수를 전달 받은 메소드들은 수집 매개 변수에 기록을 전달하고 모든 객체의 결과가 수집된다.
일반적인 TestResult의 경우 toString()으로 충분히 결과를 대조할 수 있지만, 결과가 복잡해질 수록 수집 매개 변수에대한 필요성이 증가할 것이다.
```java
String sumPrintingTest() {
    Sum sum = new Sum(Money.dollar(5), Money.franc(7));
    assertEquals("5 USD + 7 CHF", sum.toString());
}
```
```java
class Sum{
    @Override
    String toString() {
        return augend + " + " + addend;
    }
}
```

<br/>

- 단순한 출력에는 문제없지만, 예를 들어 트리형태로 표현한다면 점점 복잡해지기 시작한다.
- 그런 경우 아래와 같은 수집 매개 변수를 이용해 결과를 정리할 수 있다.
```java
String sumPrintingTest() {
    Sum sum = new Sum(Money.dollar(5), Money.franc(7));
    assertEquals("+\n\t5 USD\n\t 7 CHF", sum.toString());
}
```
```java
class Sum{
    @Override
    String toString() {
      IndentingStream writer = new IndentingStream();
      toString(writer);
      return writer.contents();
    }
    
    String toString(IndentingWriter writer) {
        writer.println("+");
        writer.indent();
        augend.toString(writer);
        writer.println();
        addend.toString(writer);
        writer.exdent();
    }
}
```

---

# 리팩토링

## 차이점 일치시키기

비슷해보이는 두 코드를 단계적으로 닮아지게 수정하고 동일해지면 둘을 합치는 것.
- 두 반복문의 구조가 비슷하다.
  - 이 둘을 동일하게 만들고 나서 하나로 합친다
- 조건문에 의해 나눠지는  두 분기의 코드가 비슷하다.
  - 이 둘을 동일하게 만들고 조건문을 제거한다.
- 두 클래스가 비슷하다.
  - 이 둘을 동일하게 만들고 하나를 제거한다.

## 변화 격리하기

객체나 메소드의 일부분만을 바꾸려면, 바꿔야할 부분을 격리하자.
일단 바꿀 부분을 격리하고 바꾸는 작업을 수행하면 작업을 되돌리기도 매우 수월해진다.
- 메소드가 하나 더 명시되는 비용과 하나의 개념이 명시되는 가치의 균형이 중요하다.
  - 예를 들어 findRate()라는 메소드는 단순히 인스턴스 변수의 반환이라고 한다면, 메소드 상태로 두는 것과 인라인시키고 메소드를 삭제하는 것의 균형을 잡아야한다.
- 변화를 격리하기 위한 방법
  - 메소드 추출하기
  - 객체 추출하기
  - 메소드 객체

## 데이터 이주시키기

표현 방식을 변경하기 위해서는 어떻게 해야할까? <br/>
&gt; 일시적 데이터 중복시키기

- 내부에서 외부로 변화시키기
- 내부의 표현 양식을 변경한 후, 외부의 인터페이스를 변화시키는 방법
  1. 새로운 포맷의 인스턴스 변수 추가
  2. 기존 포맷의 인스턴스 변수가 있는 모든 부분에 새로운 포맷의 인스턴스 변수도 셋팅
  3. 기존 변수가 사용하는 모든 부분에 새 변수를 사용하게 변경
  4. 기존 포맷 제거
  5. 새 포맷에 맞게 외부 인터페이스를 변경
- 외부에서 내부로 변화시키기
  1. 새 포맷으로 인자를 하나 추가
  2. 새 포맷 인자에서 이전 포맷의 내부적 표현양식으로 번역
  3. 이전 포맷 인자를 삭제
  4. 이전 포맷을 사용하는 것들을 새 포맷으로 변경
  5. 이전 포맷 삭제
```java
class TestSuite {
    private Test test;
    
    public void add(Test test) {
        this.test = test;
    }
    
    public void run(Result result) {
        test.run(result);
    }
}
```
```java
class TestSuite {
  private Test test;
  private List<Test> tests;
  
  TestSuite() {
      test = new ArrayList<>();
  }
}
```
```java
public void add(Test test) {
    this.test = test;
    this.tests.append(test);
}

public void run(Result result) {     
    test.run(result);
    for(int i = 0 ; i < tests.size(); ++i){
        tests.get(i).run(result);    
    }
}
```
```java
public void add(Test test) {
    this.tests.append(test);
}

public void run(Result result) {     
    for(int i = 0 ; i < tests.size(); ++i){
        tests.get(i).run(result);    
    }
}
```

## 메소드 추출하기

길고 복잡한 메소드의 일부분을 별도의 메소드로 분리하고 이를 호출하자
- 반복문 내 코드나 반복문 전체, 조건문의 가지들이 일반적인 후보
- 메소드를 작은 조각으로 나누는 것은 때때로 그 정도가 지나칠 수 있다
- 그럴때는 모든 코드를 한자리에 모아 놓고 메서드 인라인 리팩토링을 시도해보자.

### 메소드 인라인

너무 꼬이거나 산재한 제어흐름을 단순하게할 때 사용하자.
- 메서드 호출하는 부분을 지우고 본문을 교체하는 것.
- 메서드 인라인은 단순히 본문으로 교체하는 것으로 끝나는게 아니고 메서드로 추상화된 여러 계층들을 보고 다시 이해하는 것에 관점을 둔다.
- 즉, 모든 코드들이 인라인화 되고, 실제적인 필요성에 의해 다시 추상화하기 위한 하나의 단계로 볼 수 있다.

## 인터페이스 추출하기

어떤 객체에 대한 두 번째 구현이 필요한 경우 인터페이스를 고려 할 수있다.
- 첫 번째 구현에서 두 번째 구현으로 이동하고자 하는 경우
  - 예를들어, Rectangle이 있고 Oval이라는 클래스를 추가하고 싶은 경우 Shape 인터페이스를 만든다
1. 인터페이스 선언
2. 기존 클래스가 인터페이스를 구현하게 설정
3. 필요한 메서들르 인터페이스에 추가
4. 가능한 모든 곳의 타입 선언부에서 **클래스 이름 대신 인터페이스 이름**을 사용.

## 메소드 옮기기

메소드를 원래 있어야할 클래스로 옮기는 것.
- 보증 안되는 예상을 발견하는데 사용하는 방법이다
- 한 메서드가 다른 객체에 하나 이상의 메시지를 보내는 것을 의심하자
- 하나의 메서드가 다른 객체에 너무 많은 의존하는 것을 찾아 제거해보자.
- 예시의 경우 bounds(Rectangle 인스턴스)로 네 개의 메시지가 보내지고 있다.
```java
// Shape.java
int width = bounds.right() - bounds.left();
int height = bounds.bottom() - bounds.top();
int area = width * height;
```
- 메서드 옮기기로 이를 수정하면 다음과 같다
```java
// Rectangle.java
public int area(){
        int width = bounds.right() - bounds.left();
        int height = bounds.bottom() - bounds.top();
        return width * height;
}
```
```java
// Shape.java
int area = bounds.area();
```

## 메서드 객체

여러 개의 매개 변수와 지역 변수를 갖는 복잡한 메서드는 객체로 만들자
- 메서드 추출하기를 적용할 수 없는 코드를 간결하게 만들기 위한 방법
- 한 단위의 코드가 여러 임시 변수들과 매개 변수들로 얽혀 있는 경우, 여러 변수들을 끌고 다니게 된다.
- 이런 경우 메서드 객체를 이용해 코드를 단순화하고 하나의 이름 공간으로 통합할 수 있다

## 메서드 매개 변수를 생성자 매개 변수로 이동

동일한 매개 변수를 서로 다른 몇개의 메서드로 전달하는 경우 매개 변수를 한번만 전달하여 API를 단순화하자