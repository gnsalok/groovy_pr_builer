#!groovy
import groovy.json.JsonOutput

def checkoutCommitHash() {
    checkout scm
    if(!env.BUILD_COMMIT_HASH) {
        env.BUILD_COMMIT_HASH = getCommitHash()
    }
    sh "git checkout $BUILD_COMMIT_HASH"
}

def test(){
    return "test"
}

    

def getCommitHash() {
    def gitCommit = sh(returnStdout: true, script: 'git rev-parse HEAD').trim()
    return gitCommit
}

def getCurrentBranch() {
    sh(returnStdout: true, script: 'git rev-parse --abbrev-ref HEAD').trim()
}

def getChangeSet(def startTagOrCommit, def endTagOrCommit = ""){
    sh(
                 script: "git --no-pager diff ${startTagOrCommit} ${endTagOrCommit} --name-only",
                    returnStdout: true
                 ).split('\n')
}
