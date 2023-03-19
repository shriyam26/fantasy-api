package com.fantasy.contestapi.controller;

import com.fantasy.contestapi.schemaobject.PerformanceResultSo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Result")
@RequestMapping("/results")
public interface ResultApi {

    @Operation(summary = "Get Result Details", description = "Get Result Details")
    @ApiResponse(responseCode = "200", description = "Saved successfully")
    @ApiResponse(responseCode = "400", description = "Bad input parameter")
    @PostMapping(produces = {"application/json"})
    ResponseEntity<List<PerformanceResultSo>> getPerformanceResults(@Parameter(name = "Get Result Details", required = true) @Valid @RequestBody List<Long> playerIds);
}
