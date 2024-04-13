pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'java17'
        git 'git'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/ikrame-elkailech/FilRouge/tree/master'
            }
        }

        stage('Build') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn clean install'
                    } else {
                        bat 'mvn clean install'
                    }
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn test'
                    } else {
                        bat 'mvn test'
                    }
                }
            }
        }

        stage('Report') {
            steps {
                script {
                    if (isUnix()) {
                        sh 'mvn clean site'
                    } else {
                        bat 'mvn clean site'
                    }
                }
            }
        }
    }

    post {
        success {
            echo 'Build succeeded!'
        }

        failure {
            echo 'Build failed!'
        }

        unstable {
            echo 'Build unstable!'
        }
    }
}
