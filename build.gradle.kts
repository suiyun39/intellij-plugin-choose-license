plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "1.9.22"
  id("org.jetbrains.intellij") version "1.17.0"
}

group = "sh.suiyun"
version = "1.2.0"

repositories {
  mavenCentral()
}

// https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version = "2023.1"
  type = "IC"
  updateSinceUntilBuild = false
}

tasks {
  withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
  }

  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
  }

  // 插件发布
  publishPlugin {
    token = System.getenv("PUBLISH_TOKEN")
  }
}
