package com.app.BankSystem.Validation;

/*import com.app.BankSystem.Model.Card;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Validations {

    public static ResponseEntity<Object> cardValidation(List<Card> cardList) {
        for (Card card : cardList
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
            } else if (card.getIsBlocked()) {
                return new ResponseEntity<>("Your card is blocked", HttpStatus.OK);
            }
        }
        return null;
    }
}*/
