package gwaze.memo.controller;

import gwaze.memo.dto.MemoCreateRequest;
import gwaze.memo.dto.MemoCreateResponse;
import gwaze.memo.dto.MemoGetResponse;
import gwaze.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemoController {

    private final MemoService memoService;

    @PostMapping("/memos")
    public ResponseEntity<MemoCreateResponse> create(
            @RequestBody MemoCreateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(memoService.save(request));
    }

    @GetMapping("/memos/{memoId}")
    public ResponseEntity<MemoGetResponse> getOne(
            @PathVariable Long memoId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(memoService.findOne(memoId));
    }
}
