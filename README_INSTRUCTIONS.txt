Read Me

NOTE:
1.Requirements JDK 1.8 installed on system and maven 3.8 installed
2.Instructions to build the app.(commands to be executed in the Project Directory)
3.The application uses in memory H2-Databse , no need to configure the database.

A.Building the Application
1.mvn clean install
2.mvn clean package


B.Running the Application 

1.mvn spring-boot:run (running the spring boot app)
2.java -jar target/tradestore-0.0.1-SNAPSHOT.jar (Executing the jar file)

//sample request and response 
1.http://localhost:8080/createtrade (POST) //this api is used to save the records
//below is request body 
{
"tradeId":"T1", 
"version":1,
"counterPartyId":"CP-1",  
"bookingId": "B1", 
 "maturityDate": "2022-04-15",
"expired":"N"
}

{
    "statusMessage": "Trade Record Saved Successfully",
    "statusCode": "200",
    "error": false
}

2.http://localhost:8080/trades  (GET) //this endpoint will retrieve records

sample response-
{
    "statusMessage": "Valid",
    "statusCode": "200",
    "data": [
        {
            "tradeId": "T2",
            "version": 0,
            "counterPartyId": "CP-1",
            "bookingId": "B1",
            "createdDate": "2022-04-14",
            "maturityDate": "2022-04-15",
            "expired": "N"
        }
    ],
    "error": false
}