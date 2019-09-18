# Sogeti-Film-Land-Service
Build a Restful web application using spring  boot for Film land Users

# Pre-requisites
Spring Test Suite (STS 3.9.6), 
Java 8
Maven dependancies
Postman

# How to run
For a basic usage of STS, please clone the github project from repository and build the project . 
Provide JDK in class path so it can compile and spring boot required as well.
Once build success kindly run the main package(com.sogeti.api.filmland.FilmLandApplication.java).

As soon as application start up , Open a parallel service hosting Swagger in same port(http://localhost:8080/swagger-ui.html) to check the various service controller.

Recommended to open Postman to transact each request.(CRUD operation REST- spring based CRUD)

# Data Base  (H2) 
Here we are using the h2 java inline database and pre-loaded few records into tables during application run time

 USER_INFO   (Password will be stored on 128-bits (32 characters) "hash" format) 
 CATEGORY 
 SUBSCRIBE_CATEGORY
 

 # Scheduler 
 
 Used Spring spring scheduler to run and send payment notification if the start date of subsciber service is more than a month.
 
 
# Specific End points and arguments has been noted below:(Its for reference and all negative scenarios has been handled and binded in response body with HTTP error code)

1.localhost:8080/registerUser   

Json Request Body: 
{
	"username":"rahul.jha@gmail.com",
	"password":"password123"
}

Json Response Body:
{
    "status": ""User registration successful",
    "message": "rahul.jha@gmail.com register successfully"
}

2.localhost:8080/authenticate

Json Request Body:
{
	"username":"rakesh.gowda@gmail.com",
	"password":"password"
}

Json Response Body:
{
    "status": "Login Successfull",
    "message": "rakesh.gowda@gmail.com logged in successfully",
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyYWtlc2guZ293ZGFAZ21haWwuY29tIiwiZXhwIjoxNTY4Nzg1MjU4LCJpYXQiOjE1Njg3NjcyNTh9.E39F5SejtcIS1kl1UGG66Vkp7tKr0K4XYMRL-IytgN_Q_9zDvgZK7Dh2X-JfjXiwP832XDPfJaE3oywROyIu2g"
}

3.localhost:8080/categories?username=rakesh.gowda@gmail.com  (GET Request)

Headers: 
	Authorization : accessToken starting with reference keyword 'Bearer '

Response Body:
{
    "availableCategories": [
        {
            "name": "Dutch Films",
            "availableContent": 10,
            "price": 4.0
        },
        {
            "name": "Dutch Series",
            "availableContent": 20,
            "price": 6.0
        }
    ],
    "subscribedCategories": [
        {
            "name": "International Films",
            "remainingContent": 6,
            "startDate": "17-05-2019",
            "price": 8.0
        }
    ]
}

4.localhost:8080/subscribe  (POST)

Headers: 
	Authorization : accessToken starting with reference keyword 'Bearer '

Json Request Body:
{
"email":"rakesh.gowda@gmail.com",
"availableCategory":"Dutch Films"
}

Json Response Body:
{
    "status": "Subscription successful",
    "message": "rakesh.gowda@gmail.com subscribed to category Dutch Films"
}

5.localhost:8080/shareSubscription (POST)

Headers: 
	Authorization : accessToken starting with reference keyword 'Bearer '
Json Request Body:
{
"email":"rakesh.gowda@gmail.com",
"customer":"Rahul",
"subscribedCategory":"Dutch Films"
}

Json Response Body:

{
    "status": "Subscription successful",
    "message": "Dutch Films shared successfully with customer Rahul"
}