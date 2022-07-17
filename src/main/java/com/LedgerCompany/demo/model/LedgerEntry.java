package com.LedgerCompany.demo.model;

import java.util.Objects;


public class LedgerEntry {
    Integer loanId;
    Integer ledgerId;
    Integer openingAmount;
    Integer amountPaid;
    Integer emiNo;
    Boolean isLumpSum;
    Integer pendingAmount;

    public LedgerEntry(Integer loanId, Integer ledgerId, Integer openingAmount,
                       Integer amountPaid, Integer emiNo, Boolean isLumpSum, Integer pendingAmount) {
        this.loanId = loanId;
        this.ledgerId = ledgerId;
        this.openingAmount = openingAmount;
        this.amountPaid = amountPaid;
        this.emiNo = emiNo;
        this.isLumpSum = isLumpSum;
        this.pendingAmount = pendingAmount;
    }

    public Integer getLoanId() {
        return loanId;
    }

    public void setLoanId(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(Integer ledgerId) {
        this.ledgerId = ledgerId;
    }

    public Integer getOpeningAmount() {
        return openingAmount;
    }

    public void setOpeningAmount(Integer openingAmount) {
        this.openingAmount = openingAmount;
    }

    public Integer getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Integer amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Integer getEmiNo() {
        return emiNo;
    }

    public void setEmiNo(Integer emiNo) {
        this.emiNo = emiNo;
    }

    public Boolean getLumpSum() {
        return isLumpSum;
    }

    public void setLumpSum(Boolean lumpSum) {
        isLumpSum = lumpSum;
    }

    public Integer getPendingAmount() {
        return pendingAmount;
    }

    public void setPendingAmount(Integer pendingAmount) {
        this.pendingAmount = pendingAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LedgerEntry that = (LedgerEntry) o;
        return Objects.equals(loanId, that.loanId) &&
                Objects.equals(ledgerId, that.ledgerId) &&
                Objects.equals(openingAmount, that.openingAmount) &&
                Objects.equals(amountPaid, that.amountPaid) &&
                Objects.equals(emiNo, that.emiNo) &&
                Objects.equals(isLumpSum, that.isLumpSum) &&
                Objects.equals(pendingAmount, that.pendingAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, ledgerId, openingAmount, amountPaid, emiNo, isLumpSum, pendingAmount);
    }

    @Override
    public String toString() {
        return "LedgerEntry{" +
                "loanId=" + loanId +
                ", ledgerId=" + ledgerId +
                ", openingAmount=" + openingAmount +
                ", amountPaid=" + amountPaid +
                ", emiNo=" + emiNo +
                ", isLumpSum=" + isLumpSum +
                ", pendingAmount=" + pendingAmount +
                '}';
    }
}
