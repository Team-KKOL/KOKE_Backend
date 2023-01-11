package com.koke.koke_backend.roastery.controller;

import com.koke.koke_backend.roastery.service.RoasteryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author : 김하빈(danny9643@naver.com)
* @description : Roastery API
* @!
* @?
* @TODO
* @Date : 2023-01-11, Wed, 22;1
*/

@RestController
@RequiredArgsConstructor
@RequestMapping("/roastery")
public class RoasteryController {

    private final RoasteryService roasteryService;

}
