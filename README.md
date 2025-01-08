# Brief 15

## Objectif

Dans ce projet nous avons créé une application web qui permet de gérer une bibliothèque, les livres, les emprunteurs et les emprunts.

## Exigence technique


## Database structure
![img.png](img.png)

```mermaid
erDiagram
    STUDENT {
        BIGINT id PK "Primary Key"
        VARCHAR firstName
        VARCHAR lastName
    }

    BOOK {
        BIGINT id PK "Primary Key"
        VARCHAR title
        BOOLEAN isAvailable
        BIGINT student_id FK "Foreign Key to Student"
        TIMESTAMP created_at "Timestamp of creation"
    }

    GENRES {
        VARCHAR name PK "Primary Key"
    }

    BOOK_GENRES {
        BIGINT book_id FK "Foreign Key to Book"
        VARCHAR genre_name FK "Foreign Key to Genres"
    }

    STUDENT ||--o{ BOOK : borrows
    BOOK ||--o{ BOOK_GENRES : has
    GENRES ||--o{ BOOK_GENRES : includes
```