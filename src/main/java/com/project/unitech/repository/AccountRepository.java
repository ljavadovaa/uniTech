package com.project.unitech.repository;

import com.project.unitech.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<UserAccount, Long> {
    List<UserAccount> findAccountsByUserId(Long userId);

}