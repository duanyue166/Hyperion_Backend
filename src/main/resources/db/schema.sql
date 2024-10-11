CREATE DATABASE IF NOT EXISTS hyperion;
USE hyperion;


START TRANSACTION;


-- 地区表
CREATE TABLE area
(
    id        INT UNSIGNED PRIMARY KEY COMMENT '行政区划编码',
    name      VARCHAR(20) NOT NULL COMMENT '行政区划名称',
    type      TINYINT     NOT NULL CHECK (type IN (1, 2, 3)) COMMENT '类型（1：省，2：市，3：区（县））',
    parent_id INT UNSIGNED COMMENT '父级行政区划编码',
    FOREIGN KEY (parent_id) REFERENCES area (id)
) COMMENT '地区表';
CREATE INDEX idx_area_parent_id ON area (parent_id);


-- 用户表
CREATE TABLE user
(
    id         INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    tel        VARCHAR(20)                            NOT NULL COMMENT '手机号，登录凭据',
    name       VARCHAR(10)                            NOT NULL COMMENT '用户名，昵称',
    pass       VARCHAR(20)                            NOT NULL COMMENT '登录密码',
    role       ENUM ('ADMIN', 'CONSUMER', 'MERCHANT') NOT NULL COMMENT '账户类型（管理员、买家、卖家）',
    email      VARCHAR(320) COMMENT '电子邮件',
    state      ENUM ('ACTIVE', 'FROZEN', 'DELETED')   NOT NULL DEFAULT 'ACTIVE' COMMENT '账户状态（正常、冻结、删除）',
    last_login TIMESTAMP COMMENT '最后登录时间',
    UNIQUE KEY uc_tel (tel, role)
) COMMENT '用户表';
CREATE INDEX idx_user_tel ON user (tel);


-- 商品表
CREATE TABLE goods
(
    id        INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '商品唯一编号',
    user_id   INT UNSIGNED               NOT NULL COMMENT '商品的卖家编号',
    name      VARCHAR(20)                NOT NULL COMMENT '商品名称',
    state     ENUM ('ONSALE', 'OFFSALE') NOT NULL COMMENT '商品状态（正常、下架）',
    cover_url VARCHAR(2083) COMMENT '封面图片url',
    `desc`    TEXT COMMENT '商品描述',
    category  VARCHAR(10) COMMENT '商品分类',
    price     DECIMAL(10, 2)             NOT NULL CHECK (price > 0) COMMENT '价格',
    discount  DECIMAL(3, 2) DEFAULT 1.00 COMMENT '折扣',
    quantity  INT UNSIGNED               NOT NULL CHECK (quantity >= 0) COMMENT '库存数量',
    tot_sales INT UNSIGNED  DEFAULT 0 COMMENT '总销量',
    p_count   INT UNSIGNED  DEFAULT 0 COMMENT '好评数量',
    n_count   INT UNSIGNED  DEFAULT 0 COMMENT '差评数量',
    FOREIGN KEY (user_id) REFERENCES user (id)
) COMMENT '商品表';
CREATE INDEX idx_goods_name ON goods (name);
CREATE INDEX idx_goods_category ON goods (category);
CREATE INDEX idx_goods_user_id ON goods (user_id);


-- 地址表
CREATE TABLE address
(
    id         INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '地址唯一编号',
    user_id    INT UNSIGNED               NOT NULL COMMENT '所属用户编号',
    prov_id    INT UNSIGNED               NOT NULL COMMENT '省份编号',
    city_id    INT UNSIGNED               NOT NULL COMMENT '城市编号',
    dist_id    INT UNSIGNED               NOT NULL COMMENT '区（县）编号',
    detail     VARCHAR(40)                NOT NULL COMMENT '详细地址',
    is_default TINYINT(1)                          DEFAULT 0 COMMENT '是否为默认地址（0：否）',
    state      ENUM ('ACTIVE', 'DELETED') NOT NULL DEFAULT 'ACTIVE' COMMENT '状态（正常，删除）',
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (prov_id) REFERENCES area (id),
    FOREIGN KEY (city_id) REFERENCES area (id),
    FOREIGN KEY (dist_id) REFERENCES area (id)
) COMMENT '地址表';
CREATE INDEX idx_address_user_id ON address (user_id);


