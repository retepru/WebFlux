package org.astonLearning.WebFlux.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;
import reactor.util.annotation.NonNull;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Url {
    @Id
    private Long id;

    @NonNull
    private String url;

//    @NonNull
    private String token;

    private LocalDate dateAdd;

    @NonNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateEnd;

    public Url(@NonNull String url, @NonNull LocalDate dateEnd) {
        this.url = url;
        this.dateEnd = dateEnd;
        this.dateAdd = LocalDate.now();
    }
}
