## JPQL
- JPQL로 엔터티를 조회할 경우 영속성 컨텍스트에서 관리해준다.
  - 데이터를 수정할 경우 commit하면 반영된다.
- Join은 명시적 조인으로 하는게 좋다 
  - SELECT member.team X
  - SELECT member FROM Member as member JOIN member.team as t
