package com.edstem.ProjectManagement.contract;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookResponse {


    private Long id;
    @NotBlank(message = "Title can't be blank")
    @Size(min = 3, message = "title must be minimum 3 characters")
    private String title;

    @NotBlank(message = "Author can't be blank")
    private String author;

    @Min(value = 1, message = "ISBN must be a positive value")
    private long isbn;

    @PastOrPresent(message = "Publication date must be in past or present")
    private LocalDate publicationDate;
}
