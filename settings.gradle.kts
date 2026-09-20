import org.gradle.api.initialization.resolve.RepositoriesMode

// CI 跑在美西的 GitHub runner 上：Maven Central 返回 403，阿里云镜像返回 502。
// 502 会被 Gradle 当成一次失败的资源请求，重试三次仍失败后整个 plugin classpath
// 解析就报 not found，所以 CI 上用 GRADLE_SKIP_ALIYUN=1 直接跳过阿里云，改走
// 谷歌托管的 Central 全量镜像。国内本地构建不带这个变量，仍然优先走阿里云。
// 注意：pluginManagement 是分阶段单独编译的，看不到脚本顶层的 val，只能写字面量。
pluginManagement {
    repositories {
        google()
        if (System.getenv("GRADLE_SKIP_ALIYUN") == null) {
            maven("https://maven.aliyun.com/repository/public")
        }
        maven("https://maven-central.storage-download.googleapis.com/maven2/")
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.PREFER_SETTINGS)
    repositories {
        google()
        if (System.getenv("GRADLE_SKIP_ALIYUN") == null) {
            maven("https://maven.aliyun.com/repository/public")
        }
        maven("https://jitpack.io")
        maven("https://maven-central.storage-download.googleapis.com/maven2/")
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
