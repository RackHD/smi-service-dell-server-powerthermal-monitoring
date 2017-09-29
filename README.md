### smi-service-dell-powerthermal-monitoring

Copyright © 2017 Dell Inc. or its subsidiaries.  All Rights Reserved. 

### Dell Server Power and Thermal Monitoring Microservice

Power and Thermal monitoring for hardware resources in a datacenter environment is one of the major challenges for hardware administrator in converged and hyper converged environment. Administrators want to manage the power consumption by nodes or by rack.  
Setting power capping on an individual node or set of nodes will ensure the power consumption is under control and monitored. 
This Dell Server Power and Thermal Microservice will enable an administrator to monitor and manage power and thermal consumption by node or set of nodes within their racks.
The microservice is available at dockerhub as a docker image to download and run. 


### How to Install 

A docker container for this service is available at: https://hub.docker.com/r/rackhd/dell-powerthermal-monitoring/

-------------------------------------------------------------------
~~~
-sudo docker pull rackhd/dell-powerthermal-monitoring

-sudo docker run rackhd/dell-powerthermal-monitoring 
~~~
These two commands will download and run microservice on port 46019 within Docker.  The microservice will make available REST APIs for power and thermal monitoring on Dell servers
The microservice port can be mapped to a different port on hosting e.g. running a docker container on host with mapped ports. 

 -- sudo docker run -p 8080:46019 rackhd/dell-powerthermal-monitoring 


### API Usage 
-------------------------------------------------------------------
After the micro service is running, the service exposes REST APIs for consumption by your application or script.
These REST API accept requests and return responses in the JSON format. 
Swagger-UI based documentation has been added to the service and should be available for use when the service is up and running.
~~~
curl -X GET --header 'Accept: application/json' 'http://hostIP:8080/api/1.0/version'
~~~

~~~
curl -X POST --header 'Content-Type: application/json' --header 'Accept: application/json' -d '{"serverAddress":"", "userName": "" , "password": ""}' 'http://<<HostIP>>:8080/api/1.0/powerthermal'
~~~

 
### Setting Up A Development Environment 
-------------------------------------------------------------------
1- The developer can check out the source code from github into IDE of choice and start contributing to the project.

2- Download dependencies 
 
  https://github.com/rackhd/smi-lib-commons-model.git
  
  https://github.com/rackhd/smi-lib-commons-elm.git
  
  https://github.com/rackhd/smi-lib-wsman.git
  
  https://github.com/rackhd/smi-lib-wsman-client.git
  
  https://github.com/rackhd/smi-lib-adapter-server.git

  Alternatively, dependent jars can be downloaded from Maven Central
  
  3- Install and configure latest Gradle build tool
  
  4- Go to the project root directory and run
     gradle build

   This build script will download the dependencies from central maven and start building the microservice 
   
  5- Run the jar as SpringBoot application 
  
  
### Licensing
This docker microservice is available under the [Apache 2.0 License](http://www.apache.org/licenses/LICENSE-2.0.txt). 

Source code for this microservice is available in repositories at https://github.com/RackHD.  

The microservice makes use of dependent Jar libraries that may be covered by other licenses. In order to comply with the requirements of applicable licenses, the source for dependent libraries used by this microservice is available for download at:  https://bintray.com/rackhd/binary/download_file?file_path=smi-service-dell-server-powerthermal-monitoring-dependency-sources-devel.zip

Additionally the binary and source jars for all dependent libraries are available for download on Maven Central.

### Support
-------------------------------------------------------------------
Please file bugs and issues at the GitHub issues page. The code and documentation are released with no warranties or SLAs and are intended to be supported through a community driven process.

Slack Channel: codecommunity.slack.com


