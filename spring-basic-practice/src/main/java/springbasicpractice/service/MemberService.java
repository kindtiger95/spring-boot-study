package springbasicpractice.service;

import springbasicpractice.common.enumeration.Member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long memberId);
}
