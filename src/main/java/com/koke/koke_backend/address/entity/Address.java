package com.koke.koke_backend.address.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.koke.koke_backend.common.entity.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import static jakarta.persistence.FetchType.LAZY;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Address extends BaseTimeEntity {

    @Column(length = 100)
    @Comment("주소명")
    private String name;

    @Column(length = 100)
    @Comment("도로명 주소")
    private String roadNameAddress;

    @Column(length = 100)
    @Comment("상세 주소")
    private String detailedAddress;

    @Column(length = 10, nullable = false)
    @Comment("우편 번호")
    private String postalCode;

    @JsonBackReference
    @ManyToOne(fetch = LAZY)
    @JoinColumn(nullable = false, name = "addressBookId", referencedColumnName = "id")
    private AddressBook addressBook;

}
