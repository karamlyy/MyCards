package com.karamlyy.mycards.ui.passcode

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karamlyy.mycards.MainActivity
import com.karamlyy.mycards.databinding.ActivityPasscodeSetupBinding
import com.karamlyy.mycards.utilities.PasscodeUtil

class PasscodeSetupActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPasscodeSetupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPasscodeSetupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSetPasscode.setOnClickListener {
            val passcode = binding.etPasscode.text.toString()
            if (passcode.isNotEmpty()) {
                PasscodeUtil.storePasscodeHash(this, passcode)
                navigateToSecondScreen()
            }
        }
    }

    private fun navigateToSecondScreen() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}