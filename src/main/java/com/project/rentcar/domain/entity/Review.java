package com.project.rentcar.domain.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewNumber;

    @Column(name = "title" , nullable = false)
    private String title;

    @Column(name = "content" , nullable = false)
    private String content;

    @Column(name = "rating" , nullable = false)
    private int rating;     // 평가

    @Column(name = "date" , nullable = false)
    private LocalDate date;

    @Lob
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", foreignKey = @ForeignKey(name = "FK_REVIEW_USER",
    foreignKeyDefinition = "FOREIGN KEY(UserId) REFERENCES user(userId) ON DELETE CASCADE ON UPDATE CASCADE"))
    private User user;
}
