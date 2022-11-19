package springbasicpractice.repository;

import springbasicpractice.common.enumeration.Member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);

}
