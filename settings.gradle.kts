plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "cabin-cache"
include("cabin-core")
include("cabin-server")
include("cabin-client")
include("cabin-example-spring")
