
allprojects {
    repositories {
        mavenCentral()
    }
}

buildscript {
    repositories {
        mavenCentral()
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.5.10"))
//        classpath(kotlin("noarg", version = "1.5.10"))
    }
}
