package ua.olga_o.todolist.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TaskCountDto {
    private Long count;
}
