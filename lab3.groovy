pipeline {
    agent any

    tools {
        maven 'Maven-3.9.9'
    }

    stages {
        stage('Checkout') {
            steps {
                // Get some code from a GitHub repository
                git 'https://github.com/PeterNguyenn/demo-lab3.git'

                echo 'Repository cloned'
            }
        }
        stage('Build') {
            steps {
                echo 'Building with Maven...'
                sh 'mvn -B package --file pom.xml'
                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.failure.ignore=true clean package"
                echo 'Build completed'
            }
        }
        stage('Test') {
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }
    }
}
