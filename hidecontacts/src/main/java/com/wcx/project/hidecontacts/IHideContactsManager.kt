package com.wcx.project.hidecontacts

interface IHideContactsManager {
    fun hideContact(contactId: String): Boolean
    fun unhideContact(contactId: String): Boolean
    fun isHidden(contactId: String): Boolean
}
