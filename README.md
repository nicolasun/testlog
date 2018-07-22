# testlog
validate the server log

The whole system is using spring boot. HsqlDB is persistent. 

I built a jar for test. gs-relational-data-access-0.1.0.jar
It can be directly execute by java. 

19.2 Running as a Packaged Application
If you use the Spring Boot Maven or Gradle plugins to create an executable jar, you can run your application using java -jar, as shown in the following example:

$ java -jar target/myapplication-0.0.1-SNAPSHOT.jar
It is also possible to run a packaged application with remote debugging support enabled. Doing so lets you attach a debugger to your packaged application, as shown in the following example:

$ java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n \
       -jar target/myapplication-0.0.1-SNAPSHOT.jar

There is a guide of running spring boot in spring boot reference.
https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html

When the spring boot is active, the input request will be send by a browser:
http://localhost:8080/logpath?path=log.txt

The log.txt can be found in log folder in test log repo. The easy way of testing the json log is to put log.txt and gs-relational-data-access-0.1.0.jar in a same folder. 

The "log.txt" can be change to any file path of the json log. If the log file is in somewhere of the disc.

The results will be displayed on the browser.

Sorry for the time shortly. There are many things which are needed to be implement.

1. Use repository for storing data in DB
2. Use Lombok for serilize the data and control thread safty.
3. add home page for sending request, not provding a link
4. add a ui model template page to display the results
5. add junit test case


