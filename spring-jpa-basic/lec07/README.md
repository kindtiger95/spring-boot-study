## JPA 간단한 부분 정리
- Enumerated은 EnumType.STRING으로만 사용하는 것이 좋다.
- ManyToMany는 OneToMany - [table] - ManyToOne으로 구성한다.
- 즉, ManyToMany는 사용 X
- Inheritance의 strategy중 TABLE_PER_CLASS는 사용 X 그리고 간단할 때만 SINGLE_TABLE or JOIN 전략을 사용하자.
- BaseEntity는 자주 사용되어짐. --> 모든 테이블에 공통적으로 생성되어야 할 컬럼 생성