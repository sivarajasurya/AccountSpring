package com.gcit.siva.AccountSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.math.BigDecimal;

@RestController
public class Controller {

    @Autowired
    AccountService service;

    @GetMapping("/enterCreditAmount/{amt}")
    public void creditAmt(@PathVariable("amt") BigDecimal amt) {
        service.credit(amt);
    }

    @GetMapping("/enterDebitAmount/{amt}")
    public void debitAmt(@PathVariable("amt") BigDecimal amt) {
        service.debit(amt);
    }

    @GetMapping("/printLedger")
    public String ledger() {
        return service.ledgerPrint();
    }
}
