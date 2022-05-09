package com.bm.kakaopay.util;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class CsvParser {
    private static final int COLUMN_START = 0;
    private static final int ROW_START = 1;
    private static final String SPACES_REGEX = "^[\\s]*$";
    private static final Pattern spacePattern = Pattern.compile(SPACES_REGEX);

    private int getNumberOfSpaceInPrefix(final String[] rows) {
        int numberOfSpaceInPrefix = 0;

        for (String row : rows) {
            Matcher matcher = spacePattern.matcher(row);
            if (!"".equals(row) && !matcher.find()) {
                break;
            }

            numberOfSpaceInPrefix++;
        }

        return numberOfSpaceInPrefix;
    }

    private int getNumberOfSpaceInSuffix(final String[] rows) {
        int numberOfSpaceInSuffix = 0;

        for (int index = rows.length - 1; index >= 0; --index) {
            String row = rows[index];
            Matcher matcher = spacePattern.matcher(row);
            if (!"".equals(row) && !matcher.find()) {
                break;
            }

            numberOfSpaceInSuffix++;
        }

        return numberOfSpaceInSuffix;
    }

    public List<List<String>> parseBody(final List<String[]> csvFileData) {
        List<List<String>> body = new ArrayList<>(new ArrayList<>());

        for (String[] row : csvFileData.subList(ROW_START, csvFileData.size())) {
            int startIndexWithoutSpace = getNumberOfSpaceInPrefix(row);
            int endIndexWithoutSpace = row.length - getNumberOfSpaceInSuffix(row);

            body.add(new ArrayList<>(Arrays.asList(row)
                    .subList(startIndexWithoutSpace, endIndexWithoutSpace)));
        }

        return body;
    }
}
