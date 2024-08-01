plugins {
	java
	id("org.springframework.boot") version "3.3.1"
	id("io.spring.dependency-management") version "1.1.5"
}

group = "com.anuj"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

//dependencies {
//	implementation 'org.springframework.boot:spring-boot-starter-actuator'
//	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
//	implementation 'org.springframework.boot:spring-boot-starter-web'
//	implementation 'org.springframework.boot:spring-boot-starter-validation'
//
//	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-starter-webmvc-ui
//	implementation group: 'org.springdoc', name: 'springdoc-openapi-starter-webmvc-ui', version: '2.6.0'
//
//	compileOnly 'org.projectlombok:lombok'
//	developmentOnly 'org.springframework.boot:spring-boot-devtools'
//	runtimeOnly 'org.postgresql:postgresql'
//	annotationProcessor 'org.projectlombok:lombok'
//	testImplementation 'org.springframework.boot:spring-boot-starter-test'
//	testRuntimeOnly 'org.junit.platform:junit-platform-launcher'
//}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")

	//swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.6.0")


	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	runtimeOnly("org.postgresql:postgresql")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}


tasks.withType<Test> {
	useJUnitPlatform()
}