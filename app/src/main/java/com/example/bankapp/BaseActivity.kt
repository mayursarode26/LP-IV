package com.example.bankapp

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Shared options menu (Home, New Account, Deposit, Exit) for both forms
open class BaseActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_home, R.id.menu_new_account -> {
                startActivity(Intent(this, MainActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP))
            }
            R.id.menu_deposit -> onDepositMenu()
            R.id.menu_exit -> finishAffinity()
        }
        return true
    }

    // MainActivity overrides this; DepositActivity is already on the deposit screen
    open fun onDepositMenu() {
        Toast.makeText(this, "You are already on the Deposit form", Toast.LENGTH_SHORT).show()
    }
}
