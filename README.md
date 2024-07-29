**WeatherApp** 

This application helps to get the Weather Information for different locations based on lattitude and longitude.Based on the API call made to the  

sample url - https://api.weather.gov/gridpoints/MLB/33,70/forecast 

It will provide the out and the json response gets parsed and returned through Spring MVC. 

 
**Features Implemented : **

Spring MVC Controller handles the request and make calls to the https://api.weather.gov/gridpoints/MLB/?,?/forecast 

This API help to get the details based on latitude and longitude (currently handling only positive values for latitude and longitude). 


**BUILD the application as Maven Project **

clean install -U 


**DEPLOY the application to the Tomcat **

Run as java application using inbuilt tomcat server. 


Check the response by passing latitude and longitude values using below URL(latitude and longitude must be greater than 0)

http://localhost:8080/getWeather/dailyForecast?latitude=31.0&longitude=70.0 


**Swagger URL: **

http://localhost:8080/api-docs 

http://localhost:8080/swagger-ui/index.html 
