package com.project.rentcar.domain.repository;


import com.project.rentcar.domain.entity.JWTToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JWTTokenRepository extends JpaRepository<JWTToken, Long> {

    // AccessToken 을 받아 Entity 반환
    JWTToken findByAccessToken(String accessToken);

    // Username 을 받아 Entity 반환
    JWTToken findByUsername(String username);
}
