package com.koke.koke_backend.file.entity;

import com.koke.koke_backend.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class FileDtl extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(length = 20)
    private Long fileDtlId;

    @Column(length = 200, nullable = false)
    private String originFileNm;

    @Column(length = 200, nullable = false)
    private String uploadFileNm;

    @Column(length = 300, nullable = false)
    private String fileUrl;

    @Column(length = 20, nullable = false)
    private Integer fileSize;

    @Column(length = 30, nullable = false)
    private String extension;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "fileMstId", referencedColumnName = "fileMstId")
    private FileMst fileMst;

}
