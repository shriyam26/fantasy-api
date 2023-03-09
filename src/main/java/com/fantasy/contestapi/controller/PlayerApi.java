package com.fantasy.contestapi.controller;

import com.fantasy.contestapi.schemaobject.SavePlayerSo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Player")
@RequestMapping("/player")
public interface PlayerApi {

    @Operation(summary = "Save Player Info", description = "Save Player Details")
    @ApiResponse(responseCode = "200", description = "Saved successfully")
    @ApiResponse(responseCode = "400", description = "Bad input parameter")
    @PostMapping(produces = {"application/json"})
    void savePlayer(@Parameter(name = "Save Player Details", required = true) @Valid @RequestBody List<SavePlayerSo> savePlayerSoList);
}
