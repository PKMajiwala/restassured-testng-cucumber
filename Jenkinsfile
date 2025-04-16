pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean test'
            }
        }
        stage('Report') {
            steps {
                sh 'allure generate target/allure-results --clean -o target/allure-report'
                archiveArtifacts 'target/allure-report/**'
            }
        }
    }
    post {
        always {
            emailext(
              to: 'qa-team@example.com',
              subject: "API Test Report",
              body: "The test suite has completed. Check Allure Report.",
              attachmentsPattern: 'target/allure-report/index.html'
            )
        }
    }
}
