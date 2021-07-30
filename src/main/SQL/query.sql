CREATE DATABASE `shopping-mall`;

-- //사용자
CREATE TABLE `shopping-mall`.`members`
(
    `index`          INT UNSIGNED     NOT NULL AUTO_INCREMENT,
    `email`          NVARCHAR(50)     NOT NULL,               -- 이메일
    `password`       NVARCHAR(128)    NOT NULL,               -- 비말번호
    `nickname`       NVARCHAR(10)     NOT NULL,               -- 별명
    `name`           NVARCHAR(10)     NOT NULL,               -- 이름
    `address_post`   NVARCHAR(5)      NOT NULL,               -- 우편번호
    `address`        NVARCHAR(100)    NOT NULL,               -- 주소
    `address_detail` NVARCHAR(100)    NOT NULL,               -- 상세 주소
    `birth_year`     NVARCHAR(4)      NOT NULL,               -- 생일 년
    `birth_month`    NVARCHAR(2)      NOT NULL,               -- 생일 월
    `birth_day`      NVARCHAR(2)      NOT NULL,               -- 생일 일
    `level`          TINYINT UNSIGNED NOT NULL DEFAULT 9,     -- 접근권한 레벨
    `contact_first`  NVARCHAR(3)      NOT NULL,               -- 전화번호 앞자리
    `contact_second` NVARCHAR(4)      NOT NULL,               -- 전화번호 중간자리
    `contact_third`  NVARCHAR(4)      NOT NULL,               -- 전화번호 끝자리
    `registered_at`  DATETIME         NOT NULL DEFAULT NOW(), -- 가입 날짜
    `admin_flag`     BOOLEAN          NOT NULL DEFAULT FALSE, -- 관리자 권한
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT UNIQUE (`email`),
    CONSTRAINT UNIQUE (`nickname`),
    CONSTRAINT UNIQUE (`contact_first`, `contact_second`, `contact_third`)
);

-- //상품 메인 카테고리
CREATE TABLE `shopping-mall`.`product_categories`
(
    `index` INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `code`  NVARCHAR(20)  NOT NULL, -- 카테고리 코드
    `name`  NVARCHAR(100) NOT NULL, -- 카테고리 이름
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT UNIQUE (`code`)
);

