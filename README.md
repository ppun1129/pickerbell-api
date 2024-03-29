# Pickerbell(API)

### Pickerbell은?
점심식사를 어디서 해야 할 지 고민이 될 때, 내 주위에 있는 음식점 중 하나를 뽑아 알려주는 서비스 입니다.<br>
한식, 일식, 중식, 양식, 분식 등 카테고리만 고르면, 지정된 장소에서 일정 범위 내의 음식점 중 하나를 뽑아 보여줍니다.<br>
단, 이는 사용자 평가에 의한 맛집을 추천해 주는 것은 아니고 무작위로 추첨하여 보여지게 됩니다.<br>
추후에는 음식점에 대한 만족도 정보를 모아 비교적 괜찮은 음식점을 추천해 주는 것을 계획하고 있습니다.<br>

### 이 프로젝트는?
Pickerbell 서비스 프로젝트는 이용자에게 보여지기 위한 프론트 프로젝트와, 핵심 로직이 구현된 API 프로젝트로 구성되어 있습니다.<br>
이 프로젝트는 Pickerbell 서비스에서 기능 전반을 담당하는 API 서버 구현 프로젝트입니다.<br>
회원가입부터 음식점 추천까지, 대부분의 기능이 포함되어 있습니다.<br>
아직 계획한 모든 기능이 구현되지 않았기에, 하나의 프로젝트가 모든 기능을 포함하는 형태가 되었습니다.<br>
추후에는 큰 기능 분류별로 분리하여 MSA 구조로의 전환을 계획하고 있습니다.<br>

### 이 프로젝트에 사용된 기술들
* Java 17
* Spring Boot 3.1.0
* JPA(Spring integration)
* Redis
* RDBMS(Local : H2, Production : MariaDB)

### 이 프로젝트에 앞으로 사용'될' 기술들
* Docker : 운영 뿐 아니라 개발 환경 구성의 용이성을 위해 활용 예정
* Kafka : MSA 구조로 전환할 경우 시스템 간 메시지를 주고받기 위한 용도로 사용 예정

### 프로젝트 구조(패키지)
최상위 패키지 com.pickerbell.api 하위에 아래와 같이 구성되어 있으며,<br>
각각의 하위 패키지는 controller, domain, service 등의 패키지를 포함할 수 있습니다.
* auth
  * RSA, JWT등을 이용한 사용자의 인증 처리에 대해 구현된 클래스들이 위치
* common
  * 기능 구분 없이 공통적인 코드를 포함하는 패키지
  * 현재는 각 엔티티에 작성/수정자 및 등록/수정시각에 대한 BaseEntity만이 존재
* config
  * Java 기반 Spring Boot 설정 관련 코드들이 위치
  * Spring Security, Redis 연결 정보 등
* exception
  * RuntimeException을 상속한 커스텀 Exception을 정의한 클래스들이 위치
* group
  * 그룹 정보와 관련된 로직이 구현된 클래스들이 위치
  * 그룹과 사용자 정보를 매핑하는 로직도 이 패키지 하위에 위치
* location
  * 위치 정보 처리와 관련된 로직이 구현된 클래스들이 위치
  * 위치 정보 제공자(현재 카카오)가 서비스 하는 API를 이용하여 필요한 정보를 조회 및 가공
  (provider 패키지 하위에 위치)
* security
  * 구간 암호화를 위한 RSA key, API 호출 시 인가 처리를 위한 JWT를 생성하고 관리, 유효성을 판단하기 위한 클래스들이 위치
* user
  * 사용자 정보와 관련된 로직이 구현된 클래스들이 위치
