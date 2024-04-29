## 단위 테스트 기본 개념과 JUnit 활용 실습
- 단위 테스트 기본 개념
    - 자바 프로그래밍 언어용 Unit Test Framework
- JUnit5 활용 실습
    - Spring Boot 2.2 부터 기본적으로 의존성이 추가되어 있기 때문에 별도로 Package 관리 도구에 추가하지 않아도 된다.
        - Spring 부트를 사용하지 않으면 따로 추가해야 함
    - JUnit5의 명명 규칙
        - 정식 가이드 문서에서는 따로 규칙을 정의하지 않음
        - “Test”라는 접미사를 붙이는 것이 국룰
        - 회사에서 정해 놓은 규칙을 따라야 함
    - 테스트 메서드 `@Test`
        - `@Test` 어노테이션을 추가
        - return 타입은 void로 선언해야 한다.
    - Junit 프레임워크의 테스트 실행 흐름
        1. Test Method 실행
        2. 실행 결과
            - 실행 중 AssertionFailedError 예외가 발생하면, Fail 되고 테스트가 종료된다.
            - 실행 중 AssertionFailedError 예외가 발생하지 않으면, Success로 테스트가 종료
        - 예외가 발생했냐 안했냐로 테스트 성공여부를 판별하기 때문에 return 타입이 void이다. `@Test` 어노테이션으로 return 타입을 void로 강제한다.
- 단언(Assert) 메서드란?
    - Test Case의 실행 결과를 판별해주는 메서드
    - Assert 메서드
- 주요 단언 메서드
    - `assertEquals(expected, actual);`
        - 실제 값과 기대 값 비교 메서드 실패하면 fail, 성공하면 success
    - `fail();`
        - 테스트를 실패 시키고자 할 때 사용한다.
        - 실패하는 Test 시나리오를 테스트 할 때 사용한다.
        - 실행 흐름을 테스트하는 경우에 주로 사용하는 메서드이다.
        - null 유효성 체크에서 문제가 일어나면 예외를 발생시키지 않고 assertEquals 메서드를 실행한다. 이를 방지하기 위해서 fail을 사용한다.

            ```java
                private String toUpperCase(String name) throws NullPointerException{
                    if(name == null) throw new NullPointerException();
                    return name.toUpperCase();
                }
            
                @Test
                void toUpperCaseThrowNullPointExceptionTest(){
                    try{
                        String name = toUpperCase("JIMIN");
                        fail();
                        assertEquals("JIMIN", name);
                    }catch (NullPointerException e){
                        
                    }
                }
            ```

    - `assertThrows();`
        - Exception 발생 유무 검증이 필요한 경우, 사용한다.
- **JUnit 테스트 라이프 사이클**
    - 메서드
        - `@BeforeAll`
            - 한 클래스의 모든 테스트 메서드가 실행되기 전에 특정 작업을 수행해야 하는 경우, 해당 어노테이션이 붙음 메서드 정의
            - `static` 으로 정의
        - `@BeforeEach`
            - 테스트를 실행하는데 필요한 준비 작업을 할 때 사용한다.
        - `@AfterEach`
            - 테스트 종료 후, 정리할 것이 있을 때 사용한다.
            - 리소스 반환, 임시로 사용한 파일 삭제
        - `@AfterAll`
            - 모든 테스트가 끝나는 시점
            - 테스트 환경에 부가적으로 필요했던 인스턴스의 리소스 반환이나 종료를 진행
            - `static` 으로 선언
- 좋지 않은 테스트 코드
    - 테스트 메서드끼리 의존성이 있는 경우는 좋지 않다.
        - Test1을 통과해야 Test2를 통과하는 경우 테스트 메서드끼리 의존성이 있는 경우이다.
        - 테스트 메서드는 독립적으로 실행 가능해야 한다.
        - 예제