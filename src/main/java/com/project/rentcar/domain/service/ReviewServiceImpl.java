package com.project.rentcar.domain.service;


import com.project.rentcar.domain.dto.ReviewDto;
import com.project.rentcar.domain.entity.Review;
import com.project.rentcar.domain.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl {

    @Autowired
    private ReviewRepository reviewRepository;

    public void addReview(ReviewDto reviewDto){
        Review review = ReviewDto.dtoToEntity(reviewDto);
        reviewRepository.save(review);
    }
}
