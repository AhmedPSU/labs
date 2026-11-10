# Lab 16 - Spring Security notes

## What changed from the starting code
- `pom.xml`: added `spring-boot-starter-thymeleaf` (missing in the starter, needed to render the HTML pages).
  Devtools, Security and Security Test were already present.
- `application.properties`: database settings read from environment variables.
- New package `psu.se411.lab16.security` with `WebSecurityConfig`.

## Database environment variables
| Variable      | Example (Aiven)                              | Default if unset |
|---------------|----------------------------------------------|------------------|
| DB_HOST       | mysql-xxxxxxxx-project.x.aivencloud.com      | localhost        |
| DB_PORT       | 2xxxx                                        | 3306             |
| DB_NAME       | se411db                                      | se411db          |
| DB_USER       | avnadmin                                     | root             |
| DB_PASSWORD   | (from Aiven)                                 | (empty)          |
| DB_SSL_MODE   | REQUIRED                                     | PREFERRED        |

In Eclipse: Run > Run Configurations > (your app) > Environment tab > Add.

## Users
| Username | Password | Role  | Can open                         |
|----------|----------|-------|----------------------------------|
| usera    | pwd123   | USER  | /, /dashboard, /api/v1/courses   |
| admin    | admin123 | ADMIN | /, /admindashboard, /api/v1/courses |

## Access rules
- `/` -> everyone
- `/dashboard/**` -> role USER
- `/admindashboard/**` -> role ADMIN
- anything else (CRUD API) -> any logged-in user
- `/login`, `/logout` -> everyone

## Testing the API in Postman / Thunder Client (form login + CSRF)
1. `GET http://localhost:8080/login`
   Find in the HTML: `<input name="_csrf" type="hidden" value="...">` and copy the value.
2. `POST http://localhost:8080/login`
   Body: `x-www-form-urlencoded`
   - username = usera
   - password = pwd123
   - _csrf = (value from step 1)
   Postman keeps the `JSESSIONID` cookie automatically.
3. `GET http://localhost:8080/api/v1/courses` -> 200 with the course list.
4. POST / PUT / DELETE also need the CSRF token: send `GET /login` again (now logged in) or
   reuse the token, and add header `X-CSRF-TOKEN: <token>`.
   - POST `/api/v1/courses`  body (JSON): `{"code":"CS201","name":"Data Structures","description":"Lists, trees, graphs","credits":3}`
   - PUT `/api/v1/courses/1` body (JSON): `{"code":"CS101","name":"Programming 1","description":"Updated description","credits":4}`
   - DELETE `/api/v1/courses/2`

## Browser test
- `http://localhost:8080/` -> public welcome page
- "Go to dashboard" -> login as usera -> Hello usera!
- Sign out, "Go to admin dashboard" -> login as admin -> Hello admin!
- usera opening /admindashboard -> 403 Forbidden (and admin on /dashboard -> 403)
