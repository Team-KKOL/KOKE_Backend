package com.koke.koke_backend.fav.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum FavType {

    PRODUCT("상품"),
    ROASTERY("로스터리");

    private String hanNm;

}
