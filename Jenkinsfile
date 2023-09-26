node{
stage("Git Clone"){

        git credentialsId: 'GIT_HUB_CREDENTIALS', url: 'git@github.com:VSVDEv/jenkins.git'
    }
stage('Gradle Build') {

       echo "*****************Cleaning...."
       sh "gradle clean"
       echo "*****************Completed clean"
       echo "*****************Compiling"
       sh "gradle compileJava"
       sh "gradle processResources"
       sh "gradle classes"
       sh "gradle compileTestJava"
       echo "*****************Finish Compiling"
       echo "*****************Analyze"
       sh "gradle checkstyleMain"
       sh "gradle checkstyleTest"
	   echo '*****************Checkstyle finished'
	   echo '*****************Run pmd'
	   sh "gradle pmdMain"
	   sh "gradle pmdTest"
	   echo '*****************Finished pmd'
	   echo '*****************Run unit tests'
	    sh "gradle test"
       echo '*****************Unit tests finished'
       echo '*****************Integration tests'
         sh "gradle integrationTest"
       echo '*****************Integration tests finished'
       echo '*****************Jar'
          sh "gradle bootJarMainClassName"
          sh "gradle bootJar"
          sh "gradle assemble"
       echo '*****************Jar finished'
       echo '*****************Build finished'

    }

    stage("Docker build"){
        sh 'docker version'
        sh 'docker build -t vsvdev-docker-demo .'
        sh 'docker image list'
        sh 'docker tag vsvdev-docker-demo vsvdevuaco/vsvdev-docker-demo:vsvdev-docker-demo'
    }
    stage("Docker Login"){
        withCredentials([string(credentialsId: 'DOCKER_HUB_PASSWORD', variable: 'PASSWORD')]) {
            sh 'docker login -u vsvdevuaco -p $PASSWORD'
        }
    }
    stage("Push Image to Docker Hub"){
        sh 'docker push  vsvdevuaco/vsvdev-docker-demo:vsvdev-docker-demo'
    }
    stage("SSH Into k8s Server") {
        def remote = [:]
        remote.name = 'k8smaster'
        remote.host = '192.168.50.4'
        remote.user = 'vagrant'
        remote.password = 'vagrant'
        remote.allowAnyHosts = true

        stage('Put k8s-spring-boot-deployment.yml onto k8smaster') {
            sshPut remote: remote, from: 'k8s-spring-deployment.yml', into: '.'
        }
        stage('Deploy spring boot') {
          sshCommand remote: remote, command: "kubectl apply -f k8s-spring-deployment.yml"
        }
      }

}