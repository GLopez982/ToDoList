pipeline {
    agent any

    environment {
        IMAGE_NAME = 'todolist'
        DOCKER_HUB_REPO = 'glopez9982/todolist'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/GLopez982/ToDoList.git'
            }
        }

        stage('Build') {
            steps {
                bat 'mvn clean package'
            }
        }

        stage('Test') {
            steps {
                bat 'mvn test'
            }
        }

        stage('Docker Build & Push') {
            steps {
                bat 'docker build -t $IMAGE_NAME .'
                bat 'docker tag $IMAGE_NAME $DOCKER_HUB_REPO:latest'
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: 'https://index.docker.io/v1/']) {
                    bat 'docker push $DOCKER_HUB_REPO:latest'
                }
            }
        }

        stage('Deploy') {
            steps {
                bat 'docker run -d -p 8080:8080 --name todolist $DOCKER_HUB_REPO:latest'
            }
        }
    }
}