package practice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.domain.Member;
import practice.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        this.memberDuplicateCheck(member.getName());
        return this.memberRepository.save(member);
    }

    public List<Member> findAll() {
        return this.memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return this.memberRepository.findById(memberId);
    }

    @Transactional
    public void update(Long memberId, String name) {
        Member findMember = this.memberRepository.findById(memberId);
        findMember.setName(name);
    }

    private void memberDuplicateCheck(String name) {
        Member member = this.memberRepository.findByName(name);
        if (member.getId() != null) {
            throw new IllegalStateException("중복 회원입니다.");
        }
    }
}
