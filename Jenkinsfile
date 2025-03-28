pipeline {
    agent any

    environment {
        IMAGE_NAME = 'sasireddy06/ci-cd-demo:latest'
        DOCKER_CREDENTIALS = 'docker-hub-credentials'
    }

    stages {
        stage('Build') {
            agent {
                docker {
                    image "maven:3.9.6-eclipse-temurin-17"
                    args "-v $PWD:/app -w /app"
                }
            }
            steps {
                sh 'mvn clean package'
                stash includes: 'target/*.jar', name: 'app-jar'
            }
        }

        stage('Test') {
            agent {
                docker {
                    image "maven:3.9.6-eclipse-temurin-17"
                    args "-v $PWD:/app -w /app"
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
                    sh 'docker build -t $IMAGE_NAME .'
                    sh 'docker push $IMAGE_NAME'
                }
            }
        }
    }
}