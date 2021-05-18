package com.ataccama.dbrowser.controller;

import com.ataccama.dbrowser.dto.ObjectDto;
import com.ataccama.dbrowser.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dbrowser/v1/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    /**
     * Method to get statistic for specified column. Works only for tables from schema specified in connection object.
     *
     * @param id - id of connection which kept in DB of service
     * @param tableName - table for column of which statistic will be returned
     * @param columnName - column for which statistic will be returned
     * @return ObjectDto instance which contain 4 attributes: min value, max value, avg value and median value of the specified column
     */
    @GetMapping("column")
    @ResponseStatus(value = HttpStatus.OK)
    public ObjectDto getTableColumnStatistic(
            @RequestParam("connectionId") Long id,
            @RequestParam("tableName") @NotBlank String tableName,
            @RequestParam("columnName") @NotBlank String columnName) {
        return statisticService.getColumnStatistic(id, tableName, columnName);
    }

    /**
     * Methos to get statistic for specified table
     *
     * @param id - id of connection which kept in DB of service
     * @param schemaName - schema for table of which statistic will be returned
     * @param tableName - table for which statistic will be returned
     * @return ObjectDto instance which contain 2 attributes: count of records and count of attributes of the specified table
     */
    @GetMapping("table")
    @ResponseStatus(value = HttpStatus.OK)
    public ObjectDto getTableStatistic(
            @RequestParam("connectionId") Long id,
            @RequestParam("schemaName") @NotBlank String schemaName,
            @RequestParam("tableName") @NotBlank String tableName) {
        return statisticService.getTableStatistic(id, schemaName, tableName);
    }
}
