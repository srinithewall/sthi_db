package com.sthi.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReportRequest {
    @NotBlank private String name;
    @NotBlank private String gender;
    @NotNull private LocalDate birthDate;
    @NotNull private LocalTime birthTime;
    @NotBlank private String birthPlace;
    @NotBlank @Pattern(regexp = "en|hi") private String reportLanguage;
}