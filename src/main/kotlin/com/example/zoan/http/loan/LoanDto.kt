package com.example.zoan.http.loan

import com.example.zoan.domain.fund.Fund
import com.example.zoan.domain.fund.FundOwner
import com.example.zoan.domain.loan.Loan

class LoanDto(loan: Loan) {
    val amount = loan.amount
    val borrower = loan.borrower
    val monthlyInterest = loan.monthlyInterest
    val installmentCount = loan.installmentCount
    val status = loan.status

    private val _fund = loan.fund
    val fund = if (_fund != null) LoanFundDto(_fund) else null


    class LoanFundDto(fund: Fund) {
        val totalAmount = fund.amount
        val dateCreated = fund.dateCreated

        private val _owners = fund.owners
        val loaners: List<LoanFundOwnerDto>
            get() {
                return this._owners.map { fundOwner ->
                    LoanFundOwnerDto(fundOwner)
                }
            }


        class LoanFundOwnerDto(fundOwner: FundOwner) {
            val amount = fundOwner.amountAllocated
            val name = fundOwner.loaner.name
        }
    }
}
