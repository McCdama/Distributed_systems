The project My-Aktion implements a REST API with Spring Boot for
managing donation campaigns.
The repository contains a step-by-step implementation following
the respective exercise in the hands-on training booklet of lecture
**Distributed Systems**. You'll find the use cases and the domain model in the booklet, too.

Each part of the exercise is realized in a separate branch.
The branches were built on each other (technically speaking: they were rebased on their respective predecessor)
in the following order:

* start
* domain
* service-data
* logging
* rest-api
* validation
* advices
* extra-mile

You can checkout the branches to see the state of development for the corresponding part of the exercise
by `git checkout <branch_name>.` or by using the GitLab UI.

This `README.md` file may contain specific informations about the current branch.

* branch domain

Check the database scheme in the h2 database that was created by JPA.
Use the h2-console application for this reason.
http://localhost:8080/h2-console.

* branch service-data

 In this branch, the CommandLineRunner bean creates sample data using the service classes.
 After startup, connect to the h2 database using the h2-console web application: http://localhost:8080/h2-console.
 Check the data by executing some queries with the help of the console.

* branch logging

  Look at the different levels of log messages (debug, trace, info etc.).
  The level for the execution of the application is set in file application.properties. Change
  the setting to see effect on the log messages after restarting the application.

* branch rest-api

  Below you'll find how to use the API with curl

* branch validation

  Look at the constraints added to the domain classes using bean validation
  annotations (e.g. `@NotNull`). Try to pass invalid data to the service using the REST API below.
  Check the log file for a ContraintViolationException. The REST client should receive HTTP status 500.

* branch advices

  Check if the REST API returns HTTP status 404 (NOT FOUND) if you use a non-exiting Campaign Id.
  Further check, if the REST API returns a 400 (BAD REQUEST) if you try to pass invalid data.

* branch extra-mile

  The REST-API was updated. See curl commands below! Further, donations of a campaign are not loaded together with the campaign data.
  You need an extra request.

### curl commands

#### Get all campaigns
`curl localhost:8080/campaigns`

#### add new campaign
`curl -X POST -H 'Content-type:application/json' -d '{"name":"Trikots A-Jugend","donationMinimum":1.0,"targetAmount":1000.0,"account":{"iban":"DE4112312312312345","name":"Jogi Löw","nameOfBank":"KSK Freiburg"}}' localhost:8080/campaigns`

#### Get Campaign with id
`curl localhost:8080/campaigns/1`

#### Update Campaign
`curl -X PUT localhost:8080/campaigns/1 -H 'Content-type:application/json' -d '{"name":"Trikots B-Jugend","donationMinimum":1.0,"targetAmount":1000.0,"account":{"iban":"DE4112312312312345","name":"Jogi Löw","nameOfBank":"KSK Freiburg"}}'`

#### Delete Campaign with id
`curl -X DELETE localhost:8080/campaigns/1`

#### Add Donation to Campaign with id

`curl -X POST -H "Content-Type: application/json" -d '{"amount":20,"donorName":"Martin","receiptRequested":true,"status":"IN_PROCESS","account":{"name":"Martin","nameOfBank":"DKB Bank","iban":"DE41123456123456"}}' localhost:8080/campaigns/1/donations`

#### Get Donations of Campaign with id

`curl localhost:8080/campaigns/1/donations`

### Call Swagger-UI for more convenient testing the API
http://localhost:8080/swagger-ui.html
