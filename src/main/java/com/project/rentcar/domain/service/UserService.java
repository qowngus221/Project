package com.project.rentcar.domain.service;

import com.project.rentcar.config.PasswordUtil;
import com.project.rentcar.domain.entity.User;
import com.project.rentcar.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String signUp(String memberId, String memberPw, String memberNm, String memberEmail) {
        if (userRepository.findByMemberId(memberId).isPresent()) {
            return "이미 존재하는 사용자 ID입니다.";
        }

        if (userRepository.findByMemberEmail(memberEmail).isPresent()) {
            return "이미 등록된 이메일입니다.";
        }

        // Salt 생성
        String salt = PasswordUtil.generateSalt();

        // 비밀번호 암호화 (Salt와 함께 SHA-256 적용)
        String encryptedPassword = PasswordUtil.encryptPassword(memberPw, salt);

        // 사용자 정보 저장
        User user = User.builder()
                .memberId(memberId)
                .memberPw(encryptedPassword)
                .memberSalt(salt)  // Salt도 저장
                .memberNm(memberNm)
                .memberEmail(memberEmail)
                .build();

        userRepository.save(user);
        return "회원가입이 완료되었습니다.";
    }

    public String login(String memberId, String memberPw) {
        User user = userRepository.findByMemberId(memberId).orElse(null);

        if (user == null) {
            return "존재하지 않는 사용자 ID입니다.";
        }

        // 저장된 Salt로 비밀번호를 암호화하여 비교
        String encryptedPassword = PasswordUtil.encryptPassword(memberPw, user.getMemberSalt());

        if (!encryptedPassword.equals(user.getMemberPw())) {
            return "비밀번호가 일치하지 않습니다.";
        }

        return "로그인 성공";
    }
}
