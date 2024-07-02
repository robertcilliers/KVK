package com.kvk.postcode.postalcode.controller;

import com.kvk.postcode.postalcode.exception.AddPostalCodeException;
import com.kvk.postcode.postalcode.exception.HttpRequestException;
import com.kvk.postcode.postalcode.exception.PostalCodeNotFoundException;
import com.kvk.postcode.postalcode.service.PostalCodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/postal-code")
public class PostalCodeController {

    private final PostalCodeService postalCodeService;

    public PostalCodeController(PostalCodeService postalCodeService) {
        this.postalCodeService = postalCodeService;
    }

    @GetMapping("/{code}")
    public ResponseEntity<Object> getPostalCodeDetails(@PathVariable String code) {
        try {
            return ResponseEntity.ok().body(postalCodeService.getPostalCodeDetails(code));
        }catch (PostalCodeNotFoundException e) {
            return ResponseEntity.ok().body(e.getMessage());
        }
    }

    @PostMapping("/{code}")
    private ResponseEntity<Object> addPostalCode(@PathVariable String code) {
        try{
            return ResponseEntity.ok().body(postalCodeService.addPostalCode(code));
        } catch (HttpRequestException | IOException e) {
            return ResponseEntity.internalServerError().body("Failed to load postal code from remote service");
        } catch (AddPostalCodeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
