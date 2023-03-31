# SMBE4-Java_ToyProject_BookRentalSystem

### 자바 토이프로젝트 만화책 대여 관리 시스템 <br/><br/>

- 파일 이름 작성법 
    - ````com.cmbookrental.prj```` 패키지 생성
    - 패키지 구조는 아래와 같이 진행하도록 한다
      - ````app```` : Main Application (Menu 처리 및 View 처리용)
      - ````comm```` : 공통코드와 Utility
      - ````dto```` : 데이터 저장 클래스 
      - ````factory```` : 객체 생생 팩토리 클래스
      - ````controller```` : 클라이언트 요청/응답 처리 컨트롤 및 비즈니스 처리
      - ````repository```` :  데이터 처리 관련 클래스 (조회/저장/수정/삭제)
    - 각 Role별로 패키지를 나눠서 관련 클래스끼리 함께 패키지화
      - interace 구현 클래스는 인터페이스명+'Impl'을 붙이도록 Naming
      - 예시
        - ```com.cmbookrental.prj.dto``` 패키지
          - ```ComicBookDTO```, ```RentalDTO```
        - ```com.cmbookrental.prj.controller``` 패키지
          - interace ```ComicBookController```, ```RentalController```
          - interace 구현 클래스 ```ComicBookControllerImpl```, ```RentalControllerImpl```
        - ```com.cmbookrental.prj.repository``` 패키지
          - ```ComicBookRepository```, ```RentalRepository```
        - ```com.cmbookrental.prj.app``` 패키지
          - ```ComicBookRentalApp```
      
          
      
- 제출방법
    - 본인의 이름으로 ````branch````를 생성하여 ````push````후 ````pull request```` 작성
        - 예시
            - branch 이름 - ````FirstNameLastName````
            - commit 메시지 - ````Java Toy Project upload by FirstNameLastName````
    - pull request는 본인이 하고 싶은 말이나 질문을 적어주세요.
        - ````코드리뷰 빡세게 부탁드립니다.```` ````클린한 코드인지 봐주세요.```` ````이 코드의 조금 더 나은 방법이 있을까요.````
        - ````~~번 문제 풀지 못했습니다.```` ````~~번 문제 풀이 방법을 알려주시면 감사하겠습니다.````
        - ````결과는 나왔는데 맞는지 모르겠습니다.````
  
