## JPQL
- JPQL로 엔터티를 조회할 경우 영속성 컨텍스트에서 관리해준다.
  - 데이터를 수정할 경우 commit하면 반영된다.
- Join은 명시적 조인으로 하는게 좋다 
  - SELECT member.team X
  - SELECT member FROM Member as member JOIN member.team as t
- 서브쿼리는 필요할 때 다시 복습
### fetch join
- 매우 중요 -> 꾸준한 복습 필요
- JOIN FETCH 문법으로 사용
- 한계가 있는 부분 파악 필요 (OneToMany, 2개 이상의 컬렉션 프레임워크 JOIN)
- OneToMany에서 JOIN FETCH는 중복이 발생하므로 DISTINCT를 적극 사용한다.
- NamedQuery는 Spring Data JPA로 커버 가능하다.