package ua.olga_o.todolist.mapper;

import org.springframework.stereotype.Component;
import ua.olga_o.todolist.dto.TodoDto;
import ua.olga_o.todolist.entity.TaskEntity;

@Component
public class TaskMapper {
    public TodoDto convert(TaskEntity task) {
        return TodoDto.builder()
                .id(task.getId())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
    }
}
