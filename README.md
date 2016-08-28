# Log everything for a specific web request in Spring Boot

Often production servers have their log level set to INFO or higher for
performance reasons. Sometimes however, when you have a problem on a
production server, you may want to see all logs associated with a
specific web request. This will allow you to run a request and see the
output from that single request. This demo shows how this can be
achieved in Spring Boot using the default logging implementation of LogBack.

## Running the application

To run the application, run:

    ./gradlew clean bootRun
    
If you open the browser to: `http://localhost:8080/greetings/duke` you
will see that only INFO and above statements are logged. If you change
the url to: `http://localhost:8080/greetings/duke?trace=on`

## Applicability 

This example uses this technique for web requests but the same technique
can easily be applied to incoming JMS messages, scheduled jobs etc.

Of course, the same technique can be adapted to any other web
framework.