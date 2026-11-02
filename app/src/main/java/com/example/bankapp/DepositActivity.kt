package com.example.bankapp

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast

// Form 2: deposit money into the account created in Form 1
class DepositActivity : BaseActivity() {

    private var balance = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deposit)
        title = "Deposit"

        // Read the extras sent by Form 1
        val name = intent.getStringExtra("name") ?: "-"
        val accountNo = intent.getStringExtra("accountNo") ?: "-"
        val type = intent.getStringExtra("accountType") ?: "-"
        val branch = intent.getStringExtra("branch") ?: "-"

        val tvInfo = findViewById<TextView>(R.id.tvInfo)
        val tvBalance = findViewById<TextView>(R.id.tvBalance)
        val etAmount = findViewById<EditText>(R.id.etAmount)
        val spMode = findViewById<Spinner>(R.id.spMode)

        tvInfo.text = "$name\nA/c $accountNo  |  $type  |  $branch"
        tvBalance.text = "Balance: ₹%.2f".format(balance)

        spMode.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
            listOf("Cash", "Cheque", "UPI", "NEFT"))

        findViewById<Button>(R.id.btnDeposit).setOnClickListener {
            val amount = etAmount.text.toString().toDoubleOrNull()
            if (amount == null || amount <= 0) {
                etAmount.error = "Enter an amount greater than zero"
                return@setOnClickListener
            }
            balance += amount
            tvBalance.text = "Balance: ₹%.2f".format(balance)
            etAmount.text.clear()
            Toast.makeText(this, "Deposited ₹%.2f by %s".format(amount, spMode.selectedItem), Toast.LENGTH_SHORT).show()
        }
    }
}
