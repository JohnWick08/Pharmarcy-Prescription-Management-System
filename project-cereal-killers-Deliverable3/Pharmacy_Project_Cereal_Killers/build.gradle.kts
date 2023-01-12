import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.kapt3.base.Kapt.kapt
import java.util.regex.Pattern.compile

plugins {
    id("org.springframework.boot") version "2.7.5"
    id("io.spring.dependency-management") version "1.0.15.RELEASE"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.10"
    kotlin("kapt") version "1.5.10"
}

group = "com.example"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    //implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    //implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity5")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.webjars:webjars-locator:0.45")
    implementation("org.webjars:bootstrap:5.2.3")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.mapstruct:mapstruct:1.5.0.Beta2")
    implementation("org.junit.platform:junit-platform-suite:1.8.2")
    //kapt("org.mapstruct:mapstruct-processor:1.5.0.Beta2")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("org.postgresql:postgresql")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.cucumber:cucumber-java8:7.8.1")
    testImplementation("io.cucumber:cucumber-spring:7.8.1")
    testImplementation("io.cucumber:cucumber-junit-platform-engine:7.8.1")
    testImplementation("org.junit.platform:junit-platform-suite:1.9.0")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
