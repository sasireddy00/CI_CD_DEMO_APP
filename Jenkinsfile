pipeline {
    agent any

    environment {
        VERSION = "1.0.${env.BUILD_NUMBER}" // Dynamic version using Jenkins' BUILD_NUMBER
        IMAGE_NAME = 'sasireddy06/ci-cd-demo'
        DOCKER_CREDENTIALS = 'docker-hub-credentials'
    }

    stages {
        stage('Build') {
            agent {
                docker {
                    image "maven:3.9.6-eclipse-temurin-17"
                    args "-v ${WORKSPACE}:/app -w /app"
                }
            }
            steps {
                sh 'mvn clean package'
                archiveArtifacts artifacts = target/*
            }
        }

        stage('Test') {
            agent {
                docker {
                    image "maven:3.9.6-eclipse-temurin-17"
                    args "-v ${WORKSPACE}:/app -w /app"
                }
            }
            steps {
                sh 'mvn test'
            }
        }

        stage('Docker Build & Push') {
            steps {
                unstash 'app-jar'  // Retrieve JAR before building Docker image

                withDockerRegistry([credentialsId: DOCKER_CREDENTIALS, url: '']) {
                    sh "docker build -t ${IMAGE_NAME}:${VERSION} ."
                    sh "docker push ${IMAGE_NAME}:${VERSION}"
                }
            }
        }
    }
}