-- 订单表
CREATE TABLE `order`
(
    id            INT UNSIGNED PRIMARY KEY AUTO_INCREMENT COMMENT '订单ID',
    user_id       INT UNSIGNED                                        NOT NULL COMMENT '下单用户编号',
    state         ENUM ('PLACED', 'CONFIRMED', 'SHIPPED', 'COMPLETE') NOT NULL COMMENT '订单状态（待付款，待发货，待收货，完成）',
    payment       DECIMAL(10, 2)                                      NOT NULL CHECK (payment >= 0) COMMENT '实付款',
    consignee     VARCHAR(20)                                         NOT NULL COMMENT '收货人',
    contact       VARCHAR(20)                                         NOT NULL COMMENT '收货人联系电话',
    address       VARCHAR(80)                                         NOT NULL COMMENT '收获完整详细地址',
    create_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '订单创建时间',
    complete_time TIMESTAMP COMMENT '订单完成（签收）时间',
    FOREIGN KEY (user_id) REFERENCES user (id)
) COMMENT '订单表';
CREATE INDEX idx_order_user_id ON `order` (user_id);
CREATE INDEX idx_order_state ON `order` (state);


-- 购物车条目
CREATE TABLE trolly
(
    user_id  INT UNSIGNED NOT NULL COMMENT '购物车所属用户编号',
    goods_id INT UNSIGNED NOT NULL COMMENT '商品编号',
    quantity INT UNSIGNED NOT NULL CHECK (quantity > 0) COMMENT '商品添加数量',
    PRIMARY KEY (user_id, goods_id),
    FOREIGN KEY (user_id) REFERENCES user (id),
    FOREIGN KEY (goods_id) REFERENCES goods (id)
) COMMENT '购物表条目';


-- 售货表条目
CREATE TABLE sold_goods
(
    order_id   INT UNSIGNED   NOT NULL COMMENT '订单编号',
    goods_id   INT UNSIGNED   NOT NULL COMMENT '货物编号',
    quantity   INT UNSIGNED   NOT NULL CHECK (quantity > 0) COMMENT '售出数量',
    unit_price DECIMAL(10, 2) NOT NULL CHECK (unit_price > 0) COMMENT '成交单价',
    review     TINYINT DEFAULT 0 CHECK (review IN (-1, 0, 1)) COMMENT '评价',
    PRIMARY KEY (order_id, goods_id),
    FOREIGN KEY (order_id) REFERENCES `order` (id),
    FOREIGN KEY (goods_id) REFERENCES goods (id)
) COMMENT '售货表条目';


COMMIT;

ALTER TABLE user
    MODIFY COLUMN pass VARCHAR(255);

ALTER TABLE goods
    MODIFY COLUMN state ENUM ('ACTIVE', 'DELETED') NOT NULL COMMENT '商品状态（正常、删除）',
    ADD COLUMN sale ENUM ('ON', 'OFF') NOT NULL DEFAULT 'OFF' COMMENT '销售状态（开启、关闭）';

ALTER TABLE goods
    MODIFY COLUMN sale ENUM ('ON', 'OFF') NOT NULL DEFAULT 'OFF' COMMENT '销售状态（开启、关闭）';

ALTER TABLE goods
    CHANGE p_count pos_count INT UNSIGNED;
ALTER TABLE goods
    CHANGE n_count neg_count INT UNSIGNED;

ALTER TABLE trolly RENAME TO trolley;

ALTER TABLE address
    ADD COLUMN consignee VARCHAR(20) COMMENT '收货人姓名',
    ADD COLUMN contact   VARCHAR(20) COMMENT '收货人联系电话';

ALTER TABLE address
    MODIFY COLUMN consignee VARCHAR(20) NOT NULL COMMENT '收货人姓名',
    MODIFY COLUMN contact VARCHAR(20) NOT NULL COMMENT '收货人联系电话';

ALTER TABLE `order`
    ADD COLUMN cover_url VARCHAR(2083) COMMENT '订单封面';

ALTER TABLE `order`
    DROP FOREIGN KEY order_ibfk_1;

ALTER TABLE `order`
    DROP COLUMN user_id,
    ADD COLUMN con_id INT UNSIGNED NOT NULL COMMENT '买家用户编号',
    ADD COLUMN mer_id INT UNSIGNED NOT NULL COMMENT '卖家用户编号';

-- 添加新的外键约束
ALTER TABLE `order`
    ADD CONSTRAINT fk_con_id FOREIGN KEY (con_id) REFERENCES user (id),
    ADD CONSTRAINT fk_mer_id FOREIGN KEY (mer_id) REFERENCES user (id);

START TRANSACTION;
SET FOREIGN_KEY_CHECKS = 0;
ALTER TABLE `order`
    MODIFY COLUMN id INT UNSIGNED AUTO_INCREMENT NOT NULL;
SET FOREIGN_KEY_CHECKS = 1;
COMMIT;