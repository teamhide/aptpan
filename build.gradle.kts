plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25" apply false
    id("org.springframework.boot") version "3.5.0"
    id("io.spring.dependency-management") version "1.1.7" apply false
    kotlin("plugin.jpa") version "1.9.25" apply false
    kotlin("plugin.allopen") version "1.9.25"
    id("org.jlleitschuh.gradle.ktlint") version "12.3.0"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(24)
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

allprojects {
    group = "com.teamhide"
    version = "0.0.1-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin = "kotlin")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "org.jetbrains.kotlin.plugin.spring")

    allOpen {
        annotation("jakarta.persistence.Entity")
        annotation("jakarta.persistence.MappedSuperclass")
        annotation("jakarta.persistence.Embeddable")
    }

    dependencies {
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

project(":aptpan-application") {
    tasks.bootJar {
        enabled = false
    }
    tasks.jar {
        enabled = true
    }

    dependencies {
        implementation(project(":aptpan-domain"))
        implementation(project(":aptpan-infrastructure"))
    }
}

project(":aptpan-domain") {
    tasks.bootJar {
        enabled = false
    }
    tasks.jar {
        enabled = true
    }

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-data-redis")
        runtimeOnly("com.mysql:mysql-connector-j")
    }
}

project(":aptpan-infrastructure") {
    tasks.bootJar {
        enabled = false
    }
    tasks.jar {
        enabled = true
    }

    dependencies {
    }
}

project(":aptpan-presentation") {
    tasks.bootJar {
        enabled = true
    }
    tasks.jar {
        enabled = true
    }

    dependencies {
        implementation(project(":aptpan-application"))
        implementation(project(":aptpan-domain"))
        implementation(project(":aptpan-infrastructure"))
        implementation("org.springframework.boot:spring-boot-starter-validation")
        implementation("org.springframework.boot:spring-boot-starter-web")
    }
}
