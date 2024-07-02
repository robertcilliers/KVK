package com.kvk.postcode.postalcode.utils;

import com.google.gson.JsonObject;
import com.kvk.postcode.postalcode.dao.PostalCodeDAO;
import com.kvk.postcode.postalcode.entity.PostalCodeEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ObjectMapper {

    public PostalCodeEntity mapToEntity(String code, PostalCodeDAO postalCodeDAO) {
        if (Objects.nonNull(postalCodeDAO)) {
            return new PostalCodeEntity(code.toUpperCase(), postalCodeDAO.getFormat(), postalCodeDAO.getRegex(), postalCodeDAO.getCountryName());
        } else {
            return null;
        }
    }

    public PostalCodeDAO mapToDAO(PostalCodeEntity postalCodeEntity) {
        if (Objects.nonNull(postalCodeEntity)) {
            return new PostalCodeDAO(postalCodeEntity.getCountryName(), postalCodeEntity.getFormat(), postalCodeEntity.getRegex());
        } else {
            return null;
        }
    }

    public PostalCodeDAO mapToDAO(String code, JsonObject jsonObject) {
        if (Objects.nonNull(jsonObject)) {
            return new PostalCodeDAO(jsonObject.getAsJsonObject("name").get("common").getAsString(), jsonObject.getAsJsonObject("postalCode").get("format").getAsString(), jsonObject.getAsJsonObject("postalCode").get("regex").getAsString());
        } else {
            return null;
        }
    }
}
