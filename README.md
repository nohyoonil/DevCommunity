# Dev Community
개발자들을 위한 커뮤니티

## Tech Stack
* Java 11
* SPRINGBOOT
* JWT
* H2
* Kafka
* SSE
* Redis

## 프로젝트 기능 및 설계
#### [회원]
* 회원 가입
    * email, password, nickname을 통한 회원가입 (email, nickname은 유니크해야 함)
* 로그인
    * 회원가입한 email, password를 입력하여 로그인 -> 성공시 JWT 토큰 발급
* 회원 정보 변경
    * JWT로 1차 검증 후 password 입력을 통해 2차 검증하여 회원 정보 변경

#### [게시글]
* 게시글 CRUD
    * 물리적 삭제가 아닌 논리적 삭제 (deleted_date column 이용)
* 좋아요 기능
* 조회수
    * 회원의 게시글 조회 여부를 db 대신 Redis에 저장하여 확인. expired를 이용하여 조회시점 24시간 후 캐시 삭제
    * 회원은 id (pk) , 비회원은 ip를 이용해 조회수 카운팅
* 댓글/대댓글
    * parent_id column을 이용하여 댓글/대댓글 여부 파악. (id == null  -> 댓글 / id == 10  -> 10번 댓글의 대댓글)

**좋아요와 조회수 기능에서 일어날 수 있는 동시성 이슈에 대비한 lock 처리**

#### [게시글 조회]
* 좋아요/조회수 순 조회 기능
* 댓글을 작성한 게시글 조회
* 검색 키워드를 이용한 게시글 조회
    * 우선 like 검색으로 구현 후, 여유 있으면 elastic search 학습한 뒤 적용 예정

#### [알림]
* 내 게시글에 댓글이 달릴 경우
* 내 댓글의 대댓글이 달릴 경우

**알림은 댓글을 등록하는 경우에만 발생. 댓글 작성 로직에서 DB에 댓글 등록과 알림 등록 후, 실제 클라이언트에게 알림을 주는 요청은 kafka를 이용해 비동기적으로 처리 (멀티 모듈 이용 예정)**

## ERD
![erd](https://github.com/nohyoonil/DevCommunity/assets/77871257/a0700e36-5675-43b7-b4c2-12132ec891ef)



## TROUBLE SHOOTING