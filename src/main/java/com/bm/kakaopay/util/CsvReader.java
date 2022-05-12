package com.bm.kakaopay.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Csv 읽기용 유틸
 */
@Component
public class CsvReader {

    public List<String[]> read(final InputStream stream) throws IOException, CsvValidationException {
        List<String[]> results = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new InputStreamReader(stream))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                results.add(nextLine);
            }
        }

        return results;
    }
}
