package com.koke.koke_backend.address.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.koke.koke_backend.common.entity.BaseIdEntity;
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

@SuperBuilder(toBuilder = true)
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table
public class AddressBook extends BaseIdEntity {

    @JsonManagedReference
    @Builder.Default
    @OneToMany(mappedBy = "addressBook",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Address> addresses = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "userId", referencedColumnName = "id")
    @Comment("사용자 ID")
    private User user;

}
