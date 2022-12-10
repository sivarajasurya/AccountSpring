package com.gcit.siva.AccountSpring;

import core.Ledger;
import core.TrialBalanceResult;
import core.chartofaccounts.ChartOfAccounts;
import core.chartofaccounts.ChartOfAccountsBuilder;
import core.transaction.AccountingTransaction;
import core.transaction.AccountingTransactionBuilder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static core.account.AccountSide.DEBIT;

@Service
public class AccountService {

    String cashAccountNumber = "000001";
    String checkingAccountNumber = "000002";
    String accountsReceivableAccountNumber = "000003";

    // Setup ledger
    ChartOfAccounts coa = ChartOfAccountsBuilder.create().addAccount(cashAccountNumber, "Cash", DEBIT)
//                .addAccount(checkingAccountNumber, "Checking", CREDIT)
//                .addAccount(accountsReceivableAccountNumber, "Accounts Receivable", DEBIT)
            .build();
    Ledger ledger = new Ledger(coa);

    AccountingTransactionBuilder ad = ledger.createTransaction(null);

    public void credit(BigDecimal amt) {
        ad.credit(amt, cashAccountNumber);
    }

    public void debit(BigDecimal amt) {
        ad.debit(amt, cashAccountNumber);
    }

    public String ledgerPrint() {
        AccountingTransactionBuilder ad1;
        ad1 = ad;
        AccountingTransaction build = ad1.build();
        ledger.commitTransaction(build);
        System.out.println(ledger.toString());

        // Print trial balance
        TrialBalanceResult trialBalanceResult = ledger.computeTrialBalance();
        System.out.println(trialBalanceResult.toString());

        return (ledger.toString() + "\n" + trialBalanceResult.toString());
    }

}
