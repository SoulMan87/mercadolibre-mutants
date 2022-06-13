package com.soulrebel.ml.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Status {

    @ApiModelProperty(value = "Code")
    private String code;

    @ApiModelProperty(value = "Message")
    private String message;

}
