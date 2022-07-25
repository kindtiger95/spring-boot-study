## Proxy, FetchType, Cascade 정리
### PROXY
- em.getReference로 프록시 객체를 받아올 수 있다. (실제로 쓸 일은 없음, 단지 FetchType 이해를 위해 실습)
- 다음이 반드시 성립한다.
  - em.find(..., id) == em.getReference(..., id)
- 프록시 객체가 영속상태에서 떨어져 나간 뒤 프록시를 초기화하면 Exception 발생한다. (No session...)

### FetchType
- LAZY로 사용한다.
- EAGER 사용시 JPQL에서 N+1문제 발생
- LAZY로 사용하고 한 번에 조회할 때는 join fetch 사용하면 된다.
### Cascade, Orphan
- Cascade, Orphan은 소유 관계가 1(부모):1(자식)일때만 사용한다.
- 보통 부모 테이블(OneToMany에서 One)에서 삭제 or 생성 했을 때 자식 테이블도 Insert하기 위해 사용한다.