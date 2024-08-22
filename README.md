## Ch01. API 문서
### API
- Application Programming Interface
- 대부분의 Server-Side(Back-end) 에서 제공
- 데이터의 조작 (CRUD)을 하기 위함 
- 왜 만들어야할까?
    - 제공자 <-> 사용자 사용방법 공유/협의
    - API 버전 및 변경사항 관리
- 기존 방식의 단범
    - 버전 변경등 수정에 취약
    - API와 문서 상태가 다를 수 있음 (버전, 오류 등)
    - 검증되지 못한 문서
## Ch02. Spring REST Docs
### Spring REST Docs
- RESTful 서비스에 대한 정확하고 읽기 쉬운 문서를 생성하도록 돕는 것이 목적
- 테스트를 통하여 API의 문서 생성
- 스니펫(Snippets)의 조합으로 API 문서 커스터마이징
- 간결한 적용, 다양한 생성 방법 
### 장점
- 별도의 Html, PDF 파일로 API 문서 자동 생성 (Hosting 가능)
- Request/Response 테스트를 통하여 API 문서의 신뢰도 상승
- API 변경에 따른 API 문서 최신화 보장 
## Ch03. Swagger
- API 문서화의 양대산백중 하나
- RESTful 문서에 대한 명세보다는 API를 쉽게 호출해볼 수 있는것에 초점
- |Spring REST Docs|Swagger|
  |------------|------------|
  |테스트 기반으로 실행|API 테스트 UI 제공 |
  |문서에 대한 신뢰성| 어노테이션을 통한 간단한 작업 |
  |Product 코드에 영향 없음|테스트 코드가 없어도 문성화 가능|
  |간결, 명료|예쁘다 |
  |Snippets 조합으로 커스터마이징 가능||
## Ch04. 실습 API 문서 자동으로 만들기 
- 문서화 위한 API
-   | 사용처                    | HTTP API              |
    |------------------------|-----------------------|
    | 생성                     | POST /api/user        |
    | 조회                     | GET /api/user/{id}    |
    | 수정                     | PUT /api/user         |
    | 삭제                     | DELETE /api/user/{id} |
- 순서
  - 프로젝트 생성
  - 실습에 필요한 테스트 API 코드 작성
  - Spring REST Docs 관련 설정 추가
  - Test Code 작성으로 스니펫 Snippets 생성
  - 스니펫에 의해 html 문서가 만들어지도록 adoc 파일 구성
  - 빌드시 생성된 html 파일이 호스팅 되도록 설정 
## Ch05. OpenApi Spec
- REST API에 대한 설명,생성,사용 및 시각화 하기위한 인터페이스 파일의 사양
- Swagger 프레임워크의 일부였지만 오픈소스로 전환
- 기본 내용
  - endpoint
  - HTTP method, parameters
  - input / output
  - Authentication methods
  - Contact information, license, terms of use and other information
- YAML 또는 JSON으로 작성 가능
## Ch06. 실습 Spring REST Docs와 Swagger 조합
- Spring REST Docs와 Swagger 조합
  1. Spring REST Docs에서 만들어지는 문서 설정을 기반으로 OpenAPI Spec 파일을 생성
  2. 오픈소스 Gradle : https://github.com/ePages-de/restdocs-api-spec
  3. Swagger UI를 띄우고 생성된 OpenAPI Spec 파일을 읽는 방식 
  4. Spring REST Docs 의 장점
     - Test 를 통과해야 문서가 생성
     - Product 코드에 영향 없음
  5. Swagger 장점
     - API 테스트 UI를 제공
  6. MSA 환경 등 여러 API 문서를 한곳에서 조회 가능 