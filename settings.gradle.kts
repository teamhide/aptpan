plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
rootProject.name = "aptpan"
include("aptpan-domain")
include("aptpan-application")
include("aptpan-presentation")
include("aptpan-infrastructure")
include("support")
include("support:migration")
findProject(":support:migration")?.name = "migration"
include("support:logging")
findProject(":support:logging")?.name = "logging"
