This is learning lesson for developing basic springboot application and deploying in kubernets cluster with 2 microservices communicating with each other.
1st Step: product-api-service
First develop the application supporting basic HTTP API GET, POST, DELETE etc.
Once application is implemented and verified generate and test jar file locally.
Once this is done, generate docker image of this application and push to docker repository.
Once image is pushed to docker reprository, first step is done. This can be avoided once CI is integrated below.

2nd step : 
Now pull the k8s deployment file into you k8s cluster.
Open service.yaml file and update IP address same as your master IP address so that service external IP can be used to access it from outside.
Since we don't have any actual loadbalancer here.

Once that is done use 
kubectl apply -f . 
command to run deployment and service.
You can use 
kubectl get pod --> To get Running pod
kubectl get svc --> To get running service and check IP and Port of the service

Through this external IP and port we can access our app from the outside by using Postman or from webbrower.

CI - Integration
You can see the .github/workflow directory added into the repository which is having build.yml file which help us to build the project, create image and push to docker repository as soon as new pull request is created. 
Secret for your docker you can add into your settings->Security->secret.

Microservices communication : 
Two microservices product-api-service and customer_api_service will communicate with each other. This communication between them is done with REST interface by using spring Webclient. 

We can deploy both microservices by using deployment file present in the k8s_deplayment folder and then run the Post API to see that both services communicate with each other.


