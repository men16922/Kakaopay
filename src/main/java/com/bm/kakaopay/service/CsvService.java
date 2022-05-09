package com.bm.kakaopay.service;

import com.bm.kakaopay.domain.Account;
import com.bm.kakaopay.domain.AccountHistory;
import com.bm.kakaopay.domain.KakaoUser;
import com.bm.kakaopay.exception.CsvException;
import com.bm.kakaopay.repository.AccountHistoryRepository;
import com.bm.kakaopay.repository.AccountRepository;
import com.bm.kakaopay.repository.KakaoUserRepository;
import com.bm.kakaopay.util.CsvParser;
import com.bm.kakaopay.util.CsvReader;
import com.opencsv.exceptions.CsvValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import javax.transaction.TransactionalException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CsvService {

    private final KakaoUserRepository kakaoUserRepository;

    private final AccountRepository accountRepository;

    private final AccountHistoryRepository accountHistoryRepository;

    private final CsvReader csvReader;

    private final CsvParser csvParser;

    public List<String[]> read(InputStream inputStream){
        try {
            return csvReader.read(inputStream);
        } catch (IOException | CsvValidationException e) {
            log.error("CSV file read error : ", e);
            throw new CsvException(e);
        }
    }

    @Transactional(rollbackFor = TransactionalException.class)
    public void saveUser(List<String[]> csvFileRows) {

        List<List<String>> csvBody = csvParser.parseBody(csvFileRows);

        for(List<String> row : csvBody) {
            Long id = Long.parseLong(row.get(0));
            String name = row.get(1);
            int age = Integer.parseInt(StringUtils.trim(row.get(2)));
            LocalDate time = LocalDate.parse(row.get(3), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            log.info("ID -{}, name -{}, age -{}, time -{}", id, name, age, time);

            KakaoUser kakaoUser = KakaoUser.builder()
                    .userId(id)
                    .name(name)
                    .age(age)
                    .createAt(time)
                    .build();

            kakaoUserRepository.save(kakaoUser);
        }

    }

    @Transactional(rollbackFor = TransactionalException.class)
    public void saveAccount(List<String[]> csvFileRows) {

        List<List<String>> csvBody = csvParser.parseBody(csvFileRows);

        for(List<String> row : csvBody) {
            Long id = Long.parseLong(row.get(0));
            String userAccount = row.get(1);
            log.info("ID -{}, userAccount -{}", id, userAccount);

            Account account = Account.builder()
                    .userId(id)
                    .userAccount(userAccount)
                    .build();

            accountRepository.save(account);
        }

    }

    @Transactional(rollbackFor = TransactionalException.class)
    public void saveAccountHistory(List<String[]> csvFileRows) {

        List<List<String>> csvBody = csvParser.parseBody(csvFileRows);

        for(List<String> row : csvBody) {
            String userAccount = row.get(0);
            String is_tsmt = row.get(1);
            Float amount = Float.parseFloat(StringUtils.trim(row.get(2)));
            LocalDate time = LocalDate.parse(row.get(3), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            log.info("userAccount -{}, is_tsmt -{}, amount -{}, time -{}", userAccount, is_tsmt, amount, time);

            AccountHistory accountHistory = AccountHistory.builder()
                    .userAccount(userAccount)
                    .is_tsmt(is_tsmt)
                    .amount(amount)
                    .createAt(time)
                    .build();

            accountHistoryRepository.save(accountHistory);
        }

    }
}

