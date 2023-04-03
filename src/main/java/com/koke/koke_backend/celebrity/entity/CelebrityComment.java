package com.koke.koke_backend.celebrity.entity;

import com.koke.koke_backend.common.entity.BaseIdEntity;
import com.koke.koke_backend.common.jpa.StringListConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
public class CelebrityComment extends BaseIdEntity {

    @Column(nullable = false, length = 10)
    @Comment("셀럽 이름")
    private String celebrityName;

    @Column(nullable = false, length = 30)
    @Comment("셀럽 직업")
    private String celebrityJob;

    @Column(nullable = false, length = 300)
    @Comment("셀럽 프로필 이미지 URL")
    private String celebrityImgUrl;

    @Column(nullable = false, length = 50)
    @Comment("추천사 제목")
    private String title;

    @Column(nullable = false, length = 200)
    @Convert(converter = StringListConverter.class)
    @Comment("추천사 내용")
    private List<String> comment;

    @Column(nullable = false, length = 30)
    @Comment("셀럽 Instagram ID")
    private String instagramId;

}
