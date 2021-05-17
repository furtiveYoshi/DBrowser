package com.ataccama.dbrowser.controller;

import com.ataccama.dbrowser.service.BrowserDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dbrowser/v1/browserData")
public class BrowserDataController {

    private final BrowserDataService browserDataService;

    @GetMapping("schema")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity getSchemas(
            @RequestParam("connectionId") Long id,
            @RequestParam(value = "schemaName", required = false) String schemaName) {
        return ResponseEntity.ok(browserDataService.getSchemas(id, schemaName));
    }

    @GetMapping("table")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity getTables(
            @RequestParam("connectionId") Long id,
            @RequestParam(value = "schemaName", required = false) String schemaName) {
        return ResponseEntity.ok(browserDataService.getTables(id, schemaName));
    }

    @GetMapping("column")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity getColumns(
            @RequestParam("connectionId") Long id,
            @RequestParam(value = "tableName", required = false) String tableName) {
        return ResponseEntity.ok(browserDataService.getColumns(id, tableName));
    }

    @GetMapping("tablePreview")
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity getTablePreview(
            @RequestParam("connectionId") Long id,
            @RequestParam("tableName") @NotBlank String tableName) {
        return ResponseEntity.ok(browserDataService.getTablePreview(id, tableName));
    }
}
