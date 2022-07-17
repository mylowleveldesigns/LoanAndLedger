package com.LedgerCompany.demo.helpers;

public class InterestCalculations {
    public static Integer getTotalLoan(Integer principalAmount, Integer years, Double rateOfInterest){
        return (int)(Math.ceil(principalAmount*years*rateOfInterest)/100);
    }

    public static Integer getEmiAmount(Integer calculatedTotalAmount, Integer years){
        return (int) Math.ceil(Double.valueOf(calculatedTotalAmount)/((double) (years * 12)));
    }
}
