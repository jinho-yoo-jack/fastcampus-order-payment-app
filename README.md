# Spring Boot Labs
Jedi Spring Boot 연구소

## 목표
지속 성장 가능한 소프트웨어 설계과 구현에 대한 지식과 기술 습득하기

## 환경구성
![Overal_Architecture](https://github.com/jinho-yoo-jack/jedi-spring-labs/assets/58014147/e40f3347-48be-4140-8798-0a20c1512264)
1. JDK 21
2. Spring Boot 3.2.4
4. MySQL 8.0 based Docker
5. Kafka Cluster based Docker


## 개발 방법론
<p>$\bf{\large{\color{#6580DD}"지속 가능한 소프트웨어"}}$</p>
우리의 세상은 빠르게 변하고 그렇기 때문에 우리가 개발하는 Applicaton 또한 이런 변화에 빠르게 적응 할 수 있어야 한다. 
특히, 결제시스템은 더 트렌드에 민감하다. 그래서 우리는 TDD와 DDD를 이용해서 변화에 잘 적응할 수 있는 Architecture 설계부터 Application 구현까지 해보면 
최근 기업들이 추구하는 "지속 가능한 소프트웨어"란 무엇인가를 배워 볼 수 있는 기회가 되지 않을까 싶다.<br>
1. TDD: TDD는 비즈니스의 변경으로 발생하는 변경사항을 코드에 빠르게 적용하고 테스트 해 볼 수 있으며, 이를 통해 개발자의 불안감을 감소 시켜줄 수 있다.<br>
2. DDD: DDD 기반의 헥사고날 아키텍처는 우리가 개발하는 Application이 변경에 유연하고 확장성을 가질 수 있도록 도와준다.<br>

## 아키텍처
### Hexagonal architecture
![헥사고날_아키텍처_최종](https://github.com/jinho-yoo-jack/jedi-spring-labs/assets/58014147/b3662d81-b3d2-4ef1-9a5f-fba7c765ecde)


## 이커머스 주문/결제 시스템
PG사 TEST API 연동(+결제위젯)
### 결제 기능 개발 - TDD 기반 카드 결제 승인 기능
![스크린샷 2024-06-07 오후 3 23 11](https://github.com/jinho-yoo-jack/jedi-spring-labs/assets/58014147/95cfcff2-274a-458e-a849-994205355ce6)
### 결제 기능 개발 - TDD 기반 카드 결제 취소 기능
![스크린샷 2024-06-07 오후 4 20 26](https://github.com/jinho-yoo-jack/jedi-spring-labs/assets/58014147/90796985-832b-4afc-a948-266161a92b79)
### 결제 기능 개발 - TDD 기반 카드 결제 정산 기능
![스크린샷 2024-06-07 오후 4 30 57](https://github.com/jinho-yoo-jack/jedi-spring-labs/assets/58014147/263c998c-9cdd-42c5-a825-8c01ad5ea69a)
   - DDD 설계(TODO://곶감)
   - 전체 구성도(TODO://곶감)
   - 시스템 배포
     - Docker 기반 컨테이너화
