# Lab 19 - Basic authentication with users in the database

## What changed from the Lab 18 starting code
- `SecurityConfig.java`: removed the in-memory `UserDetailsService` bean (InMemoryUserDetailsManager).
- New `security/MyUserDetailsService.java`: loads users from MySQL through `UserRepository`.
- New `repository/UserRepository.java` with `findByUsername`.
- `model/User.java`: mapped to table `users` (`user` is a MySQL keyword).
- `schema.sql`: new `users` table. `data.sql`: one user with a BCrypt-hashed password.
- `application.properties`: database settings read from environment variables.
- `pom.xml`: Java 23 -> 21, artifactId lab18 -> lab19 (avoids a name clash with Lab 18 in Eclipse).

## User
| Username | Password |
|----------|----------|
| usera    | pwd123   |

## Database environment variables
| Variable    | Example (Aiven)                          | Default if unset |
|-------------|------------------------------------------|------------------|
| DB_HOST     | mysql-xxxxxxxx-project.x.aivencloud.com  | localhost        |
| DB_PORT     | 2xxxx                                    | 3306             |
| DB_NAME     | se411db                                  | se411db          |
| DB_USER     | avnadmin                                 | root             |
| DB_PASSWORD | (from Aiven)                             | (empty)          |
| DB_SSL_MODE | REQUIRED                                 | PREFERRED        |

## Testing in Thunder Client / Postman
Auth -> Basic -> `usera` / `pwd123` (header `Authorization: Basic dXNlcmE6cHdkMTIz`).

| Method | URL                                     | Body (JSON) | Expected |
|--------|-----------------------------------------|-------------|----------|
| GET    | http://localhost:8080/api/v1/products   |             | 200 + list |
| GET    | http://localhost:8080/api/v1/products/1 |             | 200 |
| POST   | http://localhost:8080/api/v1/products   | `{"name":"Monitor","description":"A 27 inch monitor","price":799.99}` | 201 |
| PUT    | http://localhost:8080/api/v1/products/1 | `{"name":"3d printer","description":"Updated","price":1700.00}` | 204 |
| DELETE | http://localhost:8080/api/v1/products/2 |             | 204 |

No credentials / wrong password -> 401 Unauthorized.
