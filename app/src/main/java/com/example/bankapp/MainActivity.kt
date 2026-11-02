package com.example.bankapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

// Form 1: create a new account, then jump to Form 2 with an Intent
class MainActivity : BaseActivity() {

    private lateinit var etName: EditText
    private lateinit var etPhone: EditText
    private lateinit var spType: Spinner
    private lateinit var spBranch: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "New account"

        etName = findViewById(R.id.etName)
        etPhone = findViewById(R.id.etPhone)
        spType = findViewById(R.id.spAccountType)
        spBranch = findViewById(R.id.spBranch)

        // Spinners filled from code (an alternative to android:entries in XML)
        spType.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
            listOf("Savings", "Current", "Fixed Deposit", "Recurring Deposit"))
        spBranch.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,
            listOf("Pune Main", "Mumbai Fort", "Nagpur Central", "Nashik Road"))

        findViewById<Button>(R.id.btnCreate).setOnClickListener { createAccount() }
    }

    private fun createAccount() {
        val name = etName.text.toString().trim()
        val phone = etPhone.text.toString().trim()

        if (name.length < 3) { etName.error = "Enter the full name"; return }
        if (!Regex("^[6-9][0-9]{9}$").matches(phone)) {
            etPhone.error = "Enter a valid 10-digit mobile number"; return
        }

        val accountNo = "1000" + (100000..999999).random()

        // Explicit Intent: Form 1 -> Form 2, data travels as extras
        val intent = Intent(this, DepositActivity::class.java).apply {
            putExtra("name", name)
            putExtra("accountNo", accountNo)
            putExtra("accountType", spType.selectedItem.toString())
            putExtra("branch", spBranch.selectedItem.toString())
        }
        Toast.makeText(this, "Account created: $accountNo", Toast.LENGTH_SHORT).show()
        startActivity(intent)
    }

    override fun onDepositMenu() {
        Toast.makeText(this, "Create an account first", Toast.LENGTH_SHORT).show()
    }
}
