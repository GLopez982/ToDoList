pipeline {
    agent any

    environment {
        IMAGE_NAME = 'todolist'
        DOCKER_HUB_REPO = 'your-dockerhub-username/my-java-app'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/your-repo.git'
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
                sh 'docker build -t $IMAGE_NAME .'
                sh 'docker tag $IMAGE_NAME $DOCKER_HUB_REPO:latest'
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: '']) {
                    sh 'docker push $DOCKER_HUB_REPO:latest'
                }
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker run -d -p 8080:8080 --name my-java-app $DOCKER_HUB_REPO:latest'
            }
        }
    }
}