package com.ataccama.dbrowser.controller;

import com.ataccama.dbrowser.dto.ConnectionDto;
import com.ataccama.dbrowser.service.ConnectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dbrowser/v1/connection")
public class ConnectionController {

    private final ConnectionService connectionService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ConnectionDto create(@RequestBody @Valid @NotNull ConnectionDto connectionDto) {
        return connectionService.create(connectionDto);
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ConnectionDto update(@RequestBody @Valid @NotNull ConnectionDto connectionDto) {
        return connectionService.update(connectionDto);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(
            @RequestParam("id") @NotNull Long id) {
        connectionService.delete(id);
    }

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ConnectionDto get(@RequestParam("id") @NotNull Long id) {
        return connectionService.getConnectionById(id);
    }
}
