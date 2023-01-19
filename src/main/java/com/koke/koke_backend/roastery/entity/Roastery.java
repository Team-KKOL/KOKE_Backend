package com.koke.koke_backend.roastery.entity;

import com.koke.koke_backend.common.converter.StringListConverter;
import com.koke.koke_backend.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.util.List;

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

    @Column(length = 30, nullable = false)
    @Comment("로스터리 카페명")
    private String roasteryNm;

    @Column(columnDefinition = "MEDIUMTEXT NOT NULL")
    @Convert(converter = StringListConverter.class)
    @Comment("로스터리 카페 소개")
    private List<String> description;

    @Column(columnDefinition = "MEDIUMTEXT")
    @Convert(converter = StringListConverter.class)
    @Comment("로스터리 카페 수상 내역")
    private List<String> awards;

    @Column(length = 200, nullable = false)
    @Comment("로스터리 카페 주소")
    private String location;

    @Column(length = 300)
    @Comment("로스터리 카페 웹 URL")
    private String webUrl;

    @Column(length = 300)
    @Comment("로스터리 카페 SNS URL")
    private String snsUrl;

    @Column(length = 300)
    @Comment("로스터리 카페 로고 이미지 URL")
    private String logoImgUrl;

    @Column(columnDefinition = "MEDIUMTEXT")
    @Convert(converter = StringListConverter.class)
    @Comment("로스터리 카페 이미지 URL")
    private List<String> photoImgUrl;

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
