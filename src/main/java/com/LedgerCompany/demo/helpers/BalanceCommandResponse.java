package com.LedgerCompany.demo.helpers;

public class BalanceCommandResponse {
    String bankName;
    String customerName;
    Integer amountPaid;
    Integer emiLeft;

    public BalanceCommandResponse(String bankName, String customerName, Integer amountPaid, Integer emiLeft) {
        this.bankName = bankName;
        this.customerName = customerName;
        this.amountPaid = amountPaid;
        this.emiLeft = emiLeft;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Integer amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Integer getEmiLeft() {
        return emiLeft;
    }

    public void setEmiLeft(Integer emiLeft) {
        this.emiLeft = emiLeft;
    }
}
