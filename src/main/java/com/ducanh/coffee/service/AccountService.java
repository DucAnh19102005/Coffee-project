package com.ducanh.coffee.service;

import com.ducanh.coffee.entity.Account;
import com.ducanh.coffee.repository.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    public Account authenticate(String email, String password) {
        Account acc = accountRepo.findByEmail(email);

        if(acc == null){
            return null; //TODO: return DTO đặc biệt mang ý nghĩa 1 email k tồn tại, ném ngoại lệ X nào đó đại diện email k tồn tại
        }

        if(!acc.getPassword().equals(password)){    //TODO: decode password
            return null; //TODO: return DTO mang ý nghĩa sai pass, ngoại lệ Y
        }

        //if thêm acc.getActive() == false

        if(acc.getRole() == 3){
            return null; //TODO: ngoa lệ Z
        }

        return acc;
    }

    public void saveAccount(Account account){
        accountRepo.save(account);
    }

}
