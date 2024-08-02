plugins {
    id("java")
    id("groovy")
    id("checkstyle")
    id("org.ec4j.editorconfig") version "0.1.0"
    id("jacoco")
//    id("org.sonarqube") version "3.5.0.2730"
    id("org.sonarqube") version "4.4.1.3373"
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
    finalizedBy("jacocoTestReport")
}

checkstyle {
    configFile = file("config/checkstyle/naver-checkstyle-rules.xml")
    configProperties =
        mapOf("suppressionFile" to "config/checkstyle/naver-checkstyle-suppressions.xml")
}

tasks.named("check") {
    dependsOn("editorconfigCheck")
}

editorconfig {
    excludes = listOf(".github", "gradle/**", "gradlew", "gradlew.bat", "out/**", "build/**")
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required = true
    }

    finalizedBy("jacocoTestCoverageVerification")
}

tasks.jacocoTestCoverageVerification {
    violationRules {
        rule {
            enabled = true

            limit {
                counter = ("LINE")
                value = ("COVEREDRATIO")
                minimum = ("0.90".toBigDecimal())
            }
        }
    }
}

sonar {
    properties {
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.organization", "chupin-tech_easyvent")
        property("sonar.projectKey", "chupin-tech")
        property("sonar.sources", "src")
        property("sonar.language", "java")
        property("sonar.sourceEncoding", "UTF-8")
//        property("sonar.test.exclusions", "jacocoExcludePatterns.join(',')")
        property("sonar.test.inclusions", "**/*Test.groovy")
        property("sonar.java.coveragePlugin", "jacoco")
        property(
            "sonar.coverage.jacoco.xmlReportPaths",
            "build/reports/jacoco/test/jacocoTestReport.xml"
        )
    }
}
