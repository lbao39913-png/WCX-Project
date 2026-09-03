package com.wcx.project.hook

interface IHookProvider {
    fun registerHook(name: String, block: () -> Unit)
    fun invokeHook(name: String)
}
