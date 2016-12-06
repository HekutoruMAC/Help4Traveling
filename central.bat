if not "%JAVA_HOME%" == "" goto ready
for /d %%i in ("C:\Program Files\Java\jdk*") do setx JAVA_HOME "%%i" /M
:ready
%~d0
cd %~dp0
apache-ant-1.9.7\bin\ant -buildfile Help4TravelingCentral\build.xml
copy "Help4TravelingCentral\dist\Help4TravelingCentral.jar" "central.jar"
pause