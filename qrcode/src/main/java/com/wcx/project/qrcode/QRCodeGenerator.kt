package com.wcx.project.qrcode

object QRCodeGenerator {
    fun generate(data: String): ByteArray {
        return data.toByteArray(Charsets.UTF_8)
    }
}
