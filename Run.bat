set projectLocation=C:\Users\himanshukumar03\eclipse-workspace\Restful
cd %projectLocation%
mvn clean apitest -DsuiteXmlFile=testng.xml
pause