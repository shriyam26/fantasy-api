package com.fantasy.contestapi.controller;

import com.fantasy.contestapi.entity.Contest;
import com.fantasy.contestapi.schemaobject.AmendContestSo;
import com.fantasy.contestapi.schemaobject.ContestPerformanceSo;
import com.fantasy.contestapi.schemaobject.SaveContestSo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Contest")
@RequestMapping("/contest")
public interface ContestApi {

    @Operation(summary = "Save Contest Info", description = "Save Contest Details")
    @ApiResponse(responseCode = "200", description = "Saved successfully")
    @ApiResponse(responseCode = "400", description = "Bad input parameter")
    @PostMapping(produces = {"application/json"})
    void saveContest(@Parameter(name = "Save Contest Information", required = true) @Valid @RequestBody SaveContestSo saveContestSo);

    @Operation(summary = "Update Contest List", description = "Update Contest List")
    @ApiResponse(responseCode = "200", description = "Results fetched successfully")
    @ApiResponse(responseCode = "400", description = "Bad input parameter")
    @PutMapping(produces = {"application/json"})
    void updateContest(@Parameter(name = "Contest Details So", required = true) @Valid @RequestBody List<AmendContestSo> amendContestSo);

    @Operation(summary = "Get Contest List", description = "Get Contest List")
    @ApiResponse(responseCode = "200", description = "Results fetched successfully")
    @ApiResponse(responseCode = "400", description = "Bad input parameter")
    @PostMapping(value = {"/getContestDetails"}, produces = {"application/json"})
    ResponseEntity<List<Contest>> getContest(@Parameter(name = "Contest Details So", required = true) @Valid @RequestBody List<Long> contestIds);

    @Operation(summary = "Get Contest Winner Details", description = "Get Contest Winner Details")
    @ApiResponse(responseCode = "200", description = "Results fetched successfully")
    @ApiResponse(responseCode = "400", description = "Bad input parameter")
    @GetMapping(value = {"/getContest"}, produces = {"application/json"})
    ResponseEntity<List<ContestPerformanceSo>> fetchContestWinnerDetails();
}