// buildSrc 是独立构建，不会继承根 settings.gradle.kts 里的插件仓库配置。
// 它默认只用 gradlePluginPortal()，而插件门户回源到 Maven Central 时会被 403 拒绝，
// 导致 buildSrc 的 buildscript classpath（plugins { kotlin-dsl }）解析失败。
// 这里补上与根构建一致的阿里云镜像。
pluginManagement {
    repositories {
        google()
        maven("https://maven.aliyun.com/repository/gradle-plugin")
        maven("https://maven.aliyun.com/repository/public")
        // 同根构建：CI 上 Maven Central 403、阿里云对较新版本 404，用谷歌镜像兜底
        maven("https://maven-central.storage-download.googleapis.com/maven2/")
        gradlePluginPortal()
        mavenCentral()
    }
}
