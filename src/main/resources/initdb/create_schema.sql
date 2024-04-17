-- create-user
CREATE DATABASE IF NOT EXISTS `service_labs`;
CREATE USER IF NOT EXISTS `fastcamp`@`localhost` identified by 'testlabs';
CREATE USER IF NOT EXISTS `fastcamp`@`%` identified by 'testlabs';
GRANT all privileges on `service_labs`.* TO `fastcamp`@`localhost`;
GRANT all privileges on `service_labs`.* TO `fastcamp`@`%`;

-- schema.sql
CREATE TABLE IF NOT EXISTS `order`
(
    `id`            BINARY(16) default (uuid_to_bin(uuid())) NOT NULL COMMENT '주문번호',
    `order_no`      BINARY(16)                               NOT NULL COMMENT '전체 주문번호',
    `name`          varchar(255)                             NOT NULL COMMENT '주문자명',
    `phone_number`  varchar(255)                             NOT NULL COMMENT '주문자 휴대전화번호',
    `order_state`   varchar(255)                             NOT NULL COMMENT '주문상태',
    `payment_id`    BINARY(16)                               NOT NULL COMMENT '결제번호',
    `product_id`    BINARY(16)                               NOT NULL COMMENT '상품번호',
    `product_name`  varchar(255)                             NOT NULL COMMENT '상품명',
    `product_price` INT                                      NOT NULL COMMENT '상품 가격',
    `product_size`  varchar(255)                             NOT NULL COMMENT '상품 사이즈',
    `amount`        INT                                      NOT NULL COMMENT '주문 수량',
    `total_price`   INT                                      NOT NULL COMMENT '상품 가격 * 주문 수량',
    `created_at`    DATETIME   DEFAULT NOW()                 NOT NULL,
    `updated_at`    DATETIME   DEFAULT NOW()                 NOT NUll,
    PRIMARY KEY (id),
    UNIQUE KEY (id, order_no)
);