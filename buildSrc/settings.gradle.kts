// buildSrc 是独立构建，不会继承根 settings.gradle.kts 里的插件仓库配置。
// 它默认只用 gradlePluginPortal()，而插件门户回源到 Maven Central 时会被 403 拒绝，
// 导致 buildSrc 的 buildscript classpath（plugins { kotlin-dsl }）解析失败。
// 这里补上与根构建一致的阿里云镜像。
pluginManagement {
    repositories {
        google()
        // 同根构建：CI 上阿里云会 502，用 GRADLE_SKIP_ALIYUN=1 跳过它
        if (System.getenv("GRADLE_SKIP_ALIYUN") == null) {
            maven("https://maven.aliyun.com/repository/gradle-plugin")
            maven("https://maven.aliyun.com/repository/public")
        }
        maven("https://maven-central.storage-download.googleapis.com/maven2/")
        gradlePluginPortal()
        mavenCentral()
    }
}
