package com.project.rentcar.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@Table(name = "user")
public class User {

    @Id
    @Column(name = "memberId")  // DB 컬럼명 'member_id'에 매핑
    private String memberId;      // Java 필드명은 'memberId'

    @Column(name = "memberPw")   // DB 컬럼명 'member_pw'에 매핑
    private String memberPw;      // Java 필드명은 'memberPw'

    @Column(name = "memberSalt") // DB 컬럼명 'member_salt'에 매핑
    private String memberSalt;    // Java 필드명은 'memberSalt'

    @Column(name = "memberNm")   // DB 컬럼명 'member_nm'에 매핑
    private String memberNm;      // Java 필드명은 'memberNm'

    @Column(name = "memberEmail")// DB 컬럼명 'member_email'에 매핑
    private String memberEmail;   // Java 필드명은 'memberEmail'

    // 기본 생성자
    public User() {}

    @Builder
    public User(String memberId, String memberPw, String memberSalt, String memberNm, String memberEmail) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberSalt = memberSalt;
        this.memberNm = memberNm;
        this.memberEmail = memberEmail;
    }

    // Getter and Setter 생략
}
