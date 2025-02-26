plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.streamsharing"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.streamsharing"
        minSdk = 24
        targetSdk = 35
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }

    //AGREGADOS
    buildFeatures{
        compose = true // Habilita Jetpack Compose
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Jetpack Compose
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation("androidx.compose.ui:ui:1.5.3")
    implementation("androidx.compose.material3:material3:1.2.0")
    implementation("androidx.compose.ui:ui-tooling-preview:1.5.3")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")

    // Agregar soporte para KeyboardOptions
    implementation("androidx.compose.ui:ui-text:1.5.3")

    // Navegación en Compose
    implementation("androidx.navigation:navigation-compose:2.7.6")

    // Para mostrar imágenes
    implementation("io.coil-kt:coil-compose:2.4.0")

    // Herramientas de depuración
    debugImplementation("androidx.compose.ui:ui-tooling:1.5.3")

    // Material icons
    implementation("androidx.compose.material:material-icons-extended:1.5.3")

    implementation("androidx.navigation:navigation-compose:2.7.6")
}