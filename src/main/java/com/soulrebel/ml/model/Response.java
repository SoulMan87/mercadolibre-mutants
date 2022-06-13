package com.soulrebel.ml.model;


import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private Object data;
    private List<Status> status;
}
