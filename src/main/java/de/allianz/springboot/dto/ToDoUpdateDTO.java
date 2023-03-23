package de.allianz.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class ToDoUpdateDTO {

    @NotNull
    @Positive
    private final Long id;

    @NotBlank(message = "name is mandatory")
    private final String name;
    @NotBlank(message = "date is mandatory")
    private final String date;

    @NotNull
    private final Boolean status;

}
