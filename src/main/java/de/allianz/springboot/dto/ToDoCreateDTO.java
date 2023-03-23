package de.allianz.springboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@RequiredArgsConstructor
@Getter
@ToString
public class ToDoCreateDTO {
    @NotBlank(message = "name is mandatory")
    private final String name;

    @NotBlank(message = "date is mandatory")
    private final String date;

    @NotNull
    private final Boolean status = false;

}
