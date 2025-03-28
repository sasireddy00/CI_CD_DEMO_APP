pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/sasireddy00/CI_CD_DEMO_APP.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }
        stage('Docker Build & Push') {
            steps {
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh 'docker build -t sasireddy06/ci-cd-demo:latest .'
                    sh 'docker push sasireddy06/ci-cd-demo:latest'
                }
            }
        }
    }
}
