package com.wcx.project.license

import android.content.Context
import com.wcx.project.core.LocalStorage
import com.wcx.project.core.Logger
import kotlin.jvm.Volatile

class LicenseManager(context: Context) {
    private val storage = LocalStorage(context)

    private val KEY_LICENSE_KEY = "license_key"
    private val KEY_LICENSE_OWNER = "license_owner"
    private val KEY_LICENSE_ACTIVATED_AT = "license_activated_at"

    @Volatile
    private var cached: LicenseData? = null

    fun saveLocal(license: LicenseData) {
        storage.putString(KEY_LICENSE_KEY, license.key)
        storage.putString(KEY_LICENSE_OWNER, license.owner)
        storage.putString(KEY_LICENSE_ACTIVATED_AT, license.activatedAtMs.toString())
        cached = license
        Logger.i("LicenseManager", "License saved locally: ${license.key}")
    }

    fun loadLocal(): LicenseData? {
        cached?.let { return it }
        val key = storage.getString(KEY_LICENSE_KEY) ?: return null
        val owner = storage.getString(KEY_LICENSE_OWNER) ?: ""
        val activatedAtStr = storage.getString(KEY_LICENSE_ACTIVATED_AT) ?: "0"
        val activatedAt = activatedAtStr.toLongOrNull() ?: 0L
        val license = LicenseData(key, owner, activatedAt)
        cached = license
        return license
    }

    fun clearLocal() {
        storage.remove(KEY_LICENSE_KEY)
        storage.remove(KEY_LICENSE_OWNER)
        storage.remove(KEY_LICENSE_ACTIVATED_AT)
        cached = null
        Logger.i("LicenseManager", "License cleared")
    }

    fun isActive(): Boolean {
        return loadLocal() != null
    }
}
