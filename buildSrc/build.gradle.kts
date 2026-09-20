plugins {
    `kotlin-dsl`
}

repositories {
    google()
    // CI 上阿里云会 502，用 GRADLE_SKIP_ALIYUN=1 跳过，改走谷歌托管的 Central 镜像
    if (System.getenv("GRADLE_SKIP_ALIYUN") == null) {
        maven("https://maven.aliyun.com/repository/public")
    }
    maven("https://maven-central.storage-download.googleapis.com/maven2/")
    gradlePluginPortal()
    mavenCentral()
}
