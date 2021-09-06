import org.gradle.api.artifacts.dsl.DependencyHandler

object AppDependencies {
    val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    private val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    private val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    private val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    private val material = "com.google.android.material:material:${Versions.material}"

    private val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    private val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    private val roomKapt = "androidx.room:room-compiler:${Versions.room}"

    private val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
    private val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveDataKtx}"

    private val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    private val koinAndroidExt = "io.insert-koin:koin-android-ext:${Versions.koin}"

    private val paging = "androidx.paging:paging-runtime-ktx:${Versions.paging}"

    private val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    private val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    private val retrofitMock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"

    private val okHttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpLoggingInterceptor}"

    private val junit = "junit:junit:${Versions.junit}"
    private val extJUnit = "androidx.test.ext:junit:${Versions.extJunit}"
    private val espressoCore = "androidx.test.espresso:espresso-core:${Versions.espresso}"

    private val dataStore = "androidx.datastore:datastore-preferences:${Versions.dataStore}"


    private val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
    private val hiltKapt = "com.google.dagger:hilt-compiler:${Versions.hilt}"

    val appLibraries = arrayListOf<String>().apply {
        add(kotlinStdLib)
        add(coreKtx)
        add(appcompat)
        add(constraintLayout)
        add(material)
        add(roomRuntime)
        add(roomKtx)
        add(fragmentKtx)
        add(liveDataKtx)
        add(koinAndroid)
        add(koinAndroidExt)
        add(paging)
        add(retrofit)
        add(retrofitGsonConverter)
        add(retrofitMock)
        add(okHttpLoggingInterceptor)
        add(dataStore)
        add(hilt)
    }

    val androidTestLibraries = arrayListOf<String>().apply {
        add(extJUnit)
        add(espressoCore)
    }

    val testLibraries = arrayListOf<String>().apply {
        add(junit)
    }

    val kaptLibraries = arrayListOf<String>().apply {
        add(roomKapt)
        add(hiltKapt)
    }
}

fun DependencyHandler.kapt(list: List<String>) {
    list.forEach { dependency ->
        add("kapt", dependency)
    }
}

fun DependencyHandler.implementation(list: List<String>) {
    list.forEach { dependency ->
        add("implementation", dependency)
    }
}

fun DependencyHandler.androidTestImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("androidTestImplementation", dependency)
    }
}

fun DependencyHandler.testImplementation(list: List<String>) {
    list.forEach { dependency ->
        add("testImplementation", dependency)
    }
}