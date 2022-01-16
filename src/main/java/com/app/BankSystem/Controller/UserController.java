package com.app.BankSystem.Controller;

import com.app.BankSystem.Model.User;
import com.app.BankSystem.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    UserService userService;

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

    @PostMapping("/update")
    public ResponseEntity<Object> update(@RequestBody User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Object> delete(@RequestParam("id") String userId) {
        return userService.deleteUser(userId);
    }
}
