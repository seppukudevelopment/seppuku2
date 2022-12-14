plugins {
  alias(mainLibs.plugins.kotlin.jvm)
}

dependencies {
  mainLibs {
    implementation(kotlin.stdlib)
    implementation(kotlin.reflect)
  }

  testLibs {
    testImplementation(kotlin.test)
  }
}

tasks {
  test {
    useJUnitPlatform()
  }
}
