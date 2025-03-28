package com.waiting.flow.controller;

import com.waiting.flow.dto.AllowUserResponse;
import com.waiting.flow.dto.AllowedUserResponse;
import com.waiting.flow.dto.RankNumberResponse;
import com.waiting.flow.dto.RegisterUserResponse;
import com.waiting.flow.service.UserQueueService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/queue")
@RequiredArgsConstructor
public class UserQueueController {
    private final UserQueueService userQueueService;

    @PostMapping("")
    public Mono<RegisterUserResponse> registerUser (
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "queue", defaultValue = "default") String queue
    ) {
        return userQueueService.registerWaitQueue(queue, userId)
                .map(RegisterUserResponse::new);
    }

    @PostMapping("/allow")
    public Mono<AllowUserResponse> allowUser (
            @RequestParam(name = "count") Long count,
            @RequestParam(name = "queue", defaultValue = "default") String queue
    ) {
        return userQueueService.allowUser(queue, count)
                .map(allowed -> new AllowUserResponse(count, allowed));
    }

    @GetMapping("/allowed")
    public Mono<AllowedUserResponse> isAllowedUser (
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "queue", defaultValue = "default") String queue
    ) {
        return userQueueService.isAllowed(queue, userId)
                .map(AllowedUserResponse::new);
    }

    @GetMapping("/rank")
    public Mono<RankNumberResponse> getRankUser (
            @RequestParam(name = "user_id") Long userId,
            @RequestParam(name = "queue", defaultValue = "default") String queue
    ) {
        return userQueueService.getRank(queue, userId)
                .map(RankNumberResponse::new);
    }
}
