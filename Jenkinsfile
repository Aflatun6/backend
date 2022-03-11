pipeline {
    agent any

    stages {
        stage ('1 gradle clean') {
            steps {
                     gradlew('clean')
            }
        }
        stage ('2 gradle test') {
            steps {
                     gradlew('test')
            }
            post {
                     always {
                         junit '**/build/test-results/test/TEST-*.xml'
                      }
            }
        }

        stage('3 sonarqube code analysis') {
            steps {
                     gradlew('sonarqube')
            }
        }

        stage('4 docker build image') {
            steps {
                 script {
                     sh 'docker build -t avelibeyli6/backend .'
                 }
            }
        }
        stage('5 deploy the image') {
            steps {
                 script {
                     withCredentials([string(credentialsId: 'Docker-Hub-Password', variable: 'dockerhubpwd')]) {
                         sh 'docker login -u avelibeyli6 -p ${dockerhubpwd}'
                     }
                     sh 'docker push  avelibeyli6/backend'
                 }
            }
        }
    }
}