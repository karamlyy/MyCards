package com.karamlyy.mycards.ui.passcode

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.karamlyy.mycards.MainActivity
import com.karamlyy.mycards.databinding.ActivityPasscodeVerificationBinding
import com.karamlyy.mycards.utilities.PasscodeUtil


class PasscodeVerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasscodeVerificationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasscodeVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVerifyPasscode.setOnClickListener {
            val enteredPasscode = binding.etPasscode.text.toString()
            if (PasscodeUtil.isPasscodeCorrect(this, enteredPasscode)) {
                navigateToSecondScreen()
            } else {
                Toast.makeText(this, "Wrong Passcode", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun navigateToSecondScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}