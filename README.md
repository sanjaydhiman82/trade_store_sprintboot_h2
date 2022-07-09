**Trade Store Application**

Problem Statement  
There is a scenario where thousands of trades are flowing into one store, assume any way of  
transmission of trades. We need to create a one trade store, which stores the trade in the following  
order

There are couples of validation, we need to provide in the above assignment

1.   During transmission if the lower version is being received by the store it will reject the trade and throw an exception. If the version is same it will override the existing record.
2.   Store should not allow the trade which has less maturity date then today date.
3.   Store should automatically update the expire flag if in a store the trade crosses the maturitydate.

**Technology** 

Spring Boot, H2
**SWAGGER**
http://localhost:8080/store/swagger-ui/index.html
** H2 **
http://localhost:8080/store/h2-console


**JUNIT**

![](https://33333.cdn.cke-cs.com/kSW7V9NHUXugvhoQeFaf/images/09af757b441b302c14135412dece62d46db64e1b6539ac78.png)

**OUT PUT**


