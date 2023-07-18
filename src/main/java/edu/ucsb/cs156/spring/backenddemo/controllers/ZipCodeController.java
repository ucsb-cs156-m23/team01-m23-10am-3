package edu.ucsb.cs156.spring.backenddemo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ucsb.cs156.spring.backenddemo.services.ZipCodeQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="ZipCode informaton from https://api.zippopotam.us/")
@RestController
@RequestMapping("/api/zipcode")
public class ZipCodeController {
    
    ObjectMapper mapper = new ObjectMapper();

    @Autowired
    ZipCodeQueryService zipCodeQueryService;

    @Operation(summary = "Get information about a location given a zip code", description = "JSON return format documented here: https://api.zippopotam.us/")
    @GetMapping("/get")
    public ResponseEntity<String> getEarthquakes(
        @Parameter(name="zipCode", description="a location's zip code", example="93101") @RequestParam String zipCode
    ) throws JsonProcessingException {
        String result = zipCodeQueryService.getJSON(zipCode);
        return ResponseEntity.ok().body(result);

    }
}
