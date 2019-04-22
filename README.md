# KW Gradr

## Requirements
Java 8
Maven 3.5.3+

## How to run
In order to simplify the execution maven is the preferred execution method through the following command:

`mvn spring-boot:run`

After the application is started, the endpoint can be reached at

`http://localhost:8080/estimate?keyword=my+keyword`

Bear in mind that some REST clients escape the `+` sign into `%2B` meaning a literal plus sign.
CURL treats the signal correctly so the command

`curl http://localhost:8080/estimate?keyword=my+keyword`

should return the payload

`{"Keyword":"my keyword","score":0.0}`