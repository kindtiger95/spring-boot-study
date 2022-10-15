package practice.querydsl.repository;

import static practice.querydsl.entity.QMember.member;
import static practice.querydsl.entity.QTeam.team;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import practice.querydsl.dto.MemberSearchCondition;
import practice.querydsl.dto.MemberTeamDto;
import practice.querydsl.dto.QMemberTeamDto;

@RequiredArgsConstructor
public class QMemberRepositoryImpl implements QMemberRepository {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MemberTeamDto> search(MemberSearchCondition condition) {
        return jpaQueryFactory.select(new QMemberTeamDto(member.id, member.username, member.age, team.id, team.name))
                              .from(member)
                              .leftJoin(member.team, team)
                              .where(usernameEq(condition.getUsername()), teamNameEq(condition.getTeamName()), ageGoe(condition.getAgeGoe()),
                                  ageLoe(condition.getAgeLoe()))
                              .fetch();
    }

    //fetchResults deprecated.
    @Deprecated
    @Override
    public Page<MemberTeamDto> searchPageSimple(MemberSearchCondition condition, Pageable pageable) {
        return null;
    }

    @Override
    public Page<MemberTeamDto> searchPageComplex(MemberSearchCondition condition, Pageable pageable) {
        List<MemberTeamDto> memberTeamDtoList = jpaQueryFactory.select(new QMemberTeamDto(member.id, member.username, member.age, team.id, team.name))
                                                               .from(member)
                                                               .leftJoin(member.team, team)
                                                               .where(usernameEq(condition.getUsername()),
                                                                   teamNameEq(condition.getTeamName()),
                                                                   ageGoe(condition.getAgeGoe()),
                                                                   ageLoe(condition.getAgeLoe()))
                                                               .offset(pageable.getOffset())
                                                               .limit(pageable.getPageSize())
                                                               .orderBy(member.username.desc())
                                                               .fetch();
        return new PageImpl<>(memberTeamDtoList, pageable, memberTeamDtoList.size());
    }

    private BooleanExpression usernameEq(String username) {
        return StringUtils.hasText(username) ? member.username.eq(username) : null;
    }

    private Predicate teamNameEq(String teamName) {
        return StringUtils.hasText(teamName) ? team.name.eq(teamName) : null;
    }

    private Predicate ageGoe(Integer ageGoe) {
        return ageGoe != null ? member.age.goe(ageGoe) : null;
    }

    private Predicate ageLoe(Integer ageLoe) {
        return ageLoe != null ? member.age.loe(ageLoe) : null;
    }
}
