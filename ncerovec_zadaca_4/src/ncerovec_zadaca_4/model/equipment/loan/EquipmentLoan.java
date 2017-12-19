/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ncerovec_zadaca_4.model.equipment.loan;

import java.util.Date;
import ncerovec_zadaca_4.helper.AppHelper;
import ncerovec_zadaca_4.model.equipment.EquipmentPart;

/**
 * LEASING - Lease
 * @author nino
 */
public class EquipmentLoan
{
    private int loanDays = 0;
    private Date loanStart = null;
    private Date loanEnd = null;
    private EquipmentPart loanEquipment = null;

    public int getLoanDays() { return loanDays; }
    public void addLoanDays(int daysLoan)
    {
        this.loanDays += daysLoan;
        if(this.loanStart != null) this.loanEnd = calculateLoanEndDate(this.loanDays);
    }

    public Date getLoanStart() { return loanStart; }
    //public void setLoanStart(Date loanStart) { this.loanStart = loanStart; }

    public Date getLoanEnd() { return loanEnd; }
    //public void setLoanEnd(Date loanEnd) { this.loanEnd = loanEnd; }
    
    public EquipmentPart getLoanEquipment() { return loanEquipment; }
    public void setLoanEquipment(EquipmentPart loanEquipment) { this.loanEquipment = loanEquipment; }   

    public EquipmentLoan(EquipmentPart loanEquipment, Date startLoan, int daysLoan)
    {
        this.loanDays = daysLoan;
        this.loanStart = startLoan;
        this.loanEnd = calculateLoanEndDate(daysLoan);
        this.loanEquipment = loanEquipment;
    }
    
    private Date calculateLoanEndDate(int days)
    {
        return AppHelper.addRemoveDays(this.loanStart, days);
    }
}
