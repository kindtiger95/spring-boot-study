package springbasicpractice.repositories;

import springbasicpractice.commons.Member;

public interface MemberRepository {
    void save(Member member);

    Member findById(Long memberId);

}
