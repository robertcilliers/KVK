package com.kvk.postcode.postalcode.repository;

import com.kvk.postcode.postalcode.entity.PostalCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostalCodeRepository extends JpaRepository<PostalCodeEntity, String> {
}
