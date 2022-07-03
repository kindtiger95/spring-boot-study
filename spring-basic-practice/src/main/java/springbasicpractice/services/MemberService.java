package springbasicpractice.services;

import springbasicpractice.commons.Member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
