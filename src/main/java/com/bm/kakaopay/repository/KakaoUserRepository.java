package com.bm.kakaopay.repository;

import com.bm.kakaopay.domain.KakaoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 사용자 Repository
 */
@Repository
public interface KakaoUserRepository extends JpaRepository<KakaoUser, Long> {

}

