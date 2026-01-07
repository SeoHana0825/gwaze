package gwaze.member.service;

import gwaze.member.dto.MemberCreateRequest;
import gwaze.member.dto.MemberCreateResponse;
import gwaze.member.dto.MemberGetResponse;
import gwaze.member.entity.Member;
import gwaze.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public MemberCreateResponse save(MemberCreateRequest request) {
        Member member = new Member(request.getName());
        Member savedMember = memberRepository.save(member); // 레퍼지토리에 있는 save 메서드 를 실행해랏 (아직 로컬)
        return new MemberCreateResponse(savedMember.getId(), savedMember.getName());
    }

    @Transactional(readOnly = true)
    public List<MemberGetResponse> findAll() {
        List<Member> members = memberRepository.findAll();
        List<MemberGetResponse> dtos = new ArrayList<>();
        for (Member member : members) {
            MemberGetResponse dto = new MemberGetResponse(member.getId(), member.getName());
            dtos.add(dto);
        }
        return dtos;
    }

    @Transactional(readOnly = true)
    public MemberGetResponse findOne(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new IllegalArgumentException("없는 맴버입니다.")
        );
        return new MemberGetResponse(member.getId(), member.getName());
    }


}
