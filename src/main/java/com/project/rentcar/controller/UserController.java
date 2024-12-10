package com.project.rentcar.controller;


import com.project.rentcar.domain.entity.JwtUtil;
import com.project.rentcar.domain.service.JWTTokenService;
import com.project.rentcar.domain.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;


@Controller
public class UserController {

    private final UserService userService;
    private final JWTTokenService jwtTokenService;

    @Autowired
    public UserController(UserService userService, JWTTokenService jwtTokenService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }

    // 회원가입 폼을 보여주는 메서드 (GET 요청)
    @GetMapping("/signup")
    public String showSignUpForm() {
        return "signup";  // /WEB-INF/jsp/signup.jsp 파일을 반환
    }

    // 회원가입 처리 메서드 (POST 요청)
    @PostMapping("/api/users/signup")
    public String signUp(@RequestParam("memberId") String memberId,
                         @RequestParam("memberPw") String memberPw,
                         @RequestParam("memberNm") String memberNm,
                         @RequestParam("memberEmail") String memberEmail) {
        String result = userService.signUp(memberId, memberPw, memberNm, memberEmail);

        // 회원가입 성공 후 다른 페이지로 리디렉션하거나, 결과를 반환할 수 있습니다.
        if ("회원가입이 완료되었습니다.".equals(result)) {
            return "redirect:/signup-success";  // 성공하면 /signup-success 페이지로 리디렉션
        }
        return "redirect:/signup";  // 실패 시 다시 회원가입 페이지로 리디렉션
    }

    // 회원가입 성공 페이지
    @GetMapping("/signup-success")
    public String showSignUpSuccess() {
        return "signup-success";  // /WEB-INF/jsp/signup-success.jsp 파일을 반환
    }


    // 로그인 폼을 보여주는 메서드 (GET 요청)
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";  // 로그인 페이지 반환
    }

    // 로그인 처리 메서드 (POST 요청)
    @PostMapping("/api/users/login")
    public void login(@RequestParam String memberId,
                      @RequestParam String memberPw,
                      HttpServletResponse response) throws IOException {

        // 로그인 검증
        String result = userService.login(memberId, memberPw);

        if ("로그인 성공".equals(result)) {
            // JWT 토큰 생성
            String newAccessToken = JwtUtil.generateAccessToken(memberId);
            String newRefreshToken = JwtUtil.generateRefreshToken(memberId);

            // JWTToken을 DB에 저장
            jwtTokenService.saveJWTToken(memberId, newAccessToken, newRefreshToken);

            // 로그인 후 응답 헤더에 토큰 설정
            response.setHeader("accessToken", newAccessToken);
            response.setHeader("refreshToken", newRefreshToken);

            // 로그인 후 메인 페이지로 리디렉션
            response.sendRedirect("/main");  // /main 페이지로 리디렉션
        } else {
            // 로그인 실패 시, 401 Unauthorized 에러 반환
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 실패");
        }
    }

    // 로그아웃 처리 메서드 (POST 요청)
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);  // 현재 세션을 가져옵니다 (세션이 없으면 null 반환)
        if (session != null) {
            session.invalidate();  // 세션 무효화
        }
        return "redirect:/login";  // 로그인 페이지로 리디렉션
    }

    // 메인 페이지
    @GetMapping("/main")
    public String showMainPage() {
        return "main";  // 로그인된 사용자만 접근할 수 있는 페이지
    }
}

