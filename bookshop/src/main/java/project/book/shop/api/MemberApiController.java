package project.book.shop.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import project.book.shop.entity.Member;
import project.book.shop.service.MemberService;
import lombok.Data;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
    private final MemberService memberService;

    //v1 파라미터는 엔티티로 받는걸 지양한다.
    @PostMapping("/api/v1/members")
    public CreateMemberResponse saveMemberV1(@RequestBody @Valid Member member){
        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }
    
    @PostMapping("api/v2/members")
    public CreateMemberResponse saveMemberV2(@RequestBody @Valid CreateMemberRequest request){
        Member member = new Member();
        member.setUserName(request.getUserName());

        Long id = memberService.join(member);
        return new CreateMemberResponse(id);
    }

    @Data
    static class CreateMemberRequest{
        private String userName;
    }
    @Data
    @AllArgsConstructor
    static class CreateMemberResponse{
        private Long id;
    }
}
