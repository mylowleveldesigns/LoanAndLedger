package com.LedgerCompany.demo.model;

import com.LedgerCompany.demo.helpers.InterestCalculations;
import java.util.Objects;


public class Loan {
    private static Integer generatedId = 0;
    Integer loanId;
    String bank;
    String customer;
    Integer principalAmount;
    Integer years;
    Double interestRate;
    Integer calculatedTotalAmount;
    Integer calculatedEMIAmount;

    public Loan(String bank, String customer, Integer principalAmount, Integer years, Double interestRate) {
        Loan.generatedId++;
        this.loanId = generatedId;
        this.bank = bank;
        this.customer = customer;
        this.principalAmount = principalAmount;
        this.years = years;
        this.interestRate = interestRate;
        this.calculatedTotalAmount = principalAmount + InterestCalculations.getTotalLoan(principalAmount, years, interestRate);
        this.calculatedEMIAmount = InterestCalculations.getEmiAmount(this.calculatedTotalAmount, years);
    }

    public Integer getLoanId() {
        return loanId;
    }

    public Integer getCalculatedTotalAmount() {
        return calculatedTotalAmount;
    }

    public Integer getCalculatedEMIAmount() {
        return calculatedEMIAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Loan loan = (Loan) o;
        return Objects.equals(loanId, loan.loanId) &&
                Objects.equals(bank, loan.bank) &&
                Objects.equals(customer, loan.customer) &&
                Objects.equals(principalAmount, loan.principalAmount) &&
                Objects.equals(years, loan.years) &&
                Objects.equals(interestRate, loan.interestRate) &&
                Objects.equals(calculatedTotalAmount, loan.calculatedTotalAmount) &&
                Objects.equals(calculatedEMIAmount, loan.calculatedEMIAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(loanId, bank, customer, principalAmount, years, interestRate, calculatedTotalAmount, calculatedEMIAmount);
    }

    @Override
    public String toString() {
        return "Loan{" +
                "loanId=" + loanId +
                ", bank='" + bank + '\'' +
                ", customer='" + customer + '\'' +
                ", principalAmount=" + principalAmount +
                ", years=" + years +
                ", interestRate=" + interestRate +
                ", calculatedTotalAmount=" + calculatedTotalAmount +
                ", calculatedEMIAmount=" + calculatedEMIAmount +
                '}';
    }
}
