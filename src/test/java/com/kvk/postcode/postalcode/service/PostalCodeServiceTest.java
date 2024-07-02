package com.kvk.postcode.postalcode.service;

import com.kvk.postcode.postalcode.dao.PostalCodeDAO;
import com.kvk.postcode.postalcode.entity.PostalCodeEntity;
import com.kvk.postcode.postalcode.exception.PostalCodeNotFoundException;
import com.kvk.postcode.postalcode.repository.PostalCodeRepository;
import com.kvk.postcode.postalcode.utils.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PostalCodeServiceTest {
    @MockBean
    private PostalCodePersistenceService postalCodePersistenceService;

    @Mock
    private PostalCodeRepository postalCodeRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private RemoteService remoteService;

    @Autowired
    private PostalCodeService postalCodeService;


    @Test
    public void getPostalCodeDetailsTest() throws PostalCodeNotFoundException {
        when(postalCodePersistenceService.getPostalCodeDetails("NL")).thenReturn(Optional.of(new PostalCodeEntity("NL","#### @@", "^(\\d{4}[A-Z]{2})$","Netherlands")));
        PostalCodeDAO postalCodeDAO = postalCodeService.getPostalCodeDetails("NL");
        assertThat(postalCodeDAO.getCountryName()).isEqualTo("Netherlands");
    }

    @Test
    public void getPostalCodeDetailsThatDoesNotExist() throws PostalCodeNotFoundException {
        when(postalCodePersistenceService.getPostalCodeDetails("NL")).thenReturn(Optional.empty());
        assertThatExceptionOfType(PostalCodeNotFoundException.class)
                .isThrownBy(() -> postalCodeService.getPostalCodeDetails("NL"))
                .withMessage("Postal code NL + not found!")
                .withNoCause();
    }

    @Test
    public void mapToDaoObjectTest() {
        PostalCodeDAO postalCodeDAO = objectMapper.mapToDAO(new PostalCodeEntity("NL", "#### @@", "^(\\d{4}[A-Z]{2})$", "Netherlands"));
        assertThat(postalCodeDAO.getCountryName()).isEqualTo("Netherlands");
        assertThat(postalCodeDAO.getFormat()).isEqualTo("#### @@");
        assertThat(postalCodeDAO.getRegex()).isEqualTo("^(\\d{4}[A-Z]{2})$");
    }

}
