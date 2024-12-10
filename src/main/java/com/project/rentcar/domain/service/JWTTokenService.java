package com.project.rentcar.domain.service;


import com.project.rentcar.domain.entity.JWTToken;
import com.project.rentcar.domain.repository.JWTTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class JWTTokenService {

    private final JWTTokenRepository jwtTokenRepository;

    @Autowired
    public JWTTokenService(JWTTokenRepository jwtTokenRepository) {
        this.jwtTokenRepository = jwtTokenRepository;
    }

    // AccessToken과 RefreshToken을 생성하고 DB에 저장
    public void saveJWTToken(String username, String accessToken, String refreshToken) {
        JWTToken jwtToken = JWTToken.builder()
                .username(username)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .issuedAt(LocalDateTime.now())
                .build();

        jwtTokenRepository.save(jwtToken);  // DB에 저장
    }

    // AccessToken을 사용하여 JWTToken을 조회
    public JWTToken getJWTTokenByAccessToken(String accessToken) {
        return jwtTokenRepository.findByAccessToken(accessToken);  // AccessToken으로 JWTToken 조회
    }

    // Username을 사용하여 JWTToken을 조회
    public JWTToken getJWTTokenByUsername(String username) {
        return jwtTokenRepository.findByUsername(username);  // Username으로 JWTToken 조회
    }
}
