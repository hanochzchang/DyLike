// buildSrc 是独立构建，不会继承根 settings.gradle.kts 里的插件仓库配置。
// 它默认只用 gradlePluginPortal()，而插件门户回源到 Maven Central 时会被 403 拒绝，
// 导致 buildSrc 的 buildscript classpath（plugins { kotlin-dsl }）解析失败。
// 这里补上与根构建一致的阿里云镜像。
pluginManagement {
    repositories {
        google()
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        maven("https://maven.aliyun.com/repository/public")
        gradlePluginPortal()
        mavenCentral()
    }
}
