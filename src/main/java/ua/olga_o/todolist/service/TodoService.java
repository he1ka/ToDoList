package ua.olga_o.todolist.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ua.olga_o.todolist.dto.TaskCountDto;
import ua.olga_o.todolist.dto.TodoDto;
import ua.olga_o.todolist.entity.TaskEntity;
import ua.olga_o.todolist.mapper.TaskMapper;
import ua.olga_o.todolist.repository.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TodoService {
    private static final int PAGE_SIZE = 10;
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public List<TodoDto> getAll(int page) {
        Pageable pageable = PageRequest.of(page - 1, PAGE_SIZE); // Page numbers start from 1, not 0

        Page<TaskEntity> pageContent = taskRepository.findAll(pageable);

        return pageContent.getContent().stream()
                .map(taskMapper::convert)
                .collect(Collectors.toList());
    }

    public TaskCountDto getTaskCount() {
        return TaskCountDto.builder().count(taskRepository.count()).build();
    }

    public TodoDto create(TodoDto todoDetails) {
        TaskEntity task = new TaskEntity();
        task.setDescription(todoDetails.getDescription());
        task.setStatus(todoDetails.getStatus());

        task = taskRepository.save(task);

        return taskMapper.convert(task);
    }

    public TodoDto update(TodoDto todoDetails) {
        if (todoDetails.getId() == null) {
            throw new RuntimeException("Please provide Task.id");
        }

        TaskEntity taskEntity = taskRepository.findById(todoDetails.getId()).orElseThrow();
        taskEntity.setDescription(todoDetails.getDescription());
        taskEntity.setStatus(todoDetails.getStatus());
        taskRepository.save(taskEntity);

        return taskMapper.convert(taskEntity);
    }

    public void delete(TodoDto todoDetails) {
        if (todoDetails.getId() == null) {
            throw new RuntimeException("Please provide Task.id");
        }

        taskRepository.deleteById(todoDetails.getId());
    }
}
