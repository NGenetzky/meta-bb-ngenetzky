#!/usr/bin/env groovy

// TODO: Fix lint errors (I don't know how to write groovey)
// https://codenarc.org/codenarc-rules-dry.html#duplicatestringliteral-rule
// https://codenarc.org/codenarc-rules-size.html#nestedblockdepth-rule
// https://codenarc.org/codenarc-rules-convention.html#compilestatic-rule

pipeline {

    // https://www.jenkins.io/doc/book/pipeline/syntax/#agent
    agent { node { any } }

    // triggers { // TODO
        // https://www.jenkins.io/doc/book/pipeline/syntax/#triggers
        // pollSCM('H */4 * * 1-5') // TODO: likely want to run on some changes.
        // cron('@midnight') // TODO: Likely want to run on a schedule
        // NOTE: @midnight actually means some time between 12:00 AM and 2:59 AM.
    // }

    // parameters {
        // Valid parameter types are booleanParam, choice, file, text, password, run, or string.
        // Are created before the first time the pipeline is run, therefore you
        // should access them via params: "echo ${params.region}"

        // booleanParam(defaultValue: true, description: '', name: 'userFlag')
        // string(defaultValue: "TEST", description: 'What environment?', name: 'userFlag')
        // choice(choices: ['US-EAST-1', 'US-WEST-2'], description: 'What AWS region?', name: 'region')
    // }

    // environment { // TODO
        // SERVICE_CREDS = credentials('my-predefined-username-password')
        // NOTE: Service user is SERVICE_CREDS_USR, Service password is $SERVICE_CREDS_PSW

        // SSH_CREDS = credentials('my-predefined-ssh-creds')
        // NOTE: SSH private key is located at $SSH_CREDS, SSH user is $SSH_CREDS_USR
    // }

    // options { // TODO
        // https://jenkins.io/doc/book/pipeline/syntax/#options

        // timestamps()
        // skipStagesAfterUnstable()
        // buildDiscarder(logRotator(numToKeepStr:'5')) // TODO
    // }

    stages {
        // https://www.jenkins.io/doc/book/pipeline/syntax/#sequential-stages
        // Note that a stage must have one and only one of steps, stages, parallel, or matrix.

        // TODO: Add 'when' 'triggeredBy' conditions
        // when { triggeredBy 'SCMTrigger' }
        // when { triggeredBy 'TimerTrigger' }
        // when { triggeredBy 'BuildUpstreamCause' }
        // when { triggeredBy cause: "UserIdCause", detail: "vlinde" }

        // TODO: Include/Exclude stages based based on git branch or tag
        // when { allOf { branch 'master'; environment name: 'DEPLOY_TO', value: 'production' } }
        // when { tag "release-*" }
        // when { branch 'master' }
        // when { branch pattern: "release-\\d+", comparator: "REGEXP"}

        // TODO: Include/Exclude stages based based on changeRequest
        // Possible attributes are id, target, branch, fork, url, title, author, authorDisplayName, and authorEmail.
        // when { changeRequest target: 'master' }

        // TODO: Include/Exclude stages based based on changeset or changelog
        // when { changeset "**/*.js" }
        // when { changelog '.*^\\[DEPENDENCY\\] .+$' }

        stage('preflight') {
            agent { node { } }

            // parallel {} // NOTE: We may want to make parallel stages here.
            // TODO: PAR-760 Bitbucket build status should be set by Jenkins builds
            steps {
                // sh 'pip --version' // TODO: pip: not found
                // sh 'python --version' // NOTE: Python 2.7.18
                // sh 'docker --version' // NOTE: Docker version 20.10.12, build e91ed57
                // sh './script/test-shellcheck.bash' // TODO: shellcheck: not found
                // sh 'docker system info'
                sh 'git diff --check' // https://ardalis.com/detect-git-conflict-markers/

                // catchError(buildResult: 'SUCCESS', stageResult: 'UNSTABLE') {
                //     sshagent(credentials: ['2022-readonly'], ignoreMissing: true) {
                //         sh './script/test-connectivity.bash'
                //     }
                // }
            }

            stage('cibuild') {
                steps {
                    sh './scripts/cibuild'
                }
            }
        }

    }
}
