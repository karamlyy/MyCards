package com.karamlyy.mycards.utilities

import android.content.Context
import java.security.MessageDigest

object PasscodeUtil {
    private const val PREFS_NAME = "SecurePrefs"
    private const val PASSCODE_HASH_KEY = "passcodeHash"

    fun storePasscodeHash(context: Context, passcode: String) {
        val hashed = hashPasscode(passcode)
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            .edit()
            .putString(PASSCODE_HASH_KEY, hashed)
            .apply()
    }

    fun isPasscodeCorrect(context: Context, enteredPasscode: String): Boolean {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val storedHash = prefs.getString(PASSCODE_HASH_KEY, null) ?: return false
        return hashPasscode(enteredPasscode) == storedHash
    }

    private fun hashPasscode(passcode: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(passcode.toByteArray(Charsets.UTF_8))
        return hash.joinToString("") { "%02x".format(it) }
    }
}