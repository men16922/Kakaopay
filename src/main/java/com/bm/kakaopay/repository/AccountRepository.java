package com.bm.kakaopay.repository;

import com.bm.kakaopay.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * 계좌 Repository
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUserId(Long userId);

    Optional<Account> findByUserAccount(String userAccount);

    List<Account> findAllByUserId(Long userId);
}
