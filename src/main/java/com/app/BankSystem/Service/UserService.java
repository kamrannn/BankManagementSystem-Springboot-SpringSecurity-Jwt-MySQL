package com.app.BankSystem.Service;

import com.app.BankSystem.Model.Account;
import com.app.BankSystem.Model.Card;
import com.app.BankSystem.Model.User;
import com.app.BankSystem.Repository.CardRepository;
import com.app.BankSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final CardRepository cardRepository;
    private final PasswordEncoder passwordEncoder;
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository, CardRepository cardRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.cardRepository = cardRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<Object> createUser(User user) {
        try {
            if (!user.getCards().isEmpty()) {
                for (Card card : user.getCards()
                ) {
                    int cardLength = String.valueOf(card.getNumber()).length();
                    int secreteCodeLength = String.valueOf(card.getSecretCode()).length();
                    int cryptogramLength = String.valueOf(card.getCryptogram()).length();
                    if (cardLength != 16) {
                        return new ResponseEntity<>("Your card number is incorrect", HttpStatus.OK);
                    } else if (secreteCodeLength != 4) {
                        return new ResponseEntity<>("Your secret code is incorrect", HttpStatus.OK);
                    } else if (cryptogramLength != 3) {
                        return new ResponseEntity<>("Your cryptogram is incorrect", HttpStatus.OK);
                    }
                    card.setIsContactless(false);
                    card.setIsBlocked(false);
                    card.setIsVirtual(true);
                    card.setMonthlyThreshold(50000.0);
                    card.setTotalAmount(100000.0);
                }
            }
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> listAllUsers() {
        try {
            List<User> userList = userRepository.findAll();
            if (userList.isEmpty()) {
                return new ResponseEntity<>("There are no users in the database", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(userList, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> updateUser(User user) {
        try {
            Optional<User> userOptional = userRepository.findById(user.getId());
            if (userOptional.isPresent()) {
                userRepository.save(user);
                return new ResponseEntity<>(user, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no user against this ID, Kindly check the user ID you are sending", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> deleteUser(String userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                userRepository.delete(user.get());
                return new ResponseEntity<>("User has been successfully deleted", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("There is no user against this ID, Kindly check the user ID again", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAccountById(String userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                Account account = user.get().getAccount();
                if (null == account) {
                    return new ResponseEntity<>("There is no account registered against this user", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(account, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("There is no user against this ID, Kindly check the user ID again", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getAccountBalance(String userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                Account account = user.get().getAccount();
                if (null == account) {
                    return new ResponseEntity<>("There is no account registered against this user", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("Total Balance in this account is: " + account.getTotalAmount(), HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("There is no user against this ID, Kindly check the user ID again", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getCardsByUserId(String userId) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                List<Card> cards = user.get().getCards();
                if (cards.isEmpty()) {
                    return new ResponseEntity<>("There are no cards registered against this user", HttpStatus.OK);
                } else {
                    return new ResponseEntity<>(cards, HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("There is no user against this ID, Kindly check the user ID again", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Object> getSpecificCardByUserId(String userId, String cardNumber) {
        try {
            Optional<User> user = userRepository.findById(userId);
            if (user.isPresent()) {
                Optional<Card> cards = cardRepository.findById(cardNumber);
                if (cards.isPresent()) {
                    return new ResponseEntity<>(cards, HttpStatus.OK);
                } else {
                    return new ResponseEntity<>("There are no cards registered against this user", HttpStatus.OK);
                }
            } else {
                return new ResponseEntity<>("There is no user against this ID, Kindly check the user ID again", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (null != user) {
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }
}
