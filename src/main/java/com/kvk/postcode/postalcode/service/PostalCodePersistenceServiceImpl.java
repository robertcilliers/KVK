package com.kvk.postcode.postalcode.service;

import com.kvk.postcode.postalcode.entity.PostalCodeEntity;
import com.kvk.postcode.postalcode.repository.PostalCodeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostalCodePersistenceServiceImpl implements PostalCodePersistenceService{

    private final PostalCodeRepository postalCodeRepository;

    public PostalCodePersistenceServiceImpl(PostalCodeRepository postalCodeRepository) {
        this.postalCodeRepository = postalCodeRepository;
    }

    @Override
    public Optional<PostalCodeEntity> getPostalCodeDetails(String code) {
        return postalCodeRepository.findById(code.toUpperCase());

    }

    @Override
    @Transactional
    public void addPostalCodeDetails(PostalCodeEntity postalCodeEntity) {
        postalCodeRepository.save(postalCodeEntity);
    }
}
