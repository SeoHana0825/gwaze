package gwaze.member.controller;

import gwaze.member.dto.MemberCreateRequest;
import gwaze.member.dto.MemberCreateResponse;
import gwaze.member.dto.MemberGetResponse;
import gwaze.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // DB members 가 꼬여서 members1 으로 다시 생성

    // 맴버 생성
    @PostMapping("/members1")
    public ResponseEntity<MemberCreateResponse> create(
            @RequestBody MemberCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memberService.save(request));
    }

    // 맴버 전체 조회
    @GetMapping("/members1")
    public ResponseEntity<List<MemberGetResponse>> getAll() {
        return ResponseEntity.ok(memberService.findAll());
    }

    // 맴버 단일 조회
    @GetMapping("/members1/{memberId}")
    public ResponseEntity<MemberGetResponse> getOne(
            @PathVariable Long memberId
    ) {
        return ResponseEntity.ok(memberService.findOne(memberId));
    }
}
