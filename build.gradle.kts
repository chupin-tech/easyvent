plugins {
    id("java")
    id("groovy")
    id("checkstyle")
    id("org.ec4j.editorconfig") version "0.1.0"
}

group = "org.chupin"
version = "0.1"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.code.findbugs:jsr305:3.0.2")

    testImplementation("org.spockframework:spock-core:2.4-M4-groovy-4.0")
}

tasks.test {
    useJUnitPlatform()
}

checkstyle {
    configFile = file("config/checkstyle/naver-checkstyle-rules.xml")
    configProperties = mapOf("suppressionFile" to "config/checkstyle/naver-checkstyle-suppressions.xml")
}

tasks.named("check") {
    dependsOn("editorconfigCheck")
}

editorconfig {
    excludes = listOf(".github", "gradle/**", "gradlew", "gradlew.bat", "out/**", "build/**")
}
