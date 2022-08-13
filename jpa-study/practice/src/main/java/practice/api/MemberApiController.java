package practice.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import practice.domain.Member;
import practice.service.MemberService;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    @GetMapping("/api/v2/members")
    public Result membersV2() {
        List<Member> findMembers = this.memberService.findAll();
        List<MemberDto> collect = findMembers.stream()
                                             .map(m -> new MemberDto(m.getName()))
                                             .collect(Collectors.toList());
        return new Result(collect);
    }

    @PostMapping("/api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Validated CreateMemberRequest createMemberRequest) {
        Member member = new Member();
        member.setName(createMemberRequest.getName());

        Long id = this.memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @PutMapping("/api/v2/members/{id}")
    public UpdateMemberResponse updateMemberV2(@PathVariable("id") Long id,
                                               @RequestBody @Validated UpdateMemberRequest updateMemberRequest) {
        this.memberService.update(id, updateMemberRequest.getName());
        Member findMember = this.memberService.findOne(id);
        return new UpdateMemberResponse(findMember.getId(), findMember.getName());
    }

    @Getter
    @Setter
    public static class CreateMemberRequest {
        @NotEmpty
        private String name;
    }

    @AllArgsConstructor
    @Getter
    public static class CreateMemberResponse {
        private Long id;
    }

    @Getter
    @Setter
    public static class UpdateMemberRequest {
        private String name;
    }

    @AllArgsConstructor
    @Getter
    public static class UpdateMemberResponse {
        private Long id;
        private String name;
    }

    @AllArgsConstructor
    @Getter
    public static class Result<T> {
        private T data;
    }

    @AllArgsConstructor
    @Getter
    public static class MemberDto {
        private String name;
    }
}