@echo off
:: If you already have a valid JAVA_HOME environment variable set, feel free to comment the below two lines
set JAVA_HOME=D:\openjdk-21_windows-x64_bin\jdk-21
set path=%JAVA_HOME%\bin;%path%
set path=%cd%\allure-2.30.0\bin;%path%
call allure generate allure-results --single-file --clean
::call allure open allure-report
pause
