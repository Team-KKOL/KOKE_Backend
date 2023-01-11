package com.koke.koke_backend.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Yn {

    Y(true), N(false);

    private boolean bool;

}
