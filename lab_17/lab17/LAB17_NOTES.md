# Lab 17 - Spring Security: Basic Authentication (MySQL)

## What changed from the starting code
- `pom.xml`: Java version 23 -> 21.
- `application.properties`: database settings read from environment variables.
- `schema.sql`: new `users` table. `data.sql`: two users (BCrypt-hashed passwords).
- `model/User.java`: record User(id, username, password), mapped to table `users`.
- `repository/UserRepository.java`: `findByUsername`.
- `security/UserPrincipal.java`: adapter from User to Spring's UserDetails.
- `security/MyUserDetailsService.java`: loads users from the database.
- `security/SecurityConfig.java`: CSRF off, all endpoints authenticated, HTTP Basic, stateless sessions, BCrypt encoder.

## Users
| Username | Password |
|----------|----------|
| usera    | pwd123   |
| userb    | pwd456   |

To add a user, store a BCrypt hash of the password (e.g. from `new BCryptPasswordEncoder().encode("...")`).

## Database environment variables
| Variable    | Example (Aiven)                          | Default if unset |
|-------------|------------------------------------------|------------------|
| DB_HOST     | mysql-xxxxxxxx-project.x.aivencloud.com  | localhost        |
| DB_PORT     | 2xxxx                                    | 3306             |
| DB_NAME     | se411db                                  | se411db          |
| DB_USER     | avnadmin                                 | root             |
| DB_PASSWORD | (from Aiven)                             | (empty)          |
| DB_SSL_MODE | REQUIRED                                 | PREFERRED        |

In Eclipse: Run > Run Configurations > (your app) > Environment tab > Add.

## Testing in Thunder Client / Postman
Auth tab -> Basic -> username `usera`, password `pwd123`
(same as header `Authorization: Basic dXNlcmE6cHdkMTIz`).

| Method | URL                                   | Body (JSON) | Expected |
|--------|---------------------------------------|-------------|----------|
| GET    | http://localhost:8080/api/v1/products |             | 200 + list |
| GET    | http://localhost:8080/api/v1/products/1 |           | 200 |
| POST   | http://localhost:8080/api/v1/products | `{"name":"Monitor","description":"A 27 inch monitor","price":799.99}` | 201 + Location header |
| PUT    | http://localhost:8080/api/v1/products/1 | `{"name":"3d printer","description":"Updated","price":1700.00}` | 204 |
| DELETE | http://localhost:8080/api/v1/products/2 |           | 204 |

No credentials or a wrong password -> 401 Unauthorized.
