pipeline {
    agent any

    environment {
        IMAGE_NAME = 'todolist'
        DOCKER_HUB_REPO = 'GLopez9982/todolist'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/GLopez982/ToDoList.git'
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
                withDockerRegistry([credentialsId: 'docker-hub-credentials', url: 'https://index.docker.io/v1/']) {
                    sh 'docker push $DOCKER_HUB_REPO:latest'
                }
            }
        }

        stage('Deploy') {
            steps {
                sh 'docker run -d -p 8080:8080 --name todolist $DOCKER_HUB_REPO:latest'
            }
        }
    }
}