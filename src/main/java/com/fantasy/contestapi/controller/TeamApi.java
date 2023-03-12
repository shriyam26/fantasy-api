package com.fantasy.contestapi.controller;

import com.fantasy.contestapi.schemaobject.SaveTeamSo;
import com.fantasy.contestapi.schemaobject.SearchTeamResponseSo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Team")
@RequestMapping("/team")
public interface TeamApi {

    @Operation(summary = "Save Team Info", description = "Save Team Details")
    @ApiResponse(responseCode = "200", description = "Saved successfully")
    @ApiResponse(responseCode = "400", description = "Bad input parameter")
    @PostMapping(produces = {"application/json"})
    void saveTeam(@Parameter(name = "Search Team Details", required = true) @Valid @RequestBody List<SaveTeamSo> saveTeamSoList);

    @Operation(summary = "Get Team list", description = "Get Player List")
    @ApiResponse(responseCode = "200", description = "Results fetched successfully")
    @ApiResponse(responseCode = "400", description = "Bad input parameter")
    @GetMapping(produces = {"application/json"})
    ResponseEntity<List<SearchTeamResponseSo>> getTeamList();
}