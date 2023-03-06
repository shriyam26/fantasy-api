package com.fantasy.contestapi.controller;

import com.fantasy.contestapi.schemaobject.SaveTeamSo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Team")
@RequestMapping("/team")
public interface TeamApi {

    @Operation(summary = "Save Team Info", description = "Save Team Details")
    @ApiResponse(responseCode = "200", description = "Saved successfully")
    @ApiResponse(responseCode = "400", description = "Bad input parameter")
    @PostMapping(produces = {"application/json"})
    void saveTeam(SaveTeamSo saveTeamSo);
}
