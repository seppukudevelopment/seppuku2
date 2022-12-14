import kotlinx.benchmark.gradle.JvmBenchmarkTarget

plugins {
  alias(mainLibs.plugins.kotlin.jvm)
  alias(testLibs.plugins.kotlinx.benchmark)
}

dependencies {
  mainLibs {
    implementation(kotlin.stdlib)
    implementation(kotlin.reflect)
    implementation(byteBuddy)
  }

  projects {
    implementation(seppukuSdk.ecs)
  }

  testLibs {
    testImplementation(kotlinx.benchmark.runtime)
  }
}

benchmark {
  configurations {
    named("main") {
      iterationTime = 5
    }
  }

  targets {
    register("test") {
      this as JvmBenchmarkTarget
      jmhVersion = "1.36"
    }
  }
}
