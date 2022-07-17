package com.LedgerCompany.demo.service;

import com.LedgerCompany.demo.helpers.BalanceCommandResponse;
import com.LedgerCompany.demo.helpers.GenerateLedgerEntryResponse;
import com.LedgerCompany.demo.model.Loan;
import com.LedgerCompany.demo.model.LedgerEntry;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LedgerService {
    final String separator = "_";
    Map<String, Loan> bank_userToLoansMap;
    Map<Integer , List<LedgerEntry>> loanIdToLedgerEntriesMap;

    public LedgerService() {
        this.bank_userToLoansMap = new HashMap<>();
        this.loanIdToLedgerEntriesMap = new HashMap<>();
    }

    private String keyInbank_userToLoansMap(String bankName, String customerName) {
        return bankName+separator+customerName;
    }

    private LedgerEntry getLastLedgerEntryForLoanId(Integer loanId){
        List<LedgerEntry> ledgerEntries = loanIdToLedgerEntriesMap.get(loanId);
        if(ledgerEntries == null){
            return null;
        } else return ledgerEntries.get(ledgerEntries.size()-1);
    }

    private Loan getLoanOfCustomerInBank(String customerName, String bankName) throws Exception {
        Loan loan = bank_userToLoansMap.get(keyInbank_userToLoansMap(bankName, customerName));
        if(loan == null) {
            throw new Exception("Not found Loan for given bankName and customerName ");
        }
        return loan;
    }

    public void createLoanEntryForUser(String bankName, String customerName,
                                       Integer principalAmount, Integer years, Double rateOfInterest) throws Exception {
        if(bank_userToLoansMap.containsKey(bankName + "_" + customerName)) {
            throw new Exception("Loan already taken by this user from given bank, cannot create new entry");
        }

        Loan newLoanEntry = new Loan(bankName, customerName, principalAmount, years, rateOfInterest);
        bank_userToLoansMap.put(keyInbank_userToLoansMap(bankName, customerName), newLoanEntry);
    }

    public void registerPaymentAndGenerateLedgers(String bankName, String customerName,
                                                  Integer lumpsumAmount, Integer emiNo) throws Exception{
        Loan loan = getLoanOfCustomerInBank(customerName, bankName);
        GenerateLedgerEntryResponse responseObject = generateLedgerEntriesForEmiNo(loan, emiNo);
        LedgerEntry lumpsumLedgerEntry = new LedgerEntry(loan.getLoanId(), responseObject.getLastGeneratedLedgerId(), responseObject.getLastOpeningAmount(), lumpsumAmount, emiNo, true, responseObject.getLastOpeningAmount() - lumpsumAmount);
        List<LedgerEntry> ledgerEntries = loanIdToLedgerEntriesMap.getOrDefault(loan.getLoanId(), new ArrayList<>());
        ledgerEntries.add(lumpsumLedgerEntry);
        loanIdToLedgerEntriesMap.put(loan.getLoanId(), ledgerEntries);
    }


    public BalanceCommandResponse getClosingBalanceForLoanAfterEmiNo(String bankName, String customerName, Integer emiNo) throws Exception {
        Loan loan = getLoanOfCustomerInBank(customerName, bankName);
        LedgerEntry lastLedgerEntry = getLastLedgerEntryForLoanId(loan.getLoanId());
        if(lastLedgerEntry != null && lastLedgerEntry.getEmiNo() >= emiNo) {
            List<LedgerEntry> ledgerEntries = loanIdToLedgerEntriesMap.get(loan.getLoanId());
            Integer amountPaid = ledgerEntries.stream().filter(entry -> entry.getEmiNo() <= emiNo)
                    .map(LedgerEntry::getAmountPaid).mapToInt(Integer::intValue).sum();

            Integer remainingAmount = loan.getCalculatedTotalAmount() - amountPaid;
            Integer emiPending = (int)Math.ceil(Double.valueOf (remainingAmount) / Double.valueOf(loan.getCalculatedEMIAmount()));
            return new BalanceCommandResponse(bankName, customerName, amountPaid, emiPending);
        } else {
            // Else we need to generate the ledgers

            generateLedgerEntriesForEmiNo(loan, emiNo);
            List<LedgerEntry> ledgerEntries = loanIdToLedgerEntriesMap.getOrDefault(loan.getLoanId(), new ArrayList<>());
            Integer amountPaid = ledgerEntries.stream().filter(entry -> entry.getEmiNo() <= emiNo)
                    .map(LedgerEntry::getAmountPaid).mapToInt(Integer::intValue).sum();

            Integer remainingAmount = loan.getCalculatedTotalAmount() - amountPaid;
            Integer emiPending = (int)Math.ceil(Double.valueOf (remainingAmount) / Double.valueOf(loan.getCalculatedEMIAmount()));
            return new BalanceCommandResponse(bankName, customerName, amountPaid, emiPending);
        }
    }

    private GenerateLedgerEntryResponse generateLedgerEntriesForEmiNo(Loan loan, Integer emiNo){
        Integer lastOpeningAmount;
        List<LedgerEntry> ledgerEntries = loanIdToLedgerEntriesMap.get(loan.getLoanId());
        Integer startingLedgerIdForEntriesGeneration;
        Integer startingEmiNoForEntriesGeneration;
        if(ledgerEntries == null || ledgerEntries.size() == 0) {
            ledgerEntries = new ArrayList<>();
            startingLedgerIdForEntriesGeneration = 0;
            startingEmiNoForEntriesGeneration = 1;
            lastOpeningAmount = loan.getCalculatedTotalAmount();
        } else {
            LedgerEntry lastAlreadyPresentEntry = ledgerEntries.get(ledgerEntries.size()-1);
            startingLedgerIdForEntriesGeneration = lastAlreadyPresentEntry.getLoanId()+1;

            startingEmiNoForEntriesGeneration = lastAlreadyPresentEntry.getEmiNo()+1;
            lastOpeningAmount = lastAlreadyPresentEntry.getPendingAmount();
        }
        Integer ledgerEntryEmiNo = startingEmiNoForEntriesGeneration;
        Integer generatedLedgerId = startingLedgerIdForEntriesGeneration;
        while(ledgerEntryEmiNo<=emiNo){
            Integer pendingAmount = lastOpeningAmount - loan.getCalculatedEMIAmount();
            LedgerEntry ledgerEntry = new LedgerEntry(loan.getLoanId(), generatedLedgerId, lastOpeningAmount, loan.getCalculatedEMIAmount(), ledgerEntryEmiNo, false, pendingAmount);
            ledgerEntries.add(ledgerEntry);
            lastOpeningAmount = pendingAmount;
            ledgerEntryEmiNo++;
            generatedLedgerId++;
        }
        loanIdToLedgerEntriesMap.put(loan.getLoanId(), ledgerEntries);
        return new GenerateLedgerEntryResponse(generatedLedgerId, lastOpeningAmount);
    }

}
