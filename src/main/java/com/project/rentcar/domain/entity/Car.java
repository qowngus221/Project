package com.project.rentcar.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "car")
public class Car {

    // Database Table과 메핑될 Class를 설계하면 됨
    // Entity란 명사를 지칭 단순히 말하면 Database에 저장될 정보를 의미


    @Id // Id 는 pk를 의미
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // GenerationValue는 자동생성, 생성정략은 IDENTITY,고유키 생성전략을 사용한다. // IDENTITY는 ID 값이 자동으로 증가
    // 자동으로 숫자나 값을 생성해 주는 기능
    // 쉽게 설명하면
    // 예를 들어 자동차의 ID 번호를 생각해보면 자동차라 새로운 테이블에 추가될때마다 매번 자동차마다 다른 ID번호를 수동으로 입력하는건 번거로우니
    // 그럴때 마다 GeneratedValue 를 사용하면 자동차 ID번호가 자동으로 생성됨
    @Column(name = "Carid")
    private Long Carid;

    @Column(nullable = false, length = 100)
    private String carname;  // 차량 이름 (예: "현대 아반떼")

    @Column(nullable = false, length = 100)
    private String model;  // 차량 모델 (예: "스포츠")

    @Column(nullable = false, length = 100)
    private String manufacturer;  // 제조사 (예: "현대")

    @Column(nullable = false)
    private Integer year;  // 차량 연식 (예: 2020)
    // ���� 가격 (100,000 won)

    @Column(nullable = false, length = 20)
    private String licensePlate;  // 차량 번호판 (예: "12가 3456")

    @Column(nullable = false)
    private Boolean available;  // 차량 대여 가능 여부 (true: 대여 가능, false: 대여 불가)

    private String imageUrl;  // 차량 이미지 URL (선택 사항)


}
