package com.wcx.project.hook

class HookRegistry : IHookProvider {
    private val hooks = mutableMapOf<String, MutableList<() -> Unit>>()

    override fun registerHook(name: String, block: () -> Unit) {
        val list = hooks.getOrPut(name) { mutableListOf() }
        list.add(block)
    }

    override fun invokeHook(name: String) {
        hooks[name]?.forEach { it.invoke() }
    }
}
