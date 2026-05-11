plugins {
  id("java")
  id("org.jetbrains.kotlin.jvm") version "2.3.21"
  id("org.jetbrains.intellij.platform") version "2.16.0"
}

repositories {
  mavenCentral()

  intellijPlatform {
    defaultRepositories()
  }
}

dependencies {
  intellijPlatform {
    intellijIdeaCommunity("2023.3")

    bundledPlugin("com.intellij.java")
  }
}

intellijPlatform {
  projectName = "intellij-plugin-choose-license"

  pluginConfiguration {
    version = "1.3.4"

    ideaVersion {
      sinceBuild = "233"
      untilBuild = provider { null }
    }
  }

  publishing {
    token = System.getenv("PUBLISH_TOKEN")
  }
}

kotlin {
  jvmToolchain(17)
}
