# DDD with Spring [![Build Status](https://travis-ci.org/daggerok/ddd-spring-hexagonal-architecture.svg?branch=master)](https://travis-ci.org/daggerok/ddd-spring-hexagonal-architecture)
Hexagonal DDD application architecture

```
    +----------+
    | Election |                Hexagonal architecture
    +----------+
       | 1             +------------------------------------------+
       |               | Infrastructure (framework related) layer |
       V 0..*          |  +------------------------------------+  |
      +-----------+    |  |    Application (business logic)    |  |
      | Candidate |    |  |           service layer            |  |
      +-----------+    |  |  +------------------------------+  |  |
         | 1           |  |  |    Domain (Election) layer   |  |  |
         |             |  |  +------------------------------+  |  |
         V 0..*        |  +------------------------------------+  |
        +-------+      +------------------------------------------+
        | Voter |
        +-------+
```

## build run and test

1. build
   ```bash
   ./mvnw spring-boot:run -f feature
   # ./mvnw spring-boot:run -f layer
   ```
1. And use [api.http file](api.http) from IntelliJ IDEA.

## resources

* [H2 with PostgreSQL mode and random UUID generation by using `RANDOM_UUID()` function](feature/src/main/resources/db/migration/V1__schema.sql#L2)
* [Helpful link 1 (Entity)](https://github.com/daggerok/spring-data-jdbc-examples/blob/dcd2fde95222f97a6accbd8db929677c05496688/many-to-many/src/main/java/com/example/springdatajdbconetomany/SpringDataJdbcManyToManyApplication.java)
* [Helpful link 2 (SQL)](https://github.com/daggerok/spring-data-jdbc-examples/blob/dcd2fde95222f97a6accbd8db929677c05496688/many-to-many/src/main/resources/db/migration/ddl.sql)
* [Nice video on  many-to-* mapping](https://www.youtube.com/watch?v=5rqlqon8xko)
* [Nice video on  one-to-* mapping](https://www.youtube.com/watch?v=ccxBXDAPdmo)
