package com.koke.koke_backend.roastery.entity;

import com.koke.koke_backend.common.entity.BaseEntity;
import com.koke.koke_backend.file.entity.FileMst;
import jakarta.persistence.*;
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
    private String roasteryId;

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

    @OneToOne
    @JoinColumn(name = "logoImgId", referencedColumnName = "logoImgId")
    private FileMst logoImg;

    @OneToOne
    @JoinColumn(name = "photoImgId", referencedColumnName = "fileMstId")
    private FileMst photoImg;

}
