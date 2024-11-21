plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "2.0.21"
  id("org.jetbrains.intellij.platform") version "2.1.0"
}

repositories {
  mavenCentral()

  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity("2023.1")

    bundledPlugin("com.intellij.java")

    instrumentationTools()
  }
}

intellijPlatform {
  projectName = "intellij-plugin-choose-license"

  pluginConfiguration {
    version = "1.3.2"
  }

  publishing {
    token = System.getenv("PUBLISH_TOKEN")
  }
}

kotlin {
  jvmToolchain(17)
}
