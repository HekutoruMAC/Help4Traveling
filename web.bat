if not "%JAVA_HOME%" == "" goto ready
for /d %%i in ("C:\Program Files\Java\jdk*") do setx JAVA_HOME "%%i" /M
:ready
%~d0
cd %~dp0
apache-ant-1.9.7\bin\ant -buildfile Help4TravelingWeb\build.xml
copy "Help4TravelingWeb\dist\Help4TravelingWeb.war" web.war
pause