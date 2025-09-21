package com.example.phase1.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CheckRequest {
    @NotNull(message = "value must not be null")
    private Integer value;
}
