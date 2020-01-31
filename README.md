# DDD with Spring [![Build Status](https://travis-ci.org/daggerok/ddd-spring-hexagonal-architecture.svg?branch=master)](https://travis-ci.org/daggerok/ddd-spring-hexagonal-architecture)
Hexagonal DDD application architecture

Status: IN PROGRESS

```

    +----------+
    | Election |
    +----------+
       | 1
       |
       V *
      +-----------+
      | Candidate |
      +-----------+
         | 1
         |
         V *
        +-------+
        | voter |
        +-------+

```

## build run and test

1. build
   ```bash
   ./mvnw spring-boot:run
   ```
1. And use [api.http file](api.http) from IntelliJ IDEA.

## resources

* [H2 with PostgreSQL mode and random UUID generation by using `RANDOM_UUID()` function](src/main/resources/db/migration/V1__schema.sql#L2)
* [Nice video on  one-to-* mapping]()
* [Nice video on  many-to-* mapping](https://www.youtube.com/watch?v=5rqlqon8xko)
* [Helpful link 1 (Entity)](https://github.com/daggerok/spring-data-jdbc-examples/blob/dcd2fde95222f97a6accbd8db929677c05496688/many-to-many/src/main/java/com/example/springdatajdbconetomany/SpringDataJdbcManyToManyApplication.java)
* [Helpful link 2 (SQL)](https://github.com/daggerok/spring-data-jdbc-examples/blob/dcd2fde95222f97a6accbd8db929677c05496688/many-to-many/src/main/resources/db/migration/ddl.sql)
