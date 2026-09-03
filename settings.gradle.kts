pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id("com.android.application") version "8.1.1"
        id("com.android.library") version "8.1.1"
        id("org.jetbrains.kotlin.android") version "1.9.10"
    }
}
rootProject.name = "WCX-Project"

include(":app")
include(":core")
include(":clonevoice")
include(":fakelocation")
include(":virtualvoipvideo")
include(":hidecontacts")
include(":qrcode")
include(":license")
include(":hook")
