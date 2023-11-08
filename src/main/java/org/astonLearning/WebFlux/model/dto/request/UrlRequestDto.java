package org.astonLearning.WebFlux.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlRequestDto {

    private String url;

    private LocalDate dateEnd; // todo нужна проверка нахождения даты в будущем
}
