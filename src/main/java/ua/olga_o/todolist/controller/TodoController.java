package ua.olga_o.todolist.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.olga_o.todolist.dto.TaskCountDto;
import ua.olga_o.todolist.dto.TodoDto;
import ua.olga_o.todolist.service.TodoService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/todo")
@AllArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping()
    public List<TodoDto> getAll(@RequestParam(defaultValue = "1") int page) {
        return todoService.getAll(page);
    }

    @GetMapping("/count")
    public TaskCountDto getTotalTaskCount() {
        return todoService.getTaskCount();
    }

    @PostMapping
    public TodoDto create(@RequestBody TodoDto todoDetails) {
        return todoService.create(todoDetails);
    }

    @PutMapping
    public TodoDto update(@RequestBody TodoDto todoDetails) {
        return todoService.update(todoDetails);
    }

    @DeleteMapping
    public void delete(@RequestBody TodoDto todoDetails) {
        todoService.delete(todoDetails);
    }
}
