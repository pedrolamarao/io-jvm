plugins {
    id("java-library")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
}

java.toolchain.languageVersion.set( JavaLanguageVersion.of(20) )

tasks.withType<JavaCompile>().configureEach {
    options.compilerArgs.add("--enable-preview")
}

tasks.named<Test>("test").configure {
    setForkEvery(1)
    jvmArgs = listOf("--enable-native-access=ALL-UNNAMED","--enable-preview")
    useJUnitPlatform()
}
