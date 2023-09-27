import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}


val secretPropertiesFile = rootProject.file("secrets.properties")
val sitProps = Properties()
secretPropertiesFile.inputStream().use { input ->
    sitProps.load(input)
}

android {
    namespace = "com.lbg.project"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lbg.project"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
        buildConfigField("String", "prefPassword", sitProps.getProperty("prefPassword"))
        buildConfigField("String", "prefName", sitProps.getProperty("prefName"))
        buildConfigField("String", "API_KEY", sitProps.getProperty("API_KEY"))
        buildConfigField("String", "baseUrl", sitProps.getProperty("baseUrl"))
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.3"

    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(platform("androidx.compose:compose-bom:2023.03.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.compose.ui:ui-android:1.5.1")
    implementation("io.coil-kt:coil-compose:2.4.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:5.5.0")
    testImplementation("org.mockito:mockito-inline:2.13.0")
    //For runBlockingTest, CoroutineDispatcher etc.
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.7.2")
    //For InstantTaskExecutorRule
    testImplementation("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("com.github.skydoves:landscapist-glide:1.3.6")
    implementation("com.nhaarman.mockitokotlin2:mockito-kotlin:2.2.0")
    //Koin
    val koinVersion = "3.4.3"
    implementation("io.insert-koin:koin-android:$koinVersion")
    // Koin core features
    implementation("io.insert-koin:koin-core:$koinVersion")
    // Koin test features
    testImplementation("io.insert-koin:koin-test:$koinVersion")
    // Koin main features for Android (Scope,ViewModel ...)
    implementation("io.insert-koin:koin-android:$koinVersion")
    // Koin Java Compatibility
    implementation("io.insert-koin:koin-android-compat:$koinVersion")
    // Koin for JUnit 4
    testImplementation("io.insert-koin:koin-test-junit4:$koinVersion")
    //Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.squareup.retrofit2:converter-simplexml:2.9.0")
    implementation("com.squareup.retrofit2:converter-scalars:2.9.0")
    //Encrypted Shared Preference
    implementation("com.pddstudio:encrypted-preferences:1.3.0")
    //Recyclerview
    implementation("androidx.recyclerview:recyclerview:1.3.1")
    implementation("androidx.cardview:cardview:1.0.0")
    //Glide
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.13.0")

}