-- //상품
CREATE TABLE `shopping-mall`.`products`
(
    `index`              INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `member_index`       INT UNSIGNED  NOT NULL,               -- 사용자 인덱스
    `product_name`       NVARCHAR(100) NOT NULL,               -- 상품명
    `product_category`   NVARCHAR(20)  NOT NULL,               -- 카테고리 코드
    `product_price`      INT UNSIGNED  NOT NULL,               -- 상품 가격
    `image`              LONGBLOB      NOT NULL,               -- 대표 이미지
    `product_created_at` DATETIME      NOT NULL DEFAULT NOW(), -- 등록일(=수정일)
    `product_hit`        INT           NOT NULL DEFAULT 0,     -- 좋아요 수
    `product_content`    LONGTEXT      NOT NULL,               -- 상세설명
    `product_views`      INT UNSIGNED  NOT NULL DEFAULT 0,     -- 상품 조회수
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`member_index`) REFERENCES `shopping-mall`.`members` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`product_category`) REFERENCES `shopping-mall`.`product_categories` (`code`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- //서브 카테고리
CREATE TABLE `shopping-mall`.`product_categories_sub`
(
    `index`           INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `categories_code` NVARCHAR(20)  NOT NULL, -- 메인 카테고리 코드
    `code`            NVARCHAR(20)  NOT NULL, -- 서브 카테고리 코드
    `name`            NVARCHAR(100) NOT NULL, -- 서브 카테고리 이름
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT UNIQUE (`code`),
    CONSTRAINT FOREIGN KEY (`categories_code`) REFERENCES `shopping-mall`.`product_categories` (`code`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- //상품 등록시, 서브 카테고리
CREATE TABLE `shopping-mall`.`products_sub`
(
    `index`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_index`        INT UNSIGNED NOT NULL, -- 사용자 인덱스
    `product_index`       INT UNSIGNED NOT NULL, -- 상품 인덱스
    `product_subCategory` NVARCHAR(20) NOT NULL, -- 상품 서브 카테고리 코드
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

-- //메인 카테고리 INSERT
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


-- //서브 카테고리 INSERT
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

-- //상품 사이즈
CREATE TABLE `shopping-mall`.`product_option_sizes`
(
    `index`         INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `product_index` INT UNSIGNED  NOT NULL, -- 해당 상품 인덱스
    `name`          NVARCHAR(100) NOT NULL, -- 사이즈 이름
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`product_index`) REFERENCES `shopping-mall`.`products` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- //상품 색상
CREATE TABLE `shopping-mall`.`product_option_colors`
(
    `index`         INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `product_index` INT UNSIGNED  NOT NULL, -- 해당 상품 인덱스
    `name`          NVARCHAR(100) NOT NULL, -- 색상 이름
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`product_index`) REFERENCES `shopping-mall`.`products` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- //해당 상품 사이즈, 색상의 추가 금액과 상품 개수
CREATE TABLE `shopping-mall`.`product_option_detail`
(
    `index`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_size_index`  INT UNSIGNED NOT NULL, -- 사이즈 인덱스
    `product_color_index` INT UNSIGNED NOT NULL, -- 색상 인덱스
    `premium`             INT DEFAULT 0,         -- 추가 금액
    `stock`               INT DEFAULT 0,         -- 개수
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`product_size_index`) REFERENCES `shopping-mall`.`product_option_sizes` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`product_color_index`) REFERENCES `shopping-mall`.`product_option_colors` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- //상품 상세 내용 이미지
CREATE TABLE `shopping-mall`.`product_images`
(
    `index` INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `key`   NVARCHAR(40) NOT NULL,
    `image` LONGBLOB     NOT NULL,
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT UNIQUE (`key`)
);

-- //총 주문 내역
CREATE TABLE `shopping-mall`.`orders`
(
    `index`                INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `member_index`         INT UNSIGNED  NOT NULL,                 -- 사용자 인덱스
    `order_total_price`    INT UNSIGNED  NOT NULL DEFAULT 0,       -- 주문한 총 가격
    `payment_state`        BOOLEAN       NOT NULL DEFAULT FALSE,   -- 결제 완료 유무
    `order_address_post`   NVARCHAR(5)   NOT NULL,                 -- 배송될 우편번호
    `order_address`        NVARCHAR(100) NOT NULL,                 -- 배송될 주소
    `order_address_detail` NVARCHAR(100) NOT NULL,                 -- 배송될 상세 주소
    `order_date`           DATETIME      NOT NULL DEFAULT NOW(),   -- 주문한 날짜
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`member_index`) REFERENCES `shopping-mall`.`members` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- //총 주문 내역의 상품 상세 내용
CREATE TABLE `shopping-mall`.`orders_product`
(
    `index`                INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_index`         INT UNSIGNED NOT NULL,            -- 사용자 인덱스
    `order_index`          INT UNSIGNED NOT NULL,            -- 주문 번호
    `product_index`        INT UNSIGNED NOT NULL,            -- 상품 인덱스
    `product_size_index`   INT UNSIGNED NOT NULL,            -- 상품 사이즈 인덱스
    `product_color_index`  INT UNSIGNED NOT NULL,            -- 상품 색상 인덱스
    `order_count`          INT UNSIGNED NOT NULL DEFAULT 0,  -- 주문 개수
    `order_subtotal_price` INT UNSIGNED NOT NULL DEFAULT 0,  -- 해당 상품 가격 (원가)
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

-- //결제 수단
CREATE TABLE `shopping-mall`.`payment`
(
    `index`          INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `order_index`    INT UNSIGNED NOT NULL,               -- 주문 번호
    `payment_method` NVARCHAR(50) NOT NULL,               -- 결제 방법
    `payment_date`   DATETIME     NOT NULL DEFAULT NOW(), -- 결제 날짜
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`order_index`) REFERENCES `shopping-mall`.`orders` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

-- //장바구니
CREATE TABLE `shopping-mall`.`carts`
(
    `index`               INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `product_index`       INT UNSIGNED NOT NULL,             -- 상품 인덱스
    `member_index`        INT UNSIGNED NOT NULL,             -- 사용자 인덱스
    `product_size_index`  INT UNSIGNED NOT NULL,             -- 상품 사이즈 인덱스
    `product_color_index` INT UNSIGNED NOT NULL,             -- 상품 색상 인덱스
    `cart_count`          INT UNSIGNED NOT NULL DEFAULT 0,   -- 상품 개수
    `cart_subtotal_price` INT UNSIGNED NOT NULL DEFAULT 0,   -- 상품 가격
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

-- //결제 후, 리뷰
CREATE TABLE `shopping-mall`.`product_review`
(
    `index`             INT UNSIGNED  NOT NULL AUTO_INCREMENT,
    `product_index`     INT UNSIGNED  NOT NULL,               -- 상품 인덱스
    `member_index`      INT UNSIGNED  NOT NULL,               -- 사용자 인덱스
    `order_index`       INT UNSIGNED  NOT NULL,               -- 주문 번호
    `content`           NVARCHAR(100) NOT NULL,               -- 리뷰 내용
    `ratingOptions`     INT UNSIGNED  NOT NULL DEFAULT 0,     -- 리뷰 별점
    `review_created_at` DATETIME      NOT NULL DEFAULT NOW(), -- 리뷰 등록 날짜
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

-- //좋아요
CREATE TABLE `shopping-mall`.`product_likes`
(
    `index`           INT UNSIGNED NOT NULL AUTO_INCREMENT,
    `member_index`    INT UNSIGNED NOT NULL,                -- 사용자 인덱스
    `product_index`   INT UNSIGNED NOT NULL,                -- 상품 인덱스
    `like_created_at` DATETIME     NOT NULL DEFAULT NOW(),  -- 좋아요 등록 날짜
    CONSTRAINT PRIMARY KEY (`index`),
    CONSTRAINT FOREIGN KEY (`product_index`) REFERENCES `shopping-mall`.`products` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT FOREIGN KEY (`member_index`) REFERENCES `shopping-mall`.`members` (`index`)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);