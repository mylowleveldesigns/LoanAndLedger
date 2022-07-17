package com.LedgerCompany.demo.helpers;


public class GenerateLedgerEntryResponse {
    Integer lastGeneratedLedgerId;
    Integer lastOpeningAmount;

    public GenerateLedgerEntryResponse(Integer lastGeneratedLedgerId, Integer lastOpeningAmount) {
        this.lastGeneratedLedgerId = lastGeneratedLedgerId;
        this.lastOpeningAmount = lastOpeningAmount;
    }

    public Integer getLastGeneratedLedgerId() {
        return lastGeneratedLedgerId;
    }

    public void setLastGeneratedLedgerId(Integer lastGeneratedLedgerId) {
        this.lastGeneratedLedgerId = lastGeneratedLedgerId;
    }

    public Integer getLastOpeningAmount() {
        return lastOpeningAmount;
    }

    public void setLastOpeningAmount(Integer lastOpeningAmount) {
        this.lastOpeningAmount = lastOpeningAmount;
    }
}
