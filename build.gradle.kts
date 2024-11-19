plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "2.0.21"
  id("org.jetbrains.intellij") version "1.17.4"
}

group = "io.suiyun"
version = "1.3.0"

repositories {
  mavenCentral()
}

// https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version = "2023.1"
  type = "IC"
  updateSinceUntilBuild = false
}

kotlin {
  jvmToolchain(17)
}

tasks {
  withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
  }

  // 插件发布
  publishPlugin {
    token = System.getenv("PUBLISH_TOKEN")
  }
}
