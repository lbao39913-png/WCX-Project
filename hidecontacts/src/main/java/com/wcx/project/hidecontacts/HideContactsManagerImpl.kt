package com.wcx.project.hidecontacts

import android.content.Context
import android.content.SharedPreferences

class HideContactsManagerImpl(context: Context) : IHideContactsManager {
    private val prefs: SharedPreferences = context.getSharedPreferences("hide_contacts", Context.MODE_PRIVATE)

    override fun hideContact(contactId: String): Boolean {
        prefs.edit().putBoolean(contactId, true).apply()
        return true
    }

    override fun unhideContact(contactId: String): Boolean {
        prefs.edit().remove(contactId).apply()
        return true
    }

    override fun isHidden(contactId: String): Boolean {
        return prefs.getBoolean(contactId, false)
    }
}
