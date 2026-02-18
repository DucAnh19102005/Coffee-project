package com.ducanh.coffee.repository;

import com.ducanh.coffee.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {

    Account findByEmail(String email);
}
