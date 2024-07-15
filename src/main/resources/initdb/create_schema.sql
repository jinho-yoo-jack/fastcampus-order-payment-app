CREATE DATABASE IF NOT EXISTS `fastcamp_labs`;
USE fastcamp_labs;
CREATE USER IF NOT EXISTS `fastcamp`@`localhost` IDENTIFIED BY 'testlabs';
CREATE USER `fastcamp`@`%` IDENTIFIED BY 'testlabs';
GRANT all privileges ON `fastcamp_labs`.* TO `fastcamp`@`localhost`;
GRANT all privileges ON `fastcamp_labs`.* TO `fastcamp`@`%`;

CREATE TABLE `purchase_order`
(
    `order_id`     BINARY(16) default (uuid_to_bin(uuid())) NOT NULL COMMENT '주문번호',
    `name`         VARCHAR(255)                             NOT NULL COMMENT '주문자명',
    `phone_number` VARCHAR(255)                             NOT NULL COMMENT '주문자 휴대전화번호',
    `order_state`  VARCHAR(255)                             NOT NULL COMMENT '주문상태',
    `payment_id`   VARCHAR(255)                             NULL COMMENT '결제정보',
    `total_price`  INT                                      NOT NULL COMMENT '상품 가격 * 주문 수량',
    `created_at`   DATETIME   DEFAULT NOW()                 NOT NULL,
    `updated_at`   DATETIME   DEFAULT NOW()                 NOT NUll,
    PRIMARY KEY (order_id)
);

CREATE TABLE `order_items`
(
    `id`            INT                    NOT NULL COMMENT '주문 상세 ID' AUTO_INCREMENT,
    `order_id`      BINARY(16)             NOT NULL COMMENT '전체 주문번호 - FK',
    `item_idx`      INTEGER(10)            NOT NULL COMMENT '주문 상세번호',
    `product_id`    BINARY(16)             NOT NULL COMMENT '상품번호',
    `product_name`  VARCHAR(255)           NOT NULL COMMENT '상품명',
    `product_price` INT                    NOT NULL COMMENT '상품 가격',
    `product_size`  VARCHAR(255)           NOT NULL COMMENT '상품 사이즈',
    `quantity`      INT                    NOT NULL COMMENT '주문 수량',
    `amount`        INT                    NOT NULL COMMENT '총 가격(상품 가격 * 주문 수량)',
    `order_state`   VARCHAR(255)           NOT NULL COMMENT '개별 주문상태',
    `created_at`    DATETIME DEFAULT NOW() NOT NULL,
    `updated_at`    DATETIME DEFAULT NOW() NOT NUll,
    PRIMARY KEY (id, item_idx),
    UNIQUE KEY (order_id, item_idx, product_id)
);

CREATE TABLE `payment_transaction`
(
    `id`              INT                    NOT NULL COMMENT '거래 번호' AUTO_INCREMENT,
    `payment_id`      VARCHAR(255)           NOT NULL COMMENT '거래 ID',
    `method`          VARCHAR(255)           NOT NULL COMMENT '거래 수단',
    `payment_status`  VARCHAR(255)           NOT NULL COMMENT '거래 상태',
    `total_amount`    INT                    NOT NULL COMMENT '최종 결제 금액(즉시 할인 금액 포함)',
    `balance_amount`  INT                    NOT NULL COMMENT '취소 가능한 금액(잔고)',
    `canceled_amount` INT                    NOT NULL COMMENT '취소된 총 금액',
    `pay_out_amount`  INT      DEFAULT 0     NULL COMMENT     '정산 금액(지급액)',
    `created_at`      DATETIME DEFAULT NOW() NOT NULL,
    `updated_at`      DATETIME DEFAULT NOW() NOT NUll,
    PRIMARY KEY (id),
    UNIQUE KEY (id, payment_id, method, payment_status)
);

CREATE TABLE `card_payment`
(
    `payment_key`     VARCHAR(255) NOT NULL COMMENT '결제번호(paymentKey)',
    `card_number`     VARCHAR(255) NOT NULL COMMENT '카드번호',
    `approve_no`      VARCHAR(10)  NOT NULL COMMENT '카드 승인 번호',
    `acquire_status`  VARCHAR(255) NOT NULL COMMENT '카드결제 매입 상태',
    `issuer_code`     VARCHAR(255) NULL COMMENT '카드 발급사 코드',
    `acquirer_code`   VARCHAR(255) NOT NULL COMMENT '카드 매입사 코드',
    `acquirer_status` VARCHAR(255) NOT NULL COMMENT '카드 결제의 상태',
    PRIMARY KEY (payment_key),
    UNIQUE KEY (payment_key, card_number, approve_no)
);

CREATE TABLE `payment_settlements`
(
    `id`                 INT                    NOT NULL COMMENT '정산 번호' AUTO_INCREMENT,
    `payment_id`         VARCHAR(255)           NOT NULL COMMENT '거래 ID',
    `method`             VARCHAR(255)           NOT NULL COMMENT '거래 수단',
    `settlements_status` VARCHAR(255)           NOT NULL COMMENT '정산 상태',
    `total_amount`       INT                    NOT NULL COMMENT '최종 결제 금액(즉시 할인 금액 포함)',
    `pay_out_amount`     INT                    NOT NULL COMMENT '정산 금액(지급액)',
    `canceled_amount`    INT                    NOT NULL COMMENT '취소된 총 금액',
    `sold_date`          DATE                   NOT NULl COMMENT '정산 매출일',
    `paid_out_date`      DATE                   NOT NULl COMMENT '정산 지급일',
    `created_at`         DATETIME DEFAULT NOW() NOT NULL,
    `updated_at`         DATETIME DEFAULT NOW() NOT NUll,
    PRIMARY KEY (id),
    UNIQUE KEY (id, payment_id, method, settlements_status)
);