pipeline {
    stages {
        stage ('Stage of Compiling') {
            steps {
                    sh 'mvn clean compile'
            }
        }
        stage ('Testing') {
            steps {
                    sh 'mvn test'
            }
        }
        stage('Building the Docker Image') {
            steps {
                 script {
                     sh 'mvn clean install && docker build -t orkhan2000/phone-app-frontend .'
                 }
            }
        }
        stage('Deploying to Docker Hub') {
            steps {
                 script {
                     withCredentials([string(credentialsId: 'dockerhubpwd', variable: 'dockerhubpwd')]) {
                         sh 'docker login -u niz242001 -p ${dockerhubpwd}'
                     }
                     sh 'docker push  niz242001/pbprojectback'
                 }
            }
        }
    }
}
