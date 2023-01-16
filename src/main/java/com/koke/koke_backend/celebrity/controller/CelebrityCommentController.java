package com.koke.koke_backend.celebrity.controller;

import com.koke.koke_backend.celebrity.service.CelebrityCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/celebrity")
public class CelebrityCommentController {

    private final CelebrityCommentService celebrityCommentService;

}
