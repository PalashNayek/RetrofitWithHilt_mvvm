package com.palash.retrofitwithhilt_mvvm.utils

import android.content.Context
import com.palash.retrofitwithhilt_mvvm.utils.Constants.PREF_TOKEN_FILES
import com.palash.retrofitwithhilt_mvvm.utils.Constants.USER_TOKEN
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class TokenManager @Inject constructor(@ApplicationContext context: Context) {

    private var pref = context.getSharedPreferences(PREF_TOKEN_FILES, Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = pref.edit()
        editor.putString(USER_TOKEN, token)
        editor.apply()
    }

    fun getToken(): String? {
        return pref.getString(USER_TOKEN, null)
    }
}