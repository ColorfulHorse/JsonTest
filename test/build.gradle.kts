import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
    kotlin("kapt")
//    id("org.jetbrains.kotlin.plugin.noarg")
}

repositories {
    mavenCentral()
}

sourceSets["main"].java.srcDir("src/main/java")


dependencies {
    testImplementation(kotlin("test"))
    implementation("com.google.code.gson:gson:2.8.7")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.12.0")
    implementation("com.alibaba:fastjson:1.2.35")
//    implementation("com.alibaba.fastjson2:fastjson2-kotlin:2.0.17")
}


tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}