plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.asciidoctor.jvm.convert") version "3.3.2" // #2
}

group = "sw.sustainable"
version = "0.0.1-SNAPSHOT"

// Ascii Doc Snippet Directory
val snippetsDir by extra { file("build/generated-snippets") } // #3
// Settings Configurations
// https://velog.io/@glencode/Kotlin-Gradle%EC%9D%84-%EC%82%AC%EC%9A%A9%ED%95%98%EC%97%AC-Spring-REST-Docs-%EC%A0%81%EC%9A%A9%ED%95%98%EA%B8%B0
var asciidoctorExt = configurations.create("asciidoctor") {
    extendsFrom(configurations["testImplementation"])
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
    asciidoctorExt
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.projectlombok:lombok")
    implementation("com.google.code.gson:gson")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    runtimeOnly("com.h2database:h2")
    runtimeOnly("com.mysql:mysql-connector-j")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-actuator")
    testImplementation("com.google.code.gson:gson")
    testAnnotationProcessor("org.projectlombok:lombok")
    testImplementation("org.projectlombok:lombok")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc") // #1
    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// Ascii Doc Create Tasks
tasks {
    // Test 결과를 snippet Directory에 출력
    test {
        outputs.dir(snippetsDir)
    }

    asciidoctor {
        // test 가 성공해야만, 아래 Task 실행
        configurations("asciidoctorExt")
        baseDirFollowsSourceFile()
        dependsOn(test)
        // 기존에 존재하는 Docs 삭제(문서 최신화를 위해)
        doFirst {
            delete(file("src/main/resources/static/docs"))
        }
        // snippet Directory 설정
        inputs.dir(snippetsDir)
        // Ascii Doc 파일 생성
        doLast {
            copy {
                from("build/docs/asciidoc")
                into("src/main/resources/static/docs")
            }
        }
    }

    register("copyDocument", Copy::class) {
        dependsOn(asciidoctor)
        doFirst {
            delete(file("src/main/resources/static/docs"))
        }
        from(file("build/docs/asciidoc"))
        into(files("src/main/resources/static/docs"))
    }

    build {
        // Ascii Doc 파일 생성이 성공해야만, Build 진행
        dependsOn(asciidoctor)
    }
}
