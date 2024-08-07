plugins {
    id("com.android.dynamic-feature")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id ("androidx.navigation.safeargs")
    id("com.google.devtools.ksp")
}
apply(from = "../shared_dependencies.gradle")

android {
    namespace = "com.ashoka.favorite"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {
    implementation(project(":app"))
    implementation(project(":core"))
    implementation("androidx.core:core-ktx:1.13.1")

    implementation("androidx.annotation:annotation:1.8.0")

    implementation("androidx.annotation:annotation-experimental:1.4.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation("androidx.annotation:annotation:1.8.0")

    implementation ("com.google.dagger:hilt-android:2.48.1")
    ksp("com.google.dagger:hilt-android-compiler:2.48.1")


    //room
    ksp("androidx.room:room-compiler:2.6.1")
}