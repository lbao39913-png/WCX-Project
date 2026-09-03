plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.wcx.project.qrcode"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        targetSdk = 35
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.21")
}
