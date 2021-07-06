CREATE DATABASE `shopping-mall`;


CREATE TABLE `shopping-mall`.`members`
(
    `index`          INT UNSIGNED     NOT NULL AUTO_INCREMENT,
    `email`          NVARCHAR(50)     NOT NULL,
    `password`       NVARCHAR(128)    NOT NULL,
    `nickname`       NVARCHAR(10)     NOT NULL,
    `name`           NVARCHAR(10)     NOT NULL,
    `address_post`   NVARCHAR(5)      NOT NULL,
    `address`        NVARCHAR(100)    NOT NULL,
    `address_detail` NVARCHAR(100)    NOT NULL,
    `birth_year`     NVARCHAR(4)      NOT NULL,
    `birth_month`    NVARCHAR(2)      NOT NULL,
    `birth_day`      NVARCHAR(2)      NOT NULL,
    `level`          TINYINT UNSIGNED NOT NULL DEFAULT 9,
    `contact_first`  NVARCHAR(3)      NOT NULL,
    `contact_second` NVARCHAR(4)      NOT NULL,
    `contact_third`  NVARCHAR(4)      NOT NULL,
    `registered_at`  DATETIME         NOT NULL DEFAULT NOW(),
    `admin_flag`     BOOLEAN          NOT NULL DEFAULT FALSE,
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT UNIQUE (`email`),
    CONSTRAINT UNIQUE (`nickname`),
    CONSTRAINT UNIQUE (`contact_first`, `contact_second`, `contact_third`)
);

CREATE TABLE `shopping-mall`.`product_categories`
(
    `index` INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `code`  NVARCHAR(20)  NOT NULL,
    `name`  NVARCHAR(100) NOT NULL,
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT UNIQUE (`code`)
);

