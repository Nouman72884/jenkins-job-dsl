job('NodeJS Docker example') {
    scm {
        git('git://github.com/Nouman72884/docker-demo.git') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('Nouman72884')
            node / gitConfigEmail('noumansatti7@gmail.com')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
    wrappers {
        nodejs('nodejs') // this is the name of the NodeJS installation in 
                         // Manage Jenkins -> Configure Tools -> NodeJS Installations -> Name
    }
    steps {
        dockerBuildAndPublish {
            repositoryName('wardviaene/docker-nodejs-demo')
            tag('${GIT_REVISION,length=9}')
            registryCredentials('dockerhub')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}
