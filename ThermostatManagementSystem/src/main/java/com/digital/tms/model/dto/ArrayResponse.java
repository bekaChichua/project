package com.digital.tms.model.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ArrayResponse<T> {
    private List<T> data;
}
