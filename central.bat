if not "%JAVA_HOME%" == "" goto ready
for /d %%i in ("C:\Program Files\Java\jdk*") do setx JAVA_HOME "%%i" /M
:ready
apache-ant-1.9.7\bin\ant -buildfile Help4TravelingCentral\build.xml