CREATE TABLE `shopping-mall`.`products`
(
    `index`              INT UNSIGNED  NOT NULL AUTO_INCREMENT, -- (PK)
    `member_index`       INT UNSIGNED  NOT NULL,                -- (FK) : 자기가 올린 상품 뭔지 알 수 있도록 한다
    `product_name`       NVARCHAR(100) NOT NULL,                -- : 상품명
    `product_category`   NVARCHAR(20)  NOT NULL,                -- (FK) : 카테고리 넘버
    `product_price`      INT UNSIGNED  NOT NULL,                -- : 상품 가격
    -- product_stock : 상품 개수
    -- `product_size`           INT UNSIGNED  NOT NULL,                -- : 상품이 옷일 경우
    `image`              LONGBLOB      NOT NULL,                -- 1,2,3 : 대표 이미지, 추가 이미지2,3
    `product_created_at` DATETIME      NOT NULL DEFAULT NOW(),  -- : 등록일(=수정일)
    `product_hit`        INT           NOT NULL DEFAULT 0,      -- : 좋아요 수
    `product_content`    LONGTEXT      NOT NULL,                -- : 상세설명
    `product_views`      INT UNSIGNED  NOT NULL DEFAULT 0,-- : 상품 조회수
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`member_index`) REFERENCES `shopping-mall`.`members` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`product_category`) REFERENCES `shopping-mall`.`product_categories` (`code`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE `shopping-mall`.`product_categories_sub`
(
    `index`           INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `categories_code` NVARCHAR(20)  NOT NULL,
    `code`            NVARCHAR(20)  NOT NULL,
    `name`            NVARCHAR(100) NOT NULL,
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT UNIQUE (`code`),
    CONSTRAINT FOREIGN KEY (`categories_code`) REFERENCES `shopping-mall`.`product_categories` (`code`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE `shopping-mall`.`products_sub`
(
    `index`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_index`        INT UNSIGNED NOT NULL,
    `product_index`       INT UNSIGNED NOT NULL,
    `product_subCategory` NVARCHAR(20) NOT NULL,
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`product_index`) REFERENCES `shopping-mall`.`products` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`member_index`) REFERENCES `shopping-mall`.`members` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`product_subCategory`) REFERENCES `shopping-mall`.`product_categories_sub` (`code`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


INSERT INTO `shopping-mall`.`product_categories`(`code`, `name`)
VALUES ('best', '베스트'),
       ('new', '신상'),
       ('outer', '아우터'),
       ('consultation', '상의'),
       ('training', '트레이닝'),
       ('basic', '베이직'),
       ('onePiece', '원피스'),
       ('skirt', '스커트'),
       ('pants', '팬츠'),
       ('bag', '가방'),
       ('shoes', '신발'),
       ('accessories', '악세서리');



INSERT INTO `shopping-mall`.`product_categories_sub`(`categories_code`, `code`, `name`)
VALUES ('outer', 'vest', '가디건/조끼'),
       ('outer', 'jumper', '야상/점퍼'),
       ('outer', 'jacket', '자켓/코트'),
       ('outer', 'padding', '패딩'),
       ('outer', 'fleece', '플리스'),
       ('consultation', 'longTShirt', '긴팔티셔츠'),
       ('consultation', 'manToMan', '맨투맨'),
       ('consultation', 'hood', '후드'),
       ('consultation', 'shortSleeve', '반팔/민소매티셔츠'),
       ('consultation', 'knit', '니트'),
       ('pants', 'jeans', '청바지'),
       ('pants', 'longPants', '롱팬츠'),
       ('pants', 'leggings', '레깅스'),
       ('pants', 'shortPants', '숏팬츠'),
       ('bag', 'backpack', '백팩/스쿨백'),
       ('bag', 'cross', '크로스/토트백'),
       ('shoes', 'sneakers', '운동화/단화'),
       ('shoes', 'shoes', '구두/워커'),
       ('shoes', 'sandals', '샌들/슬리피/장화'),
       ('accessories', 'jewelry', '주얼리'),
       ('accessories', 'hat', '모자/벨트'),
       ('accessories', 'socks', '양말/스타킹');

CREATE TABLE `shopping-mall`.`product_option_sizes`
(
    `index`         INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `product_index` INT UNSIGNED  NOT NULL,
    `name`          NVARCHAR(100) NOT NULL,
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`product_index`) REFERENCES `shopping-mall`.`products` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE `shopping-mall`.`product_option_colors`
(
    `index`         INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `product_index` INT UNSIGNED  NOT NULL,
    `name`          NVARCHAR(100) NOT NULL,
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`product_index`) REFERENCES `shopping-mall`.`products` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE `shopping-mall`.`product_option_detail`
(
    `index`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_size_index`  INT UNSIGNED NOT NULL,
    `product_color_index` INT UNSIGNED NOT NULL,
    `premium`             INT DEFAULT 0,
    `stock`               INT DEFAULT 0,
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`product_size_index`) REFERENCES `shopping-mall`.`product_option_sizes` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`product_color_index`) REFERENCES `shopping-mall`.`product_option_colors` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- color foreign key 로 추가 (수정)
ALTER TABLE `shopping-mall`.`product_option_detail`
    ADD CONSTRAINT
        FOREIGN KEY (`product_color_index`) REFERENCES `shopping-mall`.`product_option_colors` (`index`)
            ON DELETE CASCADE
            ON UPDATE CASCADE;

CREATE TABLE `shopping-mall`.`product_images`
(
    `index` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `key`   NVARCHAR(40) NOT NULL,
    `image` LONGBLOB     NOT NULL,
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT UNIQUE (`key`)
);

CREATE TABLE `shopping-mall`.`orders`
(
    `index`                INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `member_index`         INT UNSIGNED  NOT NULL,
    `order_total_price`    INT UNSIGNED  NOT NULL DEFAULT 0,
    `payment_state`        BOOLEAN       NOT NULL DEFAULT FALSE,
    `order_address_post`   NVARCHAR(5)   NOT NULL,
    `order_address`        NVARCHAR(100) NOT NULL,
    `order_address_detail` NVARCHAR(100) NOT NULL,
    `order_date`           DATETIME      NOT NULL DEFAULT NOW(),
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`member_index`) REFERENCES `shopping-mall`.`members` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE `shopping-mall`.`orders_product`
(
    `index`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_index`         INT UNSIGNED NOT NULL,
    `order_index`          INT UNSIGNED NOT NULL,
    `product_index`        INT UNSIGNED NOT NULL,
    `product_size_index`   INT UNSIGNED NOT NULL,
    `product_color_index`  INT UNSIGNED NOT NULL,
    `order_count`          INT UNSIGNED NOT NULL DEFAULT 0,
    `order_subtotal_price` INT UNSIGNED NOT NULL DEFAULT 0,
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`member_index`) REFERENCES `shopping-mall`.`members` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`order_index`) REFERENCES `shopping-mall`.`orders` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`product_index`) REFERENCES `shopping-mall`.`products` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`product_size_index`) REFERENCES `shopping-mall`.`product_option_sizes` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`product_color_index`) REFERENCES `shopping-mall`.`product_option_colors` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE `shopping-mall`.`payment`
(
    `index`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `order_index`    INT UNSIGNED NOT NULL,
    `payment_method` NVARCHAR(50) NOT NULL,
    `payment_date`   DATETIME     NOT NULL DEFAULT NOW(),
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`order_index`) REFERENCES `shopping-mall`.`orders` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE `shopping-mall`.`carts`
(
    `index`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_index`       INT UNSIGNED NOT NULL,
    `member_index`        INT UNSIGNED NOT NULL,
    `product_size_index`  INT UNSIGNED NOT NULL,
    `product_color_index` INT UNSIGNED NOT NULL,
    `cart_count`          INT UNSIGNED NOT NULL DEFAULT 0,
    `cart_subtotal_price` INT UNSIGNED NOT NULL DEFAULT 0,
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`product_index`) REFERENCES `shopping-mall`.`products` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`member_index`) REFERENCES `shopping-mall`.`members` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`product_size_index`) REFERENCES `shopping-mall`.`product_option_sizes` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`product_color_index`) REFERENCES `shopping-mall`.`product_option_colors` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE `shopping-mall`.`product_review`
(
    `index`             INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `product_index`     INT UNSIGNED  NOT NULL,
    `member_index`      INT UNSIGNED  NOT NULL,
    `order_index`       INT UNSIGNED  NOT NULL,
    `content`           NVARCHAR(100) NOT NULL,
    `ratingOptions`     INT UNSIGNED  NOT NULL DEFAULT 0,
    `review_created_at` DATETIME      NOT NULL DEFAULT NOW(),
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT `ratingOptions_check` CHECK (`ratingOptions` <= 5),
    CONSTRAINT FOREIGN KEY (`product_index`) REFERENCES `shopping-mall`.`products` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`member_index`) REFERENCES `shopping-mall`.`members` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`order_index`) REFERENCES `shopping-mall`.`orders` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE `shopping-mall`.`product_likes`
(
    `index`           INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_index`    INT UNSIGNED NOT NULL,
    `product_index`   INT UNSIGNED NOT NULL,
    `like_created_at` DATETIME     NOT NULL DEFAULT NOW(),
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`product_index`) REFERENCES `shopping-mall`.`products` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`member_index`) REFERENCES `shopping-mall`.`members` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);