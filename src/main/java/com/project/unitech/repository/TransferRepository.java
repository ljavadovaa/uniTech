package com.project.unitech.repository;

import com.project.unitech.entity.Transfer;
import com.project.unitech.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransferRepository extends JpaRepository<Transfer, Long> {
    User findUserBySenderAccountNumber(String senderAccountNumber);
}
