plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("androidx.navigation.safeargs")
    id ("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
}
apply(from = "../shared_dependencies.gradle")
android {
    namespace = "com.ashoka.capstonedicoding"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.ashoka.capstonedicoding"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_18
        targetCompatibility = JavaVersion.VERSION_18
    }
    kotlinOptions {
        jvmTarget = "18"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    dynamicFeatures += setOf(":favorite")
}

dependencies {
    implementation(project(":core"))

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.google.dagger:hilt-android:2.48.1")
    ksp("com.google.dagger:hilt-android-compiler:2.48.1")

    implementation ("com.github.muhrafitriandi:android-stepper:1.0.0")



//    implementation("com.gauravk.bubblenavigation:bubblenavigation:1.0.7")

//    implementation ("com.miguelcatalan:materialsearchview:1.4.0")


}