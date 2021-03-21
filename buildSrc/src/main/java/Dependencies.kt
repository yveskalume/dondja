package com.yvkalume.buildSrc

object Version {
    const val koin = "2.2.2"
    const val kotlin = "1.4.30"
    const val coroutines = "1.4.0"
    const val ktor = "1.4.0"
    const val epoxy = "4.4.0"
    const val mavericks = "2.0.0"
    const val workManager = "2.4.0"
    const val hilt = "2.31.2-alpha"
    const val hiltAndroidX = "1.0.0-alpha03"
    const val firebaseUiVersion = "7.1.1"
    const val lifeCycle = "2.2.0"
    const val glide = "4.11.0"
    const val appCenter = "3.3.1"
    const val compose = "1.0.0-alpha12"
    const val playService = "17.0.0"
    const val googleMapUtil = "2.1.1"
    const val paris = "1.7.2"
    const val navigation = "2.3.3"
    const val materialDialogs = "3.3.0"
}

object Module {
    const val data = ":data"
    const val domain = ":domain"
    const val util = ":util"
}

object Plugins {
    const val gradle = "com.android.tools.build:gradle:7.0.0-alpha10"
    const val navigationSafeArgs =
        "android.arch.navigation:navigation-safe-args-gradle-plugin:1.0.0"

    object Google {
        const val playService = "com.google.gms:google-services:4.3.4"
        const val firebaseCrashlyticsGradle = "com.google.firebase:firebase-crashlytics-gradle:2.2.0"
        const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Version.hilt}"
    }

    object Kotlin {
        const val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
        const val serialization = "org.jetbrains.kotlin:kotlin-serialization:${Version.kotlin}"
    }
}

object Deps {

    object Build {
        const val compileSdkVersion = 30
        const val buildToolsVersion = "30.0.2"
        const val minSdkVersion = 21
        const val targetVersion = 30
    }

    object AppCenter {
        const val analytics = "com.microsoft.appcenter:appcenter-analytics:${Version.appCenter}"
        const val crashes = "com.microsoft.appcenter:appcenter-crashes:${Version.appCenter}"
    }


    object Airbnb {
        object Epoxy {
            const val core = "com.airbnb.android:epoxy:${Version.epoxy}"
            const val processor = "com.airbnb.android:epoxy-processor:${Version.epoxy}"
            const val dataBinding = "com.airbnb.android:epoxy-databinding:${Version.epoxy}"
            const val paging = "com.airbnb.android:epoxy-paging:${Version.epoxy}"
            const val glidePreloading = "com.airbnb.android:epoxy-glide-preloading:${Version.epoxy}"
        }

        object Lottie {
            const val lottie = "com.airbnb.android:lottie:3.4.1"
        }

        object Mavericks {
            const val testing = "com.airbnb.android:mavericks-testing:${Version.mavericks}"
            const val core = "com.airbnb.android:mavericks:${Version.mavericks}"
            const val mocking = "com.airbnb.android:mavericks-mocking:${Version.mavericks}"
        }
    }


    object Firebase {
        const val firebaseBom = "com.google.firebase:firebase-bom:26.4.0"
        const val crashlytics = "com.google.firebase:firebase-crashlytics-ktx"
        const val analystics = "com.google.firebase:firebase-analytics-ktx"
        const val auth = "com.google.firebase:firebase-auth-ktx"
        const val firestore = "com.google.firebase:firebase-firestore-ktx"
        const val storage = "com.google.firebase:firebase-storage-ktx"
        const val config = "com.google.firebase:firebase-config-ktx"
        const val messaging = "com.google.firebase:firebase-messaging-ktx"

        object UI {
            const val database = "com.firebaseui:firebase-ui-database:${Version.firebaseUiVersion}"
            const val firestore =
                "com.firebaseui:firebase-ui-firestore:${Version.firebaseUiVersion}"
            const val auth = "com.firebaseui:firebase-ui-auth:${Version.firebaseUiVersion}"
            const val storage = "com.firebaseui:firebase-ui-storage:${Version.firebaseUiVersion}"
        }
    }

    object Ktor {
        const val core = "io.ktor:ktor-client-core:${Version.ktor}"
        const val android = "io.ktor:ktor-client-android:${Version.ktor}"
        const val cio = "io.ktor:ktor-client-cio:${Version.ktor}"
        const val serialization = "io.ktor:ktor-client-serialization-jvm:${Version.ktor}"
        const val logging = "io.ktor:ktor-client-logging-jvm:${Version.ktor}"
    }

    object Koin {
        const val android = "org.koin:koin-android:${Version.koin}"
        const val androidX = "org.koin:koin-androidx-ext:${Version.koin}"
        const val scope = "org.koin:koin-androidx-scope:${Version.koin}"
        const val viewModel = "org.koin:koin-androidx-viewmodel:${Version.koin}"
        const val fragment = "org.koin:koin-androidx-fragment:${Version.koin}"
        const val test = "org.koin:koin-test:${Version.koin}"
    }

    object Facebook {
        const val shimmer = "com.facebook.shimmer:shimmer:0.5.0"
        const val login = "com.facebook.android:facebook-login:8.1.0"
    }

    object Google {
        object Android {
            const val material = "com.google.android.material:material:1.3.0"

            object PlayService {
                const val map = "com.google.android.gms:play-services-maps:${Version.playService}"
                const val location = "com.google.android.gms:play-services-location:${Version.playService}"
            }

            object Map {
                const val ktx = "com.google.maps.android:maps-ktx:${Version.googleMapUtil}"
                const val utils = "com.google.maps.android:maps-utils-ktx:${Version.googleMapUtil}"
            }
        }

        object Hilt {
            const val android = "com.google.dagger:hilt-android:${Version.hilt}"
            const val compiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
        }
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Version.kotlin}"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"

        object Coroutines {
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
            const val playService =
                "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Version.coroutines}"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"
        }

        object Serialization {
            const val plugin = "org.jetbrains.kotlin:kotlin-serialization:${Version.kotlin}"
            const val core = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Version.kotlin}"
        }
    }

    object Hilt {
        const val android = "com.google.dagger:hilt-android:${Version.hilt}"
        const val compiler = "com.google.dagger:hilt-android-compiler:${Version.hilt}"
    }

    object AndroidX {

        const val core = "androidx.core:core-ktx:1.3.1"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.0-beta1"
        const val appCompat = "androidx.appcompat:appcompat:1.2.0"
        const val legacy = "androidx.legacy:legacy-support-v4:1.0.0"
        const val startup = "androidx.startup:startup-runtime:1.0.0-beta01"

        object Lifecycle {
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifeCycle}"
            const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.lifeCycle}"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifeCycle}"
        }

        object Hilt {
            const val viewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Version.hiltAndroidX}"
            const val compiler = "androidx.hilt:hilt-compiler:${Version.hiltAndroidX}"
        }

        object WorkManager {
            const val workRuntimeKtx = "androidx.work:work-runtime-ktx:${Version.workManager}"
            const val workTesting = "androidx.work:work-testing:${Version.workManager}"
        }

        object Navigation {
            private const val version = "2.3.0"

            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val test = "androidx.navigation:navigation-testing:$version"
        }

        object Preference {
            private const val version = "1.1.1"
            const val preference = "androidx.preference:preference:$version"
        }

        object Test {
            const val ext = "androidx.test.ext:junit:1.1.1"
            const val junit = "junit:junit:4.13"
            const val fragment = "androidx.fragment:fragment-testing:1.2.5"

            object Expresso {
                private const val version = "3.2.0"
                const val core = "androidx.test.espresso:espresso-core:$version"
            }
        }
    }

    object Gms {
        private const val version = "17.0.0"

        const val maps = "com.google.android.gms:play-services-maps:$version"
        const val location = "com.google.android.gms:play-services-location:$version"
    }

    object GooglePlayService {
        private const val version = "1.8.1"
        const val core = "com.google.android.play:core-ktx:$version"
    }

    object GoogleMaps {
        private const val version = "2.1.1"
        const val maps = "com.google.maps.android:maps-ktx:$version"
        const val utils = "com.google.maps.android:maps-utils-ktx:$version"

        const val place = "com.google.android.libraries.places:places:2.4.0"
        const val placeKtx = "com.google.maps.android:places-ktx:0.4.0"
    }

    object Glide {
        const val core = "com.github.bumptech.glide:glide:${Version.glide}"
        const val compiler = "com.github.bumptech.glide:compiler:${Version.glide}"
    }

    const val androidChart = "com.github.PhilJay:MPAndroidChart:v3.1.0"
    const val roundImageView = "com.makeramen:roundedimageview:2.3.0"
    const val speedDial = "com.leinardi.android:speed-dial:3.1.1"
    const val dexter = "com.karumi:dexter:6.2.1"
    const val leku = "com.schibstedspain.android:leku:7.2.0"
    const val autoImageSlider = "com.github.smarteist:autoimageslider:1.4.0"
    const val viewBindingUtil = "com.github.yogacp:android-viewbinding:1.0.1"
    const val vvalidator = "com.afollestad:vvalidator:0.5.2"
    const val timber = "com.jakewharton.timber:timber:4.7.1"
    const val pinView = "com.chaos.view:pinview:1.4.4"
    const val imagePicker = "com.github.dhaval2404:imagepicker:1.8"
    const val circularImageView = "de.hdodenhof:circleimageview:3.1.0"
    const val localization = "com.zeugmasolutions.localehelper:locale-helper-android:1.1.4"
    const val jodaTime = "net.danlew:android.joda:2.10.9"
}