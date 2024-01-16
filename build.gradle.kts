plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "1.9.22"
  id("org.jetbrains.intellij") version "1.16.1"
}

group = "sh.suiyun"
version = "1.1.0"

repositories {
  mavenCentral()
}

// https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
  version = "2023.1"
  type = "IC"
}

tasks {
  withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
  }

  withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "17"
  }

  // 配置插件的兼容性范围
  // https://plugins.jetbrains.com/docs/intellij/build-number-ranges.html#platformVersions
  patchPluginXml {
    sinceBuild = "231"
  }

  // 插件发布
  publishPlugin {
    token = System.getenv("PUBLISH_TOKEN")
  }
}
