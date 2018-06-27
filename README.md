# Spring Boot Education System
[![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
[![Build Status](https://travis-ci.com/Peng-YM/SpringBootEducationSystem.svg?token=zkm2xxngEhnJD2JPqh6t&branch=master)](https://travis-ci.com/Peng-YM/SpringBootEducationSystem)
[![CodeFactor](https://www.codefactor.io/repository/github/peng-ym/springbooteducationsystem/badge)](https://www.codefactor.io/repository/github/peng-ym/springbooteducationsystem)
![](https://tokei.rs/b1/github/Peng-YM/SpringBootEducationSystem)
![](https://tokei.rs/b1/github/Peng-YM/SpringBootEducationSystem?category=files)

Education System based on SpringBoot and REST

Table of Contents
=================

   * [Spring Boot Education System](#spring-boot-education-system)
      * [1. Development](#1-development)
         * [1.1 Prerequisites](#11-prerequisites)
         * [1.2 Configuration](#12-configuration)
      * [2. Techneques](#2-techneques)
      * [3. API documentation](#3-api-documentation)
         * [3.1 Summary](#31-summary)
         * [3.2 Example Usages](#32-example-usages)
         * [3.3 API Security](#33-api-security)
      * [LICENSE](#license)
      
## 1. Development

### 1.1 Prerequisites

1. Maven
2. JDK-8(not compatible with JDK-9)
### 1.2 Configuration

1. Create Database

    ```shell
      $ mysql -uroot -p 12345
      $ create database EAS
    ```

2. Edit `application.yml` to set your database name, username and password.

   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/EAS
       username: root
       password: 123456
   ```
3. Start server

   ```
    $ mvn spring-boot:run
   ```

    Then open the address http://localhost:8080 to open the HAL browser.

    ![332DE30D-8F1F-428B-B736-5BCBE38669E6](https://ws3.sinaimg.cn/large/006tNc79gy1fscyjj5wclj31g10teahf.jpg)

4. Or you can use the command below to package the application into jar

   ```shell
   $ mvn package
   $ java -jar *.jar # run the application
   ```

## 2. Techneques Stacks

- [x] SpringBoot
- [x] JPA Hibernate
- [x] Spring Data
- [x] Spring Security

## 3. API documentation

### 3.1 Summary

This project use well formatted HAL JSON as response, you can quickly understand them with HAL browser.

There are 5 root resources in current api version, all of this resource support below actions:

- [x] **GET**: get REST resouce.
  - [x] **Paging**: request a page of resource.
  - [x] **Sorting**: sort the resource with some of it's properties. 

- [x] **POST**: create a new REST resource.
- [x] **PATCH**: update an existent REST resource <u>with some resouce properties</u>
- [x] **DELETE**: delete an existent REST resource.

1. **exams{?page,size,sort}**

   This api exposed all the exams.

2. **marks{?page,size,sort}**

   This api exposed all the marks.

3. **users{?page,size,sort}**

   This api exposed all the users.

4. **resources{?page,size,sort}**

   This api exposed all the course resource.

5. **courses{?page,size,sort}**

   This api exposed all the course.

### 3.2 Example Usages

Take `users` resource as an example:

1. **GET all the users**

   ```http
   GET http://localhost:8080/users
   ```

2. **GET user by id**

   ```http
   GET http://localhost:8080/users/58
   ```

3. **GET with sorting**

   Ascending with `email`

   ```http
   GET http://localhost:8080/users?sort=email
   ```

   Decending with `email`:

   ```http
   GET http://localhost:8080/users?sort=-email
   ```

4. **GET with paging**

   `page`specify the page number. `size` specify the number of resource in one page.

   ```http
   GET http://localhost:8080/users?page=0&size=10
   ```

5. **GET with search parameter**

   <u>Not all the resource support search parameters:</u>

   ```http
   GET http://localhost:8080/users/search/findByEmail?email=pengym@qq.com
   ```
   To find out all avaliable search parameter under `users` resource:

   ```http
   GET http://localhost:8080/users/search/
   ```

   The response body should be:

   ```json
   {
     "_links": {
       "findByEmail": {
         "href": "http://69.171.71.251:8080/users/search/findByEmail{?email}",
         "templated": true
       },
       "self": {
         "href": "http://69.171.71.251:8080/users/search/"
       }
     }
   }
   ```

6. **GET subresource**

   get user with id 58's learning courses:

   ```http
   GET http://localhost:8080/users/58/learning
   ```

7. **Create user**

   ```http
   POST http://localhost:8080/api/users
   ```

   Body

   ```json
   {
       "email": "test@qq.com",
       "password": "$2a$10$x0Qw0EdwJTNnY9DF8xNYfebahJ./8vEOkd0b8sacHJhSVcrnK50t.",
       "firstName": "test",
       "lastName": "test",
       "phone": "12345678901",
       "roles": [
           {
               "name": "ROLE_TEACHER"
           },
           {
               "name": "ROLE_USER"
           }
       ]
   }
   ```

8. **Update user**

   Update user with id 58

   ```http
   PATCH http://localhost:8080/api/users/58
   ```

   Body

   ```json
   {
       "phone": "10086"
   }
   ```

9. **Delete a user**

   ```http
   DELETE http://localhost:8080/users/58
   ```

10. **==Update Subresource==**

   Add a course with id 59 to user with id 58's learning courses:

   ```shell
   $ curl -i -X PUT -d "http://localhost:8080/courses/59"
     -H "Content-Type:text/uri-list" http://localhost:8080/users/58/learning
   ```

### 3.3 API Security

There are 3 levels of users in this application: **Admin** > **Teacher** > **User**

Admin was granted authority to access all api, while Teacher and User only have limited access. Here's a table to demonstrate the auchorities:

"**√**" denotes full authorities: create, update, delete, query all or single REST resource.

|   Resource    | Admin |          Teacher          |    User     |
| :-----------: | :---: | :-----------------------: | :---------: |
|   **users**   | **√** | Get/Query single/all User | Get himself |
|   **exams**   | **√** |           **√**           |    **√**    |
| **resources** | **√** |           **√**           |    **√**    |
|  **courses**  | **√** |           **√**           |    **√**    |
|   **marks**   | **√** |           **√**           |    Get himself    |

## LICENSE

GPL V3 [![License: GPL v3](https://img.shields.io/badge/License-GPL%20v3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
