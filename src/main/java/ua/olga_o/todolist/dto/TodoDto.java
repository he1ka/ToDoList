package ua.olga_o.todolist.dto;

import lombok.Builder;
import lombok.Getter;
import ua.olga_o.todolist.model.Status;

@Builder
@Getter
public class TodoDto {
    private Integer id;
    private String description;
    private Status status;
}
