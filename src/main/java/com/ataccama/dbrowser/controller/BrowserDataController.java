package com.ataccama.dbrowser.controller;

import com.ataccama.dbrowser.dto.ObjectDto;
import com.ataccama.dbrowser.service.BrowserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dbrowser/v1/browserData")
public class BrowserDataController {

    private final BrowserDataService browserDataService;

    @GetMapping("schema")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ObjectDto> getSchemas(
            @RequestParam("connectionId") Long id,
            @RequestParam(value = "schemaName", required = false) String schemaName) {
        return browserDataService.getSchemas(id, schemaName);
    }

    @GetMapping("table")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ObjectDto> getTables(
            @RequestParam("connectionId") Long id,
            @RequestParam(value = "schemaName", required = false) String schemaName) {
        return browserDataService.getTables(id, schemaName);
    }

    @GetMapping("column")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ObjectDto> getColumns(
            @RequestParam("connectionId") Long id,
            @RequestParam(value = "tableName", required = false) String tableName) {
        return browserDataService.getColumns(id, tableName);
    }

    @GetMapping("tablePreview")
    @ResponseStatus(value = HttpStatus.OK)
    public List<ObjectDto> getTablePreview(
            @RequestParam("connectionId") Long id,
            @RequestParam("tableName") @NotBlank String tableName) {
        return browserDataService.getTablePreview(id, tableName);
    }
}
