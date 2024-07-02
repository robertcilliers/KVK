package com.kvk.postcode.postalcode.service;

import com.kvk.postcode.postalcode.entity.PostalCodeEntity;

import java.util.Optional;

public interface PostalCodePersistenceService {
    Optional<PostalCodeEntity> getPostalCodeDetails(String code);

    void addPostalCodeDetails(PostalCodeEntity postalCodeEntity);
}
