package com.kvk.postcode.postalcode.service;

import com.google.gson.JsonParser;
import com.kvk.postcode.postalcode.dao.PostalCodeDAO;
import com.kvk.postcode.postalcode.entity.PostalCodeEntity;
import com.kvk.postcode.postalcode.exception.AddPostalCodeException;
import com.kvk.postcode.postalcode.exception.HttpRequestException;
import com.kvk.postcode.postalcode.exception.PostalCodeNotFoundException;
import com.kvk.postcode.postalcode.utils.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class PostalCodeService {

    private final PostalCodePersistenceService postalCodePersistenceService;
    private final ObjectMapper objectMapper;
    private final RemoteService remoteService;
    public PostalCodeService(RemoteService remoteService, PostalCodePersistenceService postalCodePersistenceService, ObjectMapper objectMapper) {
        this.remoteService = remoteService;
        this.postalCodePersistenceService = postalCodePersistenceService;
        this.objectMapper = objectMapper;
    }


    public PostalCodeDAO getPostalCodeDetails(String code) throws PostalCodeNotFoundException {
        Optional<PostalCodeEntity> postalCodeDetails = postalCodePersistenceService.getPostalCodeDetails(code);
        if (postalCodeDetails.isPresent()) {
            return objectMapper.mapToDAO(postalCodeDetails.get());
        } else {
            throw new PostalCodeNotFoundException("Postal code " + code +" + not found!");
        }
    }

    public PostalCodeDAO addPostalCode(String code) throws HttpRequestException, IOException, AddPostalCodeException {
        Optional<PostalCodeEntity> postalCodeDetails = postalCodePersistenceService.getPostalCodeDetails(code);
        if (postalCodeDetails.isEmpty()) {
            //Call our remote service to get the Postal code info
            String response = remoteService.fetchPostalCodeDetails(code);
            PostalCodeDAO postalCodeDAO = objectMapper.mapToDAO(code, JsonParser.parseString(response).getAsJsonArray().get(0).getAsJsonObject());
            postalCodePersistenceService.addPostalCodeDetails(objectMapper.mapToEntity(code, postalCodeDAO));
            return postalCodeDAO;
        } else {
            throw new AddPostalCodeException("Postal code " + code + " has already been added to database.");
        }
    }

}
