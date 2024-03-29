package com.fantasy.contestapi.controller;

import com.fantasy.contestapi.schemaobject.AmendPlayerSo;
import com.fantasy.contestapi.schemaobject.SavePlayerSo;
import com.fantasy.contestapi.schemaobject.SearchPlayerResponseSo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Operation(summary = "Get Player List", description = "Get Player List")
    @ApiResponse(responseCode = "200", description = "Results fetched successfully")
    @ApiResponse(responseCode = "400", description = "Bad input parameter")
    @GetMapping(produces = {"application/json"})
    ResponseEntity<List<SearchPlayerResponseSo>> getPlayerList();

    @Operation(summary = "Update Player List", description = "Update Player List")
    @ApiResponse(responseCode = "200", description = "Results fetched successfully")
    @ApiResponse(responseCode = "400", description = "Bad input parameter")
    @PutMapping(produces = {"application/json"})
    void updatePlayer(@Parameter(name = "Player Details So", required = true) @Valid @RequestBody List<AmendPlayerSo> amendPlayerSoList);
}
