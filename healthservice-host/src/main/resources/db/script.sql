-- Drop the old tables
DROP TABLE IF EXISTS title;

-- Create new tables
CREATE TABLE title (
    id                  BIGINT                          AUTO_INCREMENT PRIMARY KEY,
    description         String                          NOT NULL
)

ENGINE = InnoDB
CHARSET = utf8mb4;