set projectLocation=C:\Users\himanshukumar03\eclipse-workspace\Restful
cd %projectLocation%
mvn clean test -DsuiteXmlFile=testng.xml
pause