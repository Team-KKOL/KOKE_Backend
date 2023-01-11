package com.koke.koke_backend.file.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.koke.koke_backend.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class FileMst extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(length = 30)
    private Long fileMstId;

    @OneToMany(mappedBy = "fileMst")
    @JsonManagedReference
    private List<FileInfo> fileInfos;

}
