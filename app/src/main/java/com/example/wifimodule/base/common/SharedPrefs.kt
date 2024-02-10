package com.example.wifimodule.base.common

import android.content.Context
import android.content.SharedPreferences
import com.example.wifimodule.data.response.LoginResponse
import com.google.gson.GsonBuilder

@Suppress("UNCHECKED_CAST")
class SharedPrefs(private val context: Context) {
    companion object {
        private const val PREF = "AppConfig"
        private const val PREF_TOKEN = "user_token"
        private const val PREF_USER = "user_data"
        private const val IS_LOGIN = "is_login"
        private const val REMEMBER_ME = "remember_me"
        private const val LANGUAGE = "language"
    }

    val sharedPref: SharedPreferences = context.getSharedPreferences(PREF, Context.MODE_PRIVATE)

    fun saveLoginResponse(user: LoginResponse) {
        putObject(PREF_USER, user)
    }

    fun getLoginResponse(): LoginResponse? {
        return getObject<LoginResponse>(PREF_USER)
    }

    fun saveRememberMeEmail(email: String) {
        put(REMEMBER_ME, email)
    }

    fun getRememberMeEmail(): String {
        return get(REMEMBER_ME, String::class.java)
    }
    fun saveLanguage(email: String) {
        put(LANGUAGE, email)
    }

    fun getLanguage(): String {
        return get(LANGUAGE, String::class.java)
    }

    fun saveToken(token: String) {
        put(PREF_TOKEN, token)
    }

    fun getToken(): String {
        return get(PREF_TOKEN, String::class.java)
    }

    fun getIsLogin(): Boolean {
        return get(IS_LOGIN, Boolean::class.java)
    }

    fun setIsLogin(flag: Boolean) {
        put(IS_LOGIN, flag)
    }

    private fun <T> get(key: String, clazz: Class<T>): T =
        when (clazz) {
            String::class.java -> sharedPref.getString(key, "")
            Boolean::class.java -> sharedPref.getBoolean(key, false)
            Float::class.java -> sharedPref.getFloat(key, -1f)
            Double::class.java -> sharedPref.getFloat(key, -1f)
            Int::class.java -> sharedPref.getInt(key, -1)
            Long::class.java -> sharedPref.getLong(key, -1L)
            else -> null
        } as T

    private fun <T> put(key: String, data: T) {
        val editor = sharedPref.edit()
        when (data) {
            is String -> editor.putString(key, data)
            is Boolean -> editor.putBoolean(key, data)
            is Float -> editor.putFloat(key, data)
            is Double -> editor.putFloat(key, data.toFloat())
            is Int -> editor.putInt(key, data)
            is Long -> editor.putLong(key, data)
        }
        editor.apply()
    }

    /**
     * Saves object into the Preferences.
     *
     * @param `object` Object of model class (of type [T]) to save
     * @param key Key with which Shared preferences to
     **/
    private fun <T> putObject(key: String, `object`: T) {
        val jsonString = GsonBuilder().create().toJson(`object`)
        val editor = sharedPref.edit()
        editor.putString(key, jsonString).apply()
    }

    /**
     * Used to retrieve object from the Preferences.
     *
     * @param key Shared Preference key with which object was saved.
     **/
    inline fun <reified T> getObject(key: String): T? {
        val value = sharedPref.getString(key, null)
        //JSON String was found which means object can be read.
        //We convert this JSON String to model object. Parameter "c" (of
        //type Class < T >" is used to cast.
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

    fun clear() {
        sharedPref.edit().run {
            remove(PREF_TOKEN)
        }.apply()
    }

    fun clearAll() {
        for (key in sharedPref.all.keys) {
            if (!key.contentEquals(REMEMBER_ME)) {
                sharedPref.edit().run {
                    remove(key)
                }.apply()
            }
        }
    }


}