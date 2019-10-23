# Spring Boot, MySQL, Spring Security, JWT, JPA, Rest API

Build Restful CRUD API for a blog using Spring Boot, Mysql, JPA and Hibernate.

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/vardanhov/egsweb.git
```

**2. Create Mysql database**
```bash
create database egs_web
```
- run `src/main/resources/`

**3. Change mysql username and password as per your installation**

+ open `src/main/resources/application.properties`
+ change `spring.datasource.username` and `spring.datasource.password` as per your mysql installation

**4. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>

## Explore Rest APIs

The app defines following CRUD APIs.

### Admin

| Method | Url | Decription | Sample Valid Request Body | 
| ------ | --- | ---------- | --------------------------- |
| POST   | /activateUser | Activate user |  |
| POST   | /createUser | Create user |  |

### User

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| POST    | /login | Log in  user profile | |
| POST    | /register | Registration | |

### Post

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /posts/getAll | Get all posts | |
| GET    | /posts/category/{id} | Get posts by category id | |
| POST    | /posts/addPost | Add Post | |
| PUT    | /posts/update/{id} | Update post | |
| GET    | /posts/getById/{id} | Get post by id | |
| DELETE    | /posts/delete/{id} | Delete post by id | |