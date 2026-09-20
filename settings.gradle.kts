import org.gradle.api.initialization.resolve.RepositoriesMode

// CI 跑在美西的 GitHub runner 上：Maven Central 对它返回 403，阿里云镜像对较新的
// 版本（如 KSP 2.3.6）会 404。补一个谷歌托管的 Central 全量镜像兜底，放在阿里云之后，
// 国内本地构建仍然优先走阿里云。
val centralMirror = "https://maven-central.storage-download.googleapis.com/maven2/"

pluginManagement {
    repositories {
        google()
        maven("https://maven.aliyun.com/repository/public")
        maven(centralMirror)
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        maven("https://maven.aliyun.com/repository/public")
        maven("https://jitpack.io")
        maven(centralMirror)
        mavenCentral()
    }
}

rootProject.name = "zhxs"

// lib
include(":lib-base")
include(":lib-common:comm-archive")
include(":lib-dm:dm-view")
include(":lib-player:dkplayer-java")
include(":lib-player:player-exo")
include(":lib-player:player-mpv")
include(":lib-player:player-ui")

// app
include(":dy-player")

val localSettings = file("settings.local.gradle.kts")
if (localSettings.exists()) {
    apply(from = localSettings)
}
