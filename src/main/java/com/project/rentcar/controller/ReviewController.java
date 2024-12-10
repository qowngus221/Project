package com.project.rentcar.controller;

import com.project.rentcar.domain.dto.ReviewDto;
import com.project.rentcar.domain.service.ReviewServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("review")
public class ReviewController {

    @Autowired
    private ReviewServiceImpl reviewServiceImpl;

    @GetMapping("addReview")
    public void getadd(){
        log.info("GET /review/addReview");
    }
    @PostMapping("addReview")
    public void postadd(ReviewDto reviewDto){
        reviewServiceImpl.addReview(reviewDto);
    }
}
