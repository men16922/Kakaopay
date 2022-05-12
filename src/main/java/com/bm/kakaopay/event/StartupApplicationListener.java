package com.bm.kakaopay.event;

import com.bm.kakaopay.exception.CsvException;
import com.bm.kakaopay.service.CsvService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Application 시작시 csv file 읽어 h2 db에 저장
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StartupApplicationListener implements
        ApplicationListener<ContextRefreshedEvent> {

    private final CsvService csvService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("--- READ CSV FILE ---");

        List<String[]> csvFileRows = new ArrayList<>();

        log.info("--- READ 사용자 CSV FILE ---");
        ClassPathResource classPathResource = new ClassPathResource("사용자.csv");

        try {
            csvFileRows = csvService.read(classPathResource.getInputStream());
            csvService.saveUser(csvFileRows);

        } catch (Exception e) {
            log.error("CSV file read error : ", e);
            throw new CsvException(e);
        }

        log.info("--- READ 계좌 CSV FILE ---");

        classPathResource = new ClassPathResource("계좌.csv");

        try {
            csvFileRows = csvService.read(classPathResource.getInputStream());
            csvService.saveAccount(csvFileRows);

        } catch (Exception e) {
            log.error("CSV file read error : ", e);
            throw new CsvException(e);
        }

        log.info("--- READ 계좌내역 CSV FILE ---");

        classPathResource = new ClassPathResource("계좌내역.csv");

        try {
            csvFileRows = csvService.read(classPathResource.getInputStream());
            csvService.saveAccountHistory(csvFileRows);

        } catch (Exception e) {
            log.error("CSV file read error : ", e);
            throw new CsvException(e);
        }

    }
}

