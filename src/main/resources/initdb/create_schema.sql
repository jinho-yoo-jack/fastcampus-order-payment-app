-- create-user
CREATE DATABASE IF NOT EXISTS `fastcamp_labs`;
USE fastcamp_labs;
CREATE USER IF NOT EXISTS `fastcamp`@`localhost` IDENTIFIED BY 'testlabs';
CREATE USER `fastcamp`@`%` IDENTIFIED BY 'testlabs';
GRANT all privileges ON `fastcamp_labs`.* TO `fastcamp`@`localhost`;
GRANT all privileges ON `fastcamp_labs`.* TO `fastcamp`@`%`;

-- schema.sql
-- Order
CREATE TABLE `purchase_order`
(
    `order_id`      BINARY(16) default (uuid_to_bin(uuid())) NOT NULL COMMENT '주문번호',
    `name`          VARCHAR(255)                             NOT NULL COMMENT '주문자명',
    `phone_number`  VARCHAR(255)                             NOT NULL COMMENT '주문자 휴대전화번호',
    `order_state`   VARCHAR(255)                             NOT NULL COMMENT '주문상태',
    `payment_id`    BINARY(16)                               NULL COMMENT '결제정보',
    `total_price`   INT                                      NOT NULL COMMENT '상품 가격 * 주문 수량',
    `created_at`    DATETIME   DEFAULT NOW()                 NOT NULL,
    `updated_at`    DATETIME   DEFAULT NOW()                 NOT NUll,
    PRIMARY KEY (order_id)
);

CREATE TABLE `order_items`
(
    `order_id`      BINARY(16)                               NOT NULL COMMENT '전체 주문번호',
    `item_idx`      INTEGER(10)                              NOT NULL COMMENT '주문 상세번호',
    `product_id`    BINARY(16)                               NOT NULL COMMENT '상품번호',
    `product_name`  VARCHAR(255)                             NOT NULL COMMENT '상품명',
    `product_price` INT                                      NOT NULL COMMENT '상품 가격',
    `product_size`  VARCHAR(255)                             NOT NULL COMMENT '상품 사이즈',
    `amount`        INT                                      NOT NULL COMMENT '주문 수량',
    `order_state`   VARCHAR(255)                             NOT NULL COMMENT '개별 주문상태',
    `created_at`    DATETIME   DEFAULT NOW()                 NOT NULL,
    `updated_at`    DATETIME   DEFAULT NOW()                 NOT NUll,
    PRIMARY KEY (order_id, item_idx),
    UNIQUE KEY (order_id, item_idx, product_id)
);

CREATE TABLE `payment`
(
    `payment_id` VARCHAR(255) NOT NULL COMMENT '결제번호(ID)',
    `method`     VARCHAR(255) NOT NULL COMMENT '결제수단',
    `status`     VARCHAR(255) NOT NULL COMMENT '결제상태',
    `order_id`   BINARY(16)   NOT NULL COMMENT '주문번호(FK)',
    PRIMARY KEY (payment_id),
    UNIQUE KEY (payment_id, order_id)
);

CREATE TABLE `card_payment`
(
    `payment_id`      VARCHAR(255) NOT NULL COMMENT '결제번호(PK and FK)',
    `card_type`       VARCHAR(255) NOT NULL COMMENT '결제번호(ID)',
    `amount`          INT          NOT NULL COMMENT '최종 결제 금액',
    `issuer_code`     VARCHAR(255) NOT NULL COMMENT '카드 발급사 코드',
    `acquirer_code`   VARCHAR(255) NOT NULL COMMENT '카드 매입사 코드',
    `acquirer_status` VARCHAR(255) NOT NULL COMMENT '카드 결제의 상태',
    PRIMARY KEY (payment_id)
);