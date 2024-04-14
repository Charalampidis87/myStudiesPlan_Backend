# myStudiesPlan_Backend
**myStudiesPlan is an Web application, designed for personalized student support in designing and following their curriculum. The application implements functions such as course selection while respecting curriculum constraints, major and specialization selection scenarios, exams, grades and GPA.**
>myStudiesPlan operates at [mystudiesplan.di.uoa.gr](https://mystudiesplan.di.uoa.gr) for [Department of Informatics and Telecommunications](https://www.di.uoa.gr) of [National and Kapodistrian University of Athens](https://www.uoa.gr).
## Pre-requirements
To **compile** this project, you will need:
- [JDK](https://www.oracle.com/java/technologies/downloads/) (`JDK 17` or later)
- [Apache Maven](https://maven.apache.org/download.cgi)

To **run** this project, you will need:
- [MySQL](https://www.mysql.com/downloads/)
- [Apache Tomcat Web server](https://tomcat.apache.org/download-90.cgi) (`Tomcat 9` or other web server engine)

**Optional**
- [Certbot](https://certbot.eff.org/instructions) (Automate Let's Encrypt TLS Certificate issuance, installation and renewal)
- [myStudiesPlan_CAS-Server](https://github.com/Charalampidis87/myStudiesPlan_CAS-Server) (Central Authorization Service `CAS` for Single Sign On `SSO` implementation)

## Database configuration
Before compiling, configure myStudiesPlan to match your system settings.
In file `/mystudiesplan-service/src/main/resources/application.properties` set the following fields for connecting to your *MySQL* Database.
```
spring.datasource.url=jdbc:mysql://localhost:3306/mystudiesplandb?useUnicode=true&
useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezo
ne=UTC
spring.datasource.username=
spring.datasource.password=
```
## Compiling and starting the servers
Run `mvn clean package` to produce `/mystudiesplan-service/target/service.war`.
Feed `service.war` to web server engine to run myStudiesPlan web app backend service.
___
> You can find full documentation [here](https://github.com/Charalampidis87/myStudiesPlan_Backend/blob/main/Assets/myStudiesPlan_Thesis.pdf) and [here](https://pergamos.lib.uoa.gr/uoa/dl/object/3294663) (*available only in **Greek***)