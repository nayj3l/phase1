package com.example.phase1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckResponse {
    private Integer a;
    private Integer b;
    private Integer c;
    private Double avg;
}
