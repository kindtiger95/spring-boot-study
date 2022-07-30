package springjpapractice1.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import springjpapractice1.domain.entities.Member;
import springjpapractice1.repositories.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Long join(Member member) {
        isExistMember(member);
        this.memberRepository.save(member);
        return member.getId();
    }

    public List<Member> findMembers() {
        return this.memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return this.memberRepository.findOne(memberId);
    }

    private void isExistMember(Member member) {
        Optional<Member> findMember = this.memberRepository.findByName(member.getName())
                                                           .stream()
                                                           .findAny();
        if (findMember.isPresent())
            throw new IllegalStateException("이미 존재하는 회원");
    }
}
