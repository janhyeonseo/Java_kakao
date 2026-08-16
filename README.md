# 카카오 결제 및 지도 API 연동 웹 서비스
### 💡 서비스 개요
- 쇼핑몰의 제품 관리와 결제 처리, 고객 관리 기능을 제공하는 웹 서비스.
- 사용자 인증 로그인 기능, 게시판 관리, 카카오 결제 시스템 통합 등의 기능을 구현. 
- 각 기능은 MVC 패턴을 기반으로 구현됨.
### 🛠️ 기술 스택
</div>
    <div style="text-align: left;">
    <div> <img src="https://img.shields.io/badge/Java-007396?style=flat-square&logo=Java&logoColor=white">
          <img src="https://img.shields.io/badge/Jsp-607396?style=flat-square&logo=Jsp&logoColor=white">
          <img src="https://img.shields.io/badge/Servlet-003496?style=flat-square&logo=Servlet&logoColor=white">
          <img src="https://img.shields.io/badge/OpenAPI-004596?style=flat-square&logo=OpenAPI&logoColor=white">
          <br>
          <img src="https://img.shields.io/badge/Javascript-F7DF1E?style=flat-square&logo=Javascript&logoColor=white">
          <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=MySQL&logoColor=white">
          <img src="https://img.shields.io/badge/Github-181717?style=flat-square&logo=Github&logoColor=white">
          </div>
    </div>
<br>

## 주요 기능 및 역할

### 📝 Kakao 주소&결제 API
- 카카오 주소 API를 사용하여 사용자가 주소를 검색하고 선택할 수 있는 기능을 제공.
- 카카오 결제 API로 `PaymentServlet`과 `ApproveServlet`을 통해 결제 기능 구현.
- 결제 성공 및 실패에 따른 리디렉션 처리.

### 📝 회원 관리
- `BCrypt` 알고리즘을 사용하여 회원의 비밀번호 암호화 기능 구현.
- `login.jsp`와 `member.jsp`는 각각 로그인과 회원 가입을 구현.

### 📝 데이터베이스 연결
- `DBConnection` 클래스를 통해 처리되며, 회원 정보 및 결제 정보가 MySQL 데이터베이스에 저장

### 📝 게시판
- 간단한 CRUD 기능 구현.
