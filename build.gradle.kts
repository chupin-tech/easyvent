plugins {
    id("java")
    id("checkstyle")
    id("org.ec4j.editorconfig") version "0.1.0"
}

group = "org.chupin"
version = "0.1"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
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
    excludes = listOf(".github/**", "gradle/**", "gradlew", "gradlew.bat")
}
