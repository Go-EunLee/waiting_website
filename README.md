# 대기열 웹사이트

**대기열 웹사이트**는 간편하고 효율적인 대기열 관리 시스템을 통해 사용자들에게 공정한 순서를 제공합니다. 특정 홈페이지에 접속하는 순간, 각 사용자에게 고유한 순번이 부여되며, 정해진 대기 조건에 따라 순차적으로 원하는 페이지로 리디렉션됩니다. 이 과정은 완전히 자동화하였습니다.

<br>

## 기술 스택
<div align=center>
  <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 
  <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white"> 
  <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"> 
  <img src="https://img.shields.io/badge/javascript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black">
  <img src="https://img.shields.io/badge/spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> 
  <img src="https://img.shields.io/badge/redis-BC382D?style=for-the-badge&logo=redis&logoColor=white"> 
  <img src="https://img.shields.io/badge/apache jmeter-D22128?style=for-the-badge&logo=apache jmeter&logoColor=white"> 
</div>


<br>

## 주요 기능
1. 사용자 접속 시 순서 지정
- 사용자가 대기열 페이지에 처음 접속하는 순간 Redis를 활용하여 고유한 순번이 부여
- 대기열에 등록된 사용자 관리, 순번에 따라 정해진 순서로 페이지에 접속할 권리를 얻음
2. 고유 토큰 발급
- 각 사용자에게 고유한 토큰을 발급하여 대기열 상태를 추적.
- 토큰은 연결된 세션과 사용자 정보를 기반으로 생성, 사용자가 대기열 페이지를 유지하고 있는 동안 유효
3. 대기열 유지 및 사용자 이탈 감지
- 사용자는 대기열 페이지에 머물러야만 순번이 유지
- 만약 사용자가 페이지에서 이탈(세션 만료, 네트워크 끊김 등)하면 순서가 뒤로 밀리게 되어 다른 사용자들에게 순서가 넘어감
4. 순서에 따른 페이지 접속 제어
- 설정된 시간과 순서에 따라 대기열 페이지에서 다음 목적지 URL로 리디렉션
5. 대규모 트래픽 대응
- Redis를 활용해 높은 처리량을 실시간으로 관리할 수 있기 때문에 대규모 사용자가 몰려도 성능 저하 없이 대기열 처리
<br>

## Jmeter 를 사용한 부하 테스트
<img width="861" alt="Image" src="https://github.com/user-attachments/assets/f728c56a-4c00-4284-adfd-07f2f17f928b" />
<img width="883" alt="Image" src="https://github.com/user-attachments/assets/bdd5fbcf-3596-4036-b528-4d55d589505e" />
