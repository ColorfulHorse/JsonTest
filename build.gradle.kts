
allprojects {
    repositories {
        mavenCentral()
        maven("https://maven.aliyun.com/repository/jcenter")
    }
}

buildscript {
    repositories {
        mavenCentral()
        maven("https://maven.aliyun.com/repository/jcenter")
    }

    dependencies {
        classpath(kotlin("gradle-plugin", version = "1.5.10"))
//        classpath(kotlin("noarg", version = "1.5.10"))
    }
}
