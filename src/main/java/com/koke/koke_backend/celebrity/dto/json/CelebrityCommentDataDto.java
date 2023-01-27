package com.koke.koke_backend.celebrity.dto.json;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder(toBuilder = true)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CelebrityCommentDataDto {

    private String img;
    private String title;
    private String detail;
    private String name;
    private String job;
    private String instad;


}
