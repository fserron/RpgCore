@echo on

cd D:\workspaces\RpgCore\RpgCore
call mvn clean install -e -DskipTests
cd target
xcopy RpgCore-1.0.war D:\DevTools\apache-tomcat-7.0.47\webapps /Y
cd D:\DevTools\apache-tomcat-7.0.47\webapps
rd /s /q RpgCore-1.0
cd..
CHOICE