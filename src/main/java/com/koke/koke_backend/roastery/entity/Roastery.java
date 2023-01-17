package com.koke.koke_backend.roastery.entity;

import com.koke.koke_backend.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Roastery extends BaseEntity {

    @Id
    @Column(length = 40)
    @Comment("로스터리 카페 ID")
    private String id;

    @Column(length = 20, nullable = false)
    @Comment("로스터리 카페명")
    private String roasteryNm;

    @Column(length = 500, nullable = false)
    @Comment("로스터리 카페 소개")
    private String description;

    @Column(length = 50, nullable = false)
    @Comment("로스터리 카페 주소")
    private String location;

    @Column(length = 50)
    @Comment("로스터리 카페 SNS URL")
    private String snsUrl;

    @Column(length = 100)
    @Comment("로스터리 카페 로고 이미지 URL")
    private String logoImgUrl;

    @Column(length = 100)
    @Comment("로스터리 카페 이미지 URL")
    private String photoImgUrl;

//    @OneToOne
//    @JoinColumn(name = "logoImgId", referencedColumnName = "fileMstId")
//    @Comment("로스터리 카페 로고 이미지 ID")
//    private FileMst logoImg;
//
//    @OneToOne
//    @JoinColumn(name = "photoImgId", referencedColumnName = "fileMstId")
//    @Comment("로스터리 카페 이미지 ID")
//    private FileMst photoImg;

}
