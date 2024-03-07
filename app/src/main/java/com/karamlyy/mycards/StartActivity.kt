package com.karamlyy.mycards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.karamlyy.mycards.databinding.ActivityStartBinding
import com.karamlyy.mycards.ui.passcode.PasscodeSetupActivity
import com.karamlyy.mycards.ui.passcode.PasscodeVerificationActivity

class StartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        authenticateUser()
    }


    private fun authenticateUser() {
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)) {
            BiometricManager.BIOMETRIC_SUCCESS -> initiateBiometricPrompt()
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED,
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE,
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> navigateToPasscodeSetup()
        }
    }

    private fun initiateBiometricPrompt() {
        val executor = ContextCompat.getMainExecutor(this)
        val biometricPrompt = BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {

            override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                super.onAuthenticationSucceeded(result)
                navigateToSecondScreen()
            }

        })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Unlock MyCards")
            .setSubtitle("Use your biometric credential to unlock")
            .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG or BiometricManager.Authenticators.DEVICE_CREDENTIAL)
            .build()

        biometricPrompt.authenticate(promptInfo)
    }

    private fun navigateToSecondScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToPasscodeSetup() {
        val prefs = this.getSharedPreferences("SecurePrefs", MODE_PRIVATE)
        val isPasscodeSet = prefs.getString("passcodeHash", null) != null
        val intent = if (isPasscodeSet) {
            Intent(this, PasscodeVerificationActivity::class.java)
        } else {
            Intent(this, PasscodeSetupActivity::class.java)
        }
        startActivity(intent)
    }
}