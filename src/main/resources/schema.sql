CREATE TABLE IF NOT EXIST `tbl_user`
    `id` BINARY(16) NOT NULL,
    `account_id` VARCHAR(20) NOT NULL,
    `password` CHAR(60) NOT NULL,
    `name` VARCHAR(10) NOT NULL,
    PRIMARY KEY (`id`)
    CONSTRAINT `UNIQUE_account_id` UNIQUE(`account_id`)
);

CREATE TABLE IF NOT EXIST `tbl_feed`
    `id` BINARY(16) NOT NULL,
    `title` VARCHAR(50) NOT NULL,
    `content` VARCHAR(1000) NOT NULL,
    PRIMARY KEY (`id`),
    CONSTRAINT `FK_tbl_user_TO_tbl_feed_1` FOREIGN KEY (`user_id`) REFERENCES `tbl_user`(`id`)
);
