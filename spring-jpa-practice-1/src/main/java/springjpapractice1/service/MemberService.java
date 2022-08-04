package springjpapractice1.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springjpapractice1.domain.Member;
import springjpapractice1.repository.MemberRepository;

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

    private void memberDuplicateCheck(String name) {
        Member member = this.memberRepository.findByName(name);
        if (member.getId() != null) {
            throw new IllegalStateException("중복 회원입니다.");
        }
    }
}
