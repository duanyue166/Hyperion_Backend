USE hyperion;

SET GLOBAL local_infile = 1;

START TRANSACTION;
SET FOREIGN_KEY_CHECKS = 0;
-- 导入 CSV 文件到 area 表
LOAD DATA LOCAL INFILE '/home/dy/Uploads/P/area.csv'
    INTO TABLE area
    FIELDS TERMINATED BY ','
    ENCLOSED BY '"'
    LINES TERMINATED BY '\n'
    IGNORE 0 LINES
    (id, name, type, parent_id);
SET FOREIGN_KEY_CHECKS = 1;
-- 提交事务
COMMIT;