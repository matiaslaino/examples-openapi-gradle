plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot") version "3.1.0"
    id("org.openapi.generator") version "6.6.0"
    id("io.spring.dependency-management") version "1.1.0"

    `java-library`
}

group = "com.example"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("jakarta.validation:jakarta.validation-api:3.0.2")
}

kotlin {
    jvmToolchain(17)
}

tasks.withType<Test> {
    useJUnitPlatform()
}

openApiGenerate {
    generatorName.set("kotlin-spring")
    inputSpec.set("$rootDir/specs/spec.yaml")
    //outputDir.set("$buildDir/generated")
    apiPackage.set("com.mlaino.examples.openapi.apis.server")
    configOptions.putAll(
        mapOf(
            Pair("datesLibrary", "java8"),
            Pair("gradleBuildFile", "false"),
            Pair("useSpringBoot3", "true"),
            Pair("documentationProvider", "none"),
        )
    )
    generateApiTests.set(false)
}

sourceSets.getByName("main").kotlin.srcDir("$buildDir/generate-resources")
tasks.compileKotlin.get().dependsOn(tasks.openApiGenerate)