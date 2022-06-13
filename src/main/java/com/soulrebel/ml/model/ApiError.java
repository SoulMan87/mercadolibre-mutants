package com.soulrebel.ml.model;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class  ApiError {

    private String code;
    private String description;

}
