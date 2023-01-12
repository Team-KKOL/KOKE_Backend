package com.koke.koke_backend.address.entity;

import com.koke.koke_backend.common.entity.BaseTimeEntity;
import com.koke.koke_backend.user.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Comment;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class AddressBook extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Comment("주소록 ID")
    private Integer id;

    @Builder.Default
    @OneToMany(mappedBy = "addressBook",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "userSeq", referencedColumnName = "userSeq")
    @Comment("사용자 PK")
    private User user;

}
