node {
    def server
    def buildInfo
    def rtMaven
    
    stage ('Clone') {
        git url: 'https://github.com/staskorz/artifactory-drill-01.git'
    }
 
    stage ('Artifactory configuration') {
        // Obtain an Artifactory server instance, defined in Jenkins --> Manage:
        server = Artifactory.server 'local-artifactory-01'

        rtMaven = Artifactory.newMavenBuild()
        rtMaven.tool = 'maven' // Tool name from Jenkins configuration
        rtMaven.deployer releaseRepo: 'libs-release-local', snapshotRepo: 'libs-snapshot-local', server: server
        rtMaven.resolver releaseRepo: 'libs-release', snapshotRepo: 'libs-snapshot', server: server
        rtMaven.deployer.deployArtifacts = false // Disable artifacts deployment during Maven run

        buildInfo = Artifactory.newBuildInfo()
    }
 
    // stage ('Test') {
    //     rtMaven.run pom: 'pom.xml', goals: 'clean test'
    // }
        
    stage ('Package') {
        rtMaven.run pom: 'pom.xml', goals: 'package', buildInfo: buildInfo
    }

    stage ('Deploy') {
        rtMaven.deployer.deployArtifacts buildInfo
    }
        
    stage ('Publish build info') {
        server.publishBuildInfo buildInfo
    }

    stage ('Scan') {
        server.publishBuildInfo buildInfo
        def scanConfig = [
            'buildName'      : buildInfo.name,
            'buildNumber'    : buildInfo.number
        ]

        server.xrayScan scanConfig
    }
}
