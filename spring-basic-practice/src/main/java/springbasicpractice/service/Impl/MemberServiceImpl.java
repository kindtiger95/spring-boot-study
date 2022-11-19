package springbasicpractice.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springbasicpractice.common.enumeration.Member;
import springbasicpractice.repository.MemberRepository;
import springbasicpractice.service.MemberService;

@Component
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
