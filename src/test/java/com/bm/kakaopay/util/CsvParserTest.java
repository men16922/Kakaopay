package com.bm.kakaopay.util;

import com.opencsv.exceptions.CsvValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * CSV 파싱 테스트
 */
class CsvParserTest {

    private final static Logger log = LoggerFactory.getLogger(CsvParserTest.class);

    private CsvParser csvParser = new CsvParser();
    private List<String[]> csvFileRows = new ArrayList<>();

    @BeforeEach
    void setUp() throws IOException, CsvValidationException {
        ClassPathResource classPathResource =
                new ClassPathResource("사용자.csv");
        CsvReader csvFileReader = new CsvReader();
        csvFileRows = csvFileReader.read(classPathResource.getInputStream());
    }

    @Test
    @DisplayName("CSV 파싱 테스트")
    void parse_csv_file_get_body() throws IOException {
        List<List<String>> body = csvParser.parseBody(csvFileRows);

        for (List<String> bodyRow : body) {
            log.info("ROW : " + bodyRow);
        }
    }

}