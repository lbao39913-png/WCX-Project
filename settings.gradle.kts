pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    plugins {
        id("com.android.application") version "8.6.1"
        id("com.android.library") version "8.6.1"
        id("org.jetbrains.kotlin.android") version "2.0.21"
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
