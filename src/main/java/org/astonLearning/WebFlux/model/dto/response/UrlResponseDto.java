package org.astonLearning.WebFlux.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class UrlResponseDto {

    private String url;

    private String token;

    private LocalDate dateEnd;
}
