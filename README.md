This is learning lesson for developing basic springboot application and deploying in kubernets cluster.
1st Step: 
First develop the application supporting basic HTTP API GET, POST, DELETE etc.
Once application is implemented and verified generate and test jar file locally.
Once this is done, generate docker image of this application and push to docker repository.
Once image is pushed to docker reprository, first step is done.

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

