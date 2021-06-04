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

//noArg {
//    // 空参构造函数是否调用辅助构造函数初始化默认值  好像有bug，不生效
//    invokeInitializers = true
//    // 注解的完整类名
//    annotations("NoArg")
//}


dependencies {
    testImplementation(kotlin("test"))
    implementation("com.google.code.gson:gson:2.8.7")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.12.0")
}


tasks.test {
    useJUnit()
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}