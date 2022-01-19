package com.app.BankSystem.Controller;

import com.app.BankSystem.Model.User;
import com.app.BankSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/list")
    public ResponseEntity<Object> list() {
        return userService.listAllUsers();
    }

    @PostMapping("/add")
    public ResponseEntity<Object> add(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/update")
    public ResponseEntity<Object> update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam("id") String userId) {
        return userService.deleteUser(userId);
    }

    @GetMapping("{userId}/account")
    public ResponseEntity<Object> getAccountByUserId(@PathVariable("userId") String userId) {
        return userService.getAccountById(userId);
    }

    @GetMapping("/{userId}/accounts/balance")
    public ResponseEntity<Object> getAccountBalance(@PathVariable("userId") String userId) {
        return userService.getAccountBalance(userId);
    }

    @GetMapping("{userId}/cards")
    public ResponseEntity<Object> getCardsByUserId(@PathVariable("userId") String userId) {
        return userService.getCardsByUserId(userId);
    }

    @GetMapping("{userId}/cards/{cardNumber}")
    public ResponseEntity<Object> getCardsByUserId(@PathVariable("userId") String userId, @PathVariable("cardNumber") String cardNumber) {
        return userService.getSpecificCardByUserId(userId, cardNumber);
    }
}
