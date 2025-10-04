package com.example.demo.Controller.creatorinvestor;

import com.example.demo.DTO.Apireponsi;
import com.example.demo.Entity.Favourites;
import com.example.demo.Entity.Transaction;
import com.example.demo.Entity.TransactionHistory;
import com.example.demo.Entity.Users;
import com.example.demo.Sercive.TransactionHistoryService;
import com.example.demo.Sercive.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/TransactionHistory")
@RequiredArgsConstructor
public class TransactionHistoryController {
    private final TransactionHistoryService transactionHistoryService;
    private final UserService userService;

    @GetMapping("/my")
    public ResponseEntity<Apireponsi<List<TransactionHistory>>> getMyTransactions() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        Users user = userService.getUserByUsername(username);
        try {
            List<TransactionHistory> mylist = transactionHistoryService.GetTransactionHistoryUser_id(user.getUserId());
            Apireponsi<List<TransactionHistory>> repon = new Apireponsi<>(HttpStatus.OK, "Lịch Sử nạp rút tiền", mylist, null);
            return ResponseEntity.status(HttpStatus.OK).body(repon);
        } catch (Exception e) {
            Apireponsi<List<TransactionHistory>> erorr = new Apireponsi(HttpStatus.BAD_REQUEST, "erorr", (Object) null, (String) null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erorr);
        }
    }
}
