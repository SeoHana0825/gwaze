package gwaze.memo.service;

import gwaze.member.entity.Member;
import gwaze.member.repository.MemberRepository;
import gwaze.memo.dto.MemoCreateRequest;
import gwaze.memo.dto.MemoCreateResponse;
import gwaze.memo.dto.MemoGetResponse;
import gwaze.memo.entity.Memo;
import gwaze.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemoService {

    // memberRepository 추가!!
    private final MemoRepository memoRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public MemoCreateResponse save(MemoCreateRequest request) {
        Member member = memberRepository.findById(request.getMemberId()).orElseThrow(
                () -> new IllegalStateException("없는 멤버입니다")
        );

        Memo memo = new Memo(request.getText(), member);
        Memo savedMemo = memoRepository.save(memo);
        return new MemoCreateResponse(savedMemo.getId(), savedMemo.getText());
    }

    @Transactional(readOnly = true)
    public MemoGetResponse findOne(Long memoId) {
        Memo memo = memoRepository.findById(memoId).orElseThrow(
                () -> new IllegalStateException("없는 메모입니다.")
        );
        System.out.println("=====주의=====");
        Member member = memo.getMember();
        return new MemoGetResponse(
                memo.getId(),
                memo.getText(),
                member.getId(),
                member.getName()
        );
    }
}
