# Hướng dẫn sử dụng framework FMO (Backend)

- [Tổng quan](#tổng-quan)
- [Phạm vi hỗ trợ](#phạm-vi-hỗ-trợ)
- [Setup môi trường](#setup-môi-trường)
- [Chạy ứng dụng](#chạy-ứng-dụng)
- [Config Postman để gửi request](#config-postman-để-gửi-request)
- [Lưu ý](#lưu-ý)
- [Database (Postgres)](#database-postgres)
- [Kinh nghiệm](#kinh-nghiệm)
  - [Hướng dẫn tích hợp Lombok](#hướng-dẫn-tích-hợp-lombok)
  - [Hướng dẫn tích hợp Swagger](#hướng-dẫn-tích-hợp-swagger)
  - [Hướng dẫn tích hợp Log4j2](#hướng-dẫn-tích-hợp-log4j2)
  - [Một số lỗi thường gặp](#một-số-lỗi-thường-gặp)

## Tổng quan
- FMO là framework hỗ trợ cho việc phát triển web, gồm 2 phần:
  - Backend (chính là repository này), được thiết kế theo dạng [RESTful](https://en.wikipedia.org/wiki/Representational_state_transfer)
  - Frontend ở [repository này](http://10.240.203.2:8180/hungtv77/front-end-framework-template), được thiết kế theo dạng Dashboard (Cấu trúc giao diện tương tự GNOC2)
- FMO Backend sử dụng Spring Boot theo kiến trúc monolithic và chạy trên Java 11

## Phạm vi hỗ trợ
1. Cung cấp CRUD API
- Xem: Hỗ trợ phân trang, sắp xếp, tìm kiếm nâng cao khi lấy data trong 1 table. Nếu từ 2 table trở lên, bạn phải viết native query (có sample với Postgres)
- Thêm: Có hỗ trợ
- Sửa: Có hỗ trợ
- Xóa: Có hỗ trợ
2. Ghi log ra file
3. Xử lý ngoại lệ cấp độ Controller tự động
4. Tạo API Document tự động
5. Xác thực và phân quyền (Đang phát triển)
6. Export dữ liệu sang exel (Đang phát triển)

## Setup môi trường
1. JDK: Java 11
2. IDE: IntelliJ IDEA
3. Maven: 3.6.0
4. Testing tool: Postman
5. Dependencies cho maven: Giải nén file repository.rar ([tải tại đây](https://datasecurity.viettel.vn/s/uy2QPKllEIO4cGq/repository-rar)), sao chép tất cả các folder trong folder repository vừa giải nén tới đường dẫn repository trong folder maven của bạn (Thường là `C:\Users\{username}\.m2\repository`)

## Chạy ứng dụng
1. Click "Run DemoApplication" or Shift + F10

## Config Postman để gửi request
1. Import file json ([tải tại đây](./outside-resources/postman.json)) vào postman (Hướng dẫn import [tại đây](https://viblo.asia/p/cach-export-va-import-mot-hoac-nhieu-request-su-dung-postman-bWrZneaOKxw))
2. Sửa biến server thành `localhost:8080` hoặc `10.240.202.123:8080`
<div align="center">
  <img src="./outside-resources/image/Postman_ChangeVariable_1.png" alt="Change Variable Step 1"/>
</div>
<div align="center">
  <img src="./outside-resources/image/Postman_ChangeVariable_2.png" alt="Change Variable Step 2"/>
</div>

## Lưu ý
1. Folder [outside-resources](./outside-resources) là folder chứa các tài nguyên bên ngoài (không phải là thành phần của project)

## Database (Postgres)
```sql
create table book
(
    book_id        serial not null
        constraint book_pkey
            primary key,
    author         varchar,
    publisher      varchar,
    published_date timestamp default now(),
    title          varchar,
    total_pages    integer,
    rating         integer,
    isbn           varchar,
    best_seller    boolean
);

create table account
(
    account_id serial not null
        constraint account_pkey
            primary key,
    email      varchar,
    password   varchar
);

create table question
(
    question_id serial not null
        constraint question_pkey
            primary key,
    account_id  integer
        constraint question_account_foreign_key
            references account,
    title       varchar,
    description varchar
);
```

## Kinh nghiệm

### Hướng dẫn tích hợp Lombok
1. Cài plugin Lombok cho Intellij: https://projectlombok.org/setup/intellij
2. Vào `> Settings > Build, Execution, Deployment > Compiler > Annotation Processors`, chọn `Enable Annotation Processing`
<div align="center">
  <img src="./outside-resources/image/Lombok.png" alt="Lombok"/>
</div>

3. Thêm dependency vào `pom.xml`:

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
</dependency>
```

### Hướng dẫn tích hợp Swagger
1. Thêm dependency sau vào `pom.xml`:
```xml
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-ui</artifactId>
</dependency>
<dependency>
    <groupId>org.springdoc</groupId>
    <artifactId>springdoc-openapi-data-rest</artifactId>
</dependency>
```
2. URI mặc định:
- api-docs: /api-docs
- openapi file: /api-docs.yaml
- swagger-ui: /swagger-ui.html

Nếu bạn muốn thay đổi, hãy cấu hình ở file 'application.yml':
```yaml
springdoc:
  api-docs:
    path: /api-docs
  swagger-ui:
    path: /swagger-ui
``` 

3. Lưu ý:
- springdoc-openapi-ui đã tích hợp SwaggerUI
- springdoc-openapi-ui fix được lỗi hiển thị Timestamp của springfox-boot-starter
- springdoc-openapi-data-rest dùng để hỗ trợ hiển thị Pageable
- Thư viện không support định nghĩa cho `org.springframework.data.jpa.domain.Specification` nên bạn có thể **sử dụng file OpenAPI tự định nghĩa** ở `/src/main/resources/static` và cấu hình ở `application.yml`:
```yaml
springdoc:
  swagger-ui:
    uri: /custom-open-api.yaml
```
### Hướng dẫn tích hợp Log4j2
1. Thêm `exclusions` vào `spring-boot-starter-web` để xóa Logback (Logging framework mặc định của Spring Boot, nếu không xóa sẽ gây conflict với Log4j2):
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
    <exclusions>
        <exclusion>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```
2. Thêm dependency:
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
</dependency>
```
3. Thêm file `log4j2.xml` vào `\resources`:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
    <Appenders>
        <Console name="Console" target="SYSTEM_OUT" follow="true">
            <PatternLayout
                    pattern="%clr{%d{yyyy-MM-dd HH:mm:ss.SSS}}{faint} %clr{%5p} %clr{${sys:PID}}{magenta} %clr{---}{faint} %clr{[%15.15t]}{faint} %clr{%-40.40c{1.}}{cyan} %clr{:}{faint} %m%n%xwEx"/>
        </Console>
        <File name="File" fileName="application.log">
            <PatternLayout
                    pattern="%d{yyyy-MM-dd HH:mm:ss.SSS} %5p ${sys:PID} --- [%15.15t] %-40.40c{1.} : %m%n%xwEx"/>
        </File>
    </Appenders>
    <Loggers>
        <Root level="trace">
            <AppenderRef ref="Console" level="info"/>
            <AppenderRef ref="File"/>
        </Root>
    </Loggers>
</Configuration>
```
File trên sẽ giúp bạn ghi log ra console ở level info và ghi log ra file application.log (cùng cấp với file pom.xml) ở level trace
4. Cách sử dụng trong code (kết hợp với Lombok)
```java
@RestController
@Log4j2
public class LombokLoggingController {

    @RequestMapping("/lombok")
    public String index() {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");
 
        return "Howdy! Check out the Logs to see the output...";
    }
}   
```
5. Một số link hữu dụng
- Các quy ước để tạo layout: https://logging.apache.org/log4j/2.x/manual/layouts.html
- Các quy ước về level: https://www.tutorialspoint.com/log4j/log4j_logging_levels.htm, https://logging.apache.org/log4j/log4j-2.3/manual/customloglevels.html
- Tại sao nên dùng Log4j2? Câu trả lời từ [Phil Webb - Đồng sáng lập Spring Boot](https://spring.io/team/philwebb) : https://github.com/spring-projects/spring-boot/issues/16864#issuecomment-492570488

### Một số lỗi thường gặp
#### Unable to find valid certification path to requested target windows
1. Open Chrome => click on site icon left to address in address bar, select "Certificate" -> "Details" -> "Export" and save in format "Der-encoded binary, single certificate".
2. Determine location of cacerts files, eg. C:\Program Files (x86)\Java\jre1.6.0_22\lib\security\cacerts. 
3. Next import the example.cer file into cacerts in command line (may need administrator command prompt):
`keytool -import -alias example -keystore  "C:\Program Files (x86)\Java\jre1.6.0_22\lib\security\cacerts" -file example.cer`. <br/>
You will be asked for password which default is `changeit`
4. Restart your JVM/PC.<br/>
Source: https://stackoverflow.com/questions/21076179/pkix-path-building-failed-and-unable-to-find-valid-certification-path-to-requ
