-- 创建供应商表（tb_suppliers）
CREATE TABLE tb_suppliers (
supplierId INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT '供应商ID',
supplierName VARCHAR(50) UNIQUE NOT NULL COMMENT '供应商名称',
contactName VARCHAR(20) NOT NULL COMMENT '联系人姓名',
contactPhone VARCHAR(15) NOT NULL COMMENT '联系电话',
address VARCHAR(200) NOT NULL COMMENT '地址',
bankAccount VARCHAR(30) COMMENT '银行账户',
remark VARCHAR(500) COMMENT '备注',
cooperationStatus TINYINT DEFAULT 1 COMMENT '合作状态',
createTime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
updateTime DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 插入五条供应商数据
INSERT INTO tb_suppliers (supplierName, contactName, contactPhone, address, bankAccount, remark)
VALUES
('北京绿野蔬菜供应有限公司', '张伟', '13800138000', '北京市海淀区中关村大街1号', '1001001001001001001', '长期合作伙伴'),
('上海鲜果配送中心', '李娜', '13900139000', '上海市浦东新区世纪大道100号', '1002002002002002002', '新开发客户'),
('广州肉类批发商行', '王强', '13700137000', '广州市天河区体育西路1号', '1003003003003003003', '优质供应商'),
('深圳海鲜市场', '赵敏', '13600136000', '深圳市南山区科技园科技路1号', '1004004004004004004', '价格优惠'),
('杭州粮油贸易公司', '孙丽', '13500135000', '杭州市滨江区长河街道江南大道1号', '1005005005005005005', '技术实力强');

-- 创建仓库表（tb_stash）
CREATE TABLE tb_stash (
stashId INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT '仓库ID',
stashName VARCHAR(50) UNIQUE NOT NULL COMMENT '仓库名称',
stashAddress VARCHAR(200) NOT NULL COMMENT '仓库地址',
storageTemperature VARCHAR(20) COMMENT '存储温度',
stashArea VARCHAR(20) COMMENT '仓库面积',
adminName VARCHAR(20) COMMENT '管理员名称',
remark VARCHAR(500) COMMENT '备注',
createTime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
updateTime DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
isDeleted TINYINT DEFAULT 0 COMMENT '是否删除（0-未删除，1-已删除）'
);

-- 插入五条仓库数据
INSERT INTO tb_stash (stashName, stashAddress, storageTemperature, stashArea, adminName, remark)
VALUES
('郑州冷冻仓库', '郑州市金水区农业路1号', '冷冻', '5000平方米', '张伟', '主要存放冷冻食品'),
('郑州冷藏仓库', '郑州市中原区建设路2号', '冷藏', '3000平方米', '李娜', '主要存放冷藏食品'),
('郑州常温仓库', '郑州市二七区解放路3号', '常温', '4000平方米', '王强', '主要存放常温食品'),
('郑州生鲜仓库', '郑州市管城区郑汴路4号', '冷藏', '2000平方米', '赵敏', '专门处理新鲜食材'),
('郑州干货仓库', '郑州市惠济区文化路5号', '常温', '6000平方米', '孙丽', '主要用于储存干货');

-- 创建用户表（tb_user）
CREATE TABLE tb_user (
userId INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
username VARCHAR(50) NOT NULL COMMENT '用户名',
adminName VARCHAR(50) UNIQUE COMMENT '管理员名称',
password VARCHAR(20) NOT NULL COMMENT '密码',
position VARCHAR(20) NOT NULL COMMENT '职位',
telephone VARCHAR(15) NOT NULL COMMENT '电话',
workPlace VARCHAR(15) NOT NULL COMMENT '工作地点',
role VARCHAR(15) COMMENT '角色',
remark VARCHAR(500) COMMENT '备注',
createTime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
updateTime DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 插入30条用户数据
INSERT INTO tb_user (username, adminName, password, position, telephone, workPlace, role, remark)
VALUES
('AdminZhangWei', '张伟', '123456', '经理', '13800138000', '郑州冷冻仓库', 'Admin', '负责冷冻食品管理'),
('AdminLiNa', '李娜', '123456', '主管', '13900139000', '郑州冷藏仓库', 'Admin', '负责冷藏食品管理'),
('AdminWangQiang', '王强', '123456', '主管', '13700137000', '郑州常温仓库', 'Admin', '负责常温食品管理'),
('AdminZhaoMin', '赵敏', '123456', '主管', '13600136000', '郑州生鲜仓库', 'Admin', '负责生鲜食材管理'),
('AdminSunLi', '孙丽', '123456', '主管', '13500135000', '郑州干货仓库', 'Admin', '负责干货管理'),
('user6', '刘洋', '123456', '员工', '13400134000', '郑州冷冻仓库', 'User', '普通员工'),
('user7', '陈静', '123456', '员工', '13300133000', '郑州冷藏仓库', 'User', '普通员工'),
('user8', '杨柳', '123456', '员工', '13200132000', '郑州常温仓库', 'User', '普通员工'),
('user9', '周杰', '123456', '员工', '13100131000', '郑州生鲜仓库', 'User', '普通员工'),
('user10', '吴梅', '123456', '员工', '13000130000', '郑州干货仓库', 'User', '普通员工'),
('user11', '郑刚', '123456', '员工', '12900129000', '郑州冷冻仓库', 'User', '普通员工'),
('user12', '马丽', '123456', '员工', '12800128000', '郑州冷藏仓库', 'User', '普通员工'),
('user13', '黄涛', '123456', '员工', '12700127000', '郑州常温仓库', 'User', '普通员工'),
('user14', '朱红', '123456', '员工', '12600126000', '郑州生鲜仓库', 'User', '普通员工'),
('user15', '徐明', '123456', '员工', '12500125000', '郑州干货仓库', 'User', '普通员工'),
('user16', '林芳', '123456', '员工', '12400124000', '郑州冷冻仓库', 'User', '普通员工'),
('user17', '宋辉', '123456', '员工', '12300123000', '郑州冷藏仓库', 'User', '普通员工'),
('user18', '何军', '123456', '员工', '12200122000', '郑州常温仓库', 'User', '普通员工'),
('user19', '郭美', '123456', '员工', '12100121000', '郑州生鲜仓库', 'User', '普通员工'),
('user20', '田亮', '123456', '员工', '12000120000', '郑州干货仓库', 'User', '普通员工'),
('user21', '罗艳', '123456', '员工', '11900119000', '郑州冷冻仓库', 'User', '普通员工'),
('user22', '莫勇', '123456', '员工', '11800118000', '郑州冷藏仓库', 'User', '普通员工'),
('user23', '程莉', '123456', '员工', '11700117000', '郑州常温仓库', 'User', '普通员工'),
('user24', '潘华', '123456', '员工', '11600116000', '郑州生鲜仓库', 'User', '普通员工'),
('user25', '戴强', '123456', '员工', '11500115000', '郑州干货仓库', 'User', '普通员工'),
('user26', '齐晓', '123456', '员工', '11400114000', '郑州冷冻仓库', 'User', '普通员工'),
('user27', '冯洁', '123456', '员工', '11300113000', '郑州冷藏仓库', 'User', '普通员工'),
('user28', '熊飞', '123456', '员工', '11200112000', '郑州常温仓库', 'User', '普通员工'),
('user29', '钱丽', '123456', '员工', '11100111000', '郑州生鲜仓库', 'User', '普通员工'),
('user30', '苏浩', '123456', '员工', '11000110000', '郑州干货仓库', 'User', '普通员工');

-- 创建商品表(tb_product)
CREATE TABLE tb_product (
productId INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT '商品ID',
productName VARCHAR(50) NOT NULL COMMENT '商品名称',
category VARCHAR(20) NOT NULL COMMENT '类别',
stashName VARCHAR(50) NOT NULL COMMENT '仓库名称',
supplierName VARCHAR(50) NOT NULL COMMENT '供应商名称',
storageTemperature VARCHAR(20) NOT NULL COMMENT '存储温度',
remark VARCHAR(500) COMMENT '备注',
shelfLife INTEGER COMMENT '保质期',
productTime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '生产日期',
createTime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
updateTime DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
imageUrl MEDIUMTEXT COMMENT '图片路径'
);

-- 插入50条商品数据
INSERT INTO tb_product (productName, category, stashName, supplierName, storageTemperature, remark, shelfLife, productTime)
VALUES
('冷冻鸡胸肉', '肉类', '郑州冷冻仓库', '北京绿野蔬菜供应有限公司', '冷冻', '优质冷冻鸡肉', 360, DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
('速冻饺子', '面食', '郑州冷冻仓库', '上海鲜果配送中心', '冷冻', '多种口味可选', 360, DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
('冷冻虾仁', '海鲜', '郑州冷冻仓库', '广州肉类批发商行', '冷冻', '新鲜冷冻虾仁', 360, DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
('冷冻豌豆', '蔬菜', '郑州冷冻仓库', '深圳海鲜市场', '冷冻', '绿色无污染', 360, DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
('冷冻玉米粒', '蔬菜', '郑州冷冻仓库', '杭州粮油贸易公司', '冷冻', '适合各种烹饪', 360, DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
('冷冻牛肉片', '肉类', '郑州冷冻仓库', '北京绿野蔬菜供应有限公司', '冷冻', '薄切牛肉片', 360, DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
('冷冻鱼丸', '海鲜', '郑州冷冻仓库', '上海鲜果配送中心', '冷冻', '多种口味鱼丸', 360, DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
('冷冻青豆', '蔬菜', '郑州冷冻仓库', '广州肉类批发商行', '冷冻', '健康小菜', 360, DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
('冷冻胡萝卜丁', '蔬菜', '郑州冷冻仓库', '深圳海鲜市场', '冷冻', '方便快捷', 360, DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),
('冷冻豆腐皮', '豆制品', '郑州冷冻仓库', '杭州粮油贸易公司', '冷冻', '素食佳品', 180, DATE_SUB(CURDATE(), INTERVAL 1 MONTH)),

('冷藏酸奶', '乳制品', '郑州冷藏仓库', '北京绿野蔬菜供应有限公司', '冷藏', '多种口味酸奶', 180, DATE_SUB(CURDATE(), INTERVAL 2 MONTH)),
('冷藏牛奶', '乳制品', '郑州冷藏仓库', '上海鲜果配送中心', '冷藏', '纯天然牛奶', 180, DATE_SUB(CURDATE(), INTERVAL 2 MONTH)),
('冷藏果汁', '饮料', '郑州冷藏仓库', '广州肉类批发商行', '冷藏', '新鲜榨汁', 180, DATE_SUB(CURDATE(), INTERVAL 2 MONTH)),
('冷藏橙子', '水果', '郑州冷藏仓库', '深圳海鲜市场', '冷藏', '多汁爽口', 180, DATE_SUB(CURDATE(), INTERVAL 2 MONTH)),
('冷藏苹果', '水果', '郑州冷藏仓库', '杭州粮油贸易公司', '冷藏', '脆甜可口', 180, DATE_SUB(CURDATE(), INTERVAL 2 MONTH)),
('冷藏草莓', '水果', '郑州冷藏仓库', '北京绿野蔬菜供应有限公司', '冷藏', '酸甜适中', 60, DATE_SUB(CURDATE(), INTERVAL 2 MONTH)),
('冷藏蓝莓', '水果', '郑州冷藏仓库', '上海鲜果配送中心', '冷藏', '富含维生素', 60, DATE_SUB(CURDATE(), INTERVAL 2 MONTH)),
('冷藏香蕉', '水果', '郑州冷藏仓库', '广州肉类批发商行', '冷藏', '营养丰富', 60, DATE_SUB(CURDATE(), INTERVAL 2 MONTH)),
('冷藏菠萝', '水果', '郑州冷藏仓库', '深圳海鲜市场', '冷藏', '清甜解渴', 60, DATE_SUB(CURDATE(), INTERVAL 2 MONTH)),
('冷藏葡萄', '水果', '郑州冷藏仓库', '杭州粮油贸易公司', '冷藏', '口感甜美', 180, DATE_SUB(CURDATE(), INTERVAL 2 MONTH)),

('常温大米', '谷物', '郑州常温仓库', '北京绿野蔬菜供应有限公司', '常温', '优质大米', 180, DATE_SUB(CURDATE(), INTERVAL 6 MONTH)),
('常温面粉', '谷物', '郑州常温仓库', '上海鲜果配送中心', '常温', '低筋高筋均可', 180, DATE_SUB(CURDATE(), INTERVAL 6 MONTH)),
('常温食用油', '调味品', '郑州常温仓库', '广州肉类批发商行', '常温', '非转基因', 180, DATE_SUB(CURDATE(), INTERVAL 6 MONTH)),
('常温酱油', '调味品', '郑州常温仓库', '深圳海鲜市场', '常温', '生抽老抽均有', 180, DATE_SUB(CURDATE(), INTERVAL 6 MONTH)),
('常温醋', '调味品', '郑州常温仓库', '杭州粮油贸易公司', '常温', '香醋米醋', 180, DATE_SUB(CURDATE(), INTERVAL 6 MONTH)),
('常温白糖', '干货', '郑州干货仓库', '北京绿野蔬菜供应有限公司', '常温', '精制白糖', 180, DATE_SUB(CURDATE(), INTERVAL 6 MONTH)),
('常温盐', '干货', '郑州干货仓库', '上海鲜果配送中心', '常温', '加碘盐', 180, DATE_SUB(CURDATE(), INTERVAL 6 MONTH)),
('常温味精', '调料', '郑州干货仓库', '广州肉类批发商行', '常温', '增味提鲜', 180, DATE_SUB(CURDATE(), INTERVAL 6 MONTH)),
('常温花椒', '调料', '郑州干货仓库', '深圳海鲜市场', '常温', '去腥增香', 180, DATE_SUB(CURDATE(), INTERVAL 6 MONTH)),
('常温辣椒粉', '调料', '郑州干货仓库', '杭州粮油贸易公司', '常温', '辣而不燥', 180, DATE_SUB(CURDATE(), INTERVAL 6 MONTH)),
('常温黑胡椒', '调料', '郑州干货仓库', '北京绿野蔬菜供应有限公司', '常温', '浓郁香味', 180, DATE_SUB(CURDATE(), INTERVAL 6 MONTH)),
('常温生姜', '调料', '郑州干货仓库', '上海鲜果配送中心', '常温', '驱寒暖身', 180, DATE_SUB(CURDATE(), INTERVAL 6 MONTH)),
('常温大蒜', '调料', '郑州干货仓库', '广州肉类批发商行', '常温', '杀菌消炎', 180, DATE_SUB(CURDATE(), INTERVAL 6 MONTH)),
('常温洋葱', '调料', '郑州干货仓库', '深圳海鲜市场', '常温', '增加风味', 180, DATE_SUB(CURDATE(), INTERVAL 6 MONTH)),
('常温蒜蓉', '调料', '郑州干货仓库', '杭州粮油贸易公司', '常温', '调味必备', 180, DATE_SUB(CURDATE(), INTERVAL 6 MONTH));

-- 创建库存表(tb_inventory)
CREATE TABLE tb_inventory (
inventoryId INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT '库存ID',
productName VARCHAR(50) NOT NULL COMMENT '商品名称',
stashName VARCHAR(50) NOT NULL COMMENT '仓库名称',
supplierName VARCHAR(50) NOT NULL COMMENT '供应商名称',
remark VARCHAR(500) COMMENT '备注',
quantity INTEGER NOT NULL DEFAULT 0 COMMENT '数量',
lastStockTime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '最后入库时间',
isDeleted TINYINT DEFAULT 0 COMMENT '是否删除（0-未删除，1-已删除）'
);

-- 创建50条库存数据
INSERT INTO tb_inventory (productName, stashName, supplierName, remark, quantity)
VALUES
('冷冻鸡胸肉', '郑州冷冻仓库', '北京绿野蔬菜供应有限公司', '优质冷冻鸡肉', 900),
('速冻饺子', '郑州冷冻仓库', '上海鲜果配送中心', '多种口味可选', 1200),
('冷冻虾仁', '郑州冷冻仓库', '广州肉类批发商行', '新鲜冷冻虾仁', 1500),
('冷冻豌豆', '郑州冷冻仓库', '深圳海鲜市场', '绿色无污染', 1800),
('冷冻玉米粒', '郑州冷冻仓库', '杭州粮油贸易公司', '适合各种烹饪', 2100),
('冷冻牛肉片', '郑州冷冻仓库', '北京绿野蔬菜供应有限公司', '薄切牛肉片', 2400),
('冷冻鱼丸', '郑州冷冻仓库', '上海鲜果配送中心', '多种口味鱼丸', 2700),
('冷冻青豆', '郑州冷冻仓库', '广州肉类批发商行', '健康小菜', 3000),
('冷冻胡萝卜丁', '郑州冷冻仓库', '深圳海鲜市场', '方便快捷', 3300),
('冷冻豆腐皮', '郑州冷冻仓库', '杭州粮油贸易公司', '素食佳品', 3600),

('冷藏酸奶', '郑州冷藏仓库', '北京绿野蔬菜供应有限公司', '多种口味酸奶', 900),
('冷藏牛奶', '郑州冷藏仓库', '上海鲜果配送中心', '纯天然牛奶', 1200),
('冷藏果汁', '郑州冷藏仓库', '广州肉类批发商行', '新鲜榨汁', 1500),
('冷藏橙子', '郑州冷藏仓库', '深圳海鲜市场', '多汁爽口', 1800),
('冷藏苹果', '郑州冷藏仓库', '杭州粮油贸易公司', '脆甜可口', 2100),
('冷藏草莓', '郑州冷藏仓库', '北京绿野蔬菜供应有限公司', '酸甜适中', 2400),
('冷藏蓝莓', '郑州冷藏仓库', '上海鲜果配送中心', '富含维生素', 2700),
('冷藏香蕉', '郑州冷藏仓库', '广州肉类批发商行', '营养丰富', 3000),
('冷藏菠萝', '郑州冷藏仓库', '深圳海鲜市场', '清甜解渴', 3300),
('冷藏葡萄', '郑州冷藏仓库', '杭州粮油贸易公司', '口感甜美', 3600),

('常温大米', '郑州常温仓库', '北京绿野蔬菜供应有限公司', '优质大米', 900),
('常温面粉', '郑州常温仓库', '上海鲜果配送中心', '低筋高筋均可', 1200),
('常温食用油', '郑州常温仓库', '广州肉类批发商行', '非转基因', 1500),
('常温酱油', '郑州常温仓库', '深圳海鲜市场', '生抽老抽均有', 1800),
('常温醋', '郑州常温仓库', '杭州粮油贸易公司', '香醋米醋', 2100),
('常温白糖', '郑州干货仓库', '北京绿野蔬菜供应有限公司', '精制白糖', 2400),
('常温盐', '郑州干货仓库', '上海鲜果配送中心', '加碘盐', 2700),
('常温味精', '郑州干货仓库', '广州肉类批发商行', '增味提鲜', 3000),
('常温花椒', '郑州干货仓库', '深圳海鲜市场', '去腥增香', 3300),
('常温辣椒粉', '郑州干货仓库', '杭州粮油贸易公司', '辣而不燥', 3600),
('常温黑胡椒', '郑州干货仓库', '北京绿野蔬菜供应有限公司', '浓郁香味', 900),
('常温生姜', '郑州干货仓库', '上海鲜果配送中心', '驱寒暖身', 1200),
('常温大蒜', '郑州干货仓库', '广州肉类批发商行', '杀菌消炎', 1500),
('常温洋葱', '郑州干货仓库', '深圳海鲜市场', '增加风味', 1800),
('常温蒜蓉', '郑州干货仓库', '杭州粮油贸易公司', '调味必备', 2100);

-- 创建库存记录表(tb_inventory_log)
CREATE TABLE tb_inventory_log (
inventoryLogId INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT '库存记录ID',
productName VARCHAR(50) NOT NULL COMMENT '商品名称',
stashName VARCHAR(50) NOT NULL COMMENT '仓库名称',
supplierName VARCHAR(50) NOT NULL COMMENT '供应商名称',
type VARCHAR(10) NOT NULL COMMENT '类型',
remark VARCHAR(500) COMMENT '备注',
quantity INTEGER NOT NULL DEFAULT 0 COMMENT '数量',
lastStockTime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '执行时间',
isDeleted TINYINT DEFAULT 0 COMMENT '是否删除（0-未删除，1-已删除）'
);

-- 创建150条库存记录
INSERT INTO tb_inventory_log (productName, stashName, supplierName, type, remark, quantity, lastStockTime)
VALUES
('冷冻鸡胸肉', '郑州冷冻仓库', '北京绿野蔬菜供应有限公司', 'add', '初次入库', 900, '2025-04-01 08:00:00'),
('冷冻鸡胸肉', '郑州冷冻仓库', '北京绿野蔬菜供应有限公司', 'sub', '销售出库', 300, '2025-04-02 09:00:00'),
('冷冻鸡胸肉', '郑州冷冻仓库', '北京绿野蔬菜供应有限公司', 'add', '补货入库', 600, '2025-04-03 10:00:00'),
('冷冻鸡胸肉', '郑州冷冻仓库', '北京绿野蔬菜供应有限公司', 'sub', '损坏报废', 100, '2025-04-04 11:00:00'),
('冷冻鸡胸肉', '郑州冷冻仓库', '北京绿野蔬菜供应有限公司', 'add', '紧急补货', 300, '2025-04-05 12:00:00'),

('速冻饺子', '郑州冷冻仓库', '上海鲜果配送中心', 'add', '初次入库', 1200, '2025-04-01 08:00:00'),
('速冻饺子', '郑州冷冻仓库', '上海鲜果配送中心', 'sub', '销售出库', 400, '2025-04-02 09:00:00'),
('速冻饺子', '郑州冷冻仓库', '上海鲜果配送中心', 'add', '补货入库', 600, '2025-04-03 10:00:00'),
('速冻饺子', '郑州冷冻仓库', '上海鲜果配送中心', 'sub', '损坏报废', 200, '2025-04-04 11:00:00'),
('速冻饺子', '郑州冷冻仓库', '上海鲜果配送中心', 'add', '紧急补货', 400, '2025-04-05 12:00:00'),

('冷冻虾仁', '郑州冷冻仓库', '广州肉类批发商行', 'add', '初次入库', 1500, '2025-04-01 08:00:00'),
('冷冻虾仁', '郑州冷冻仓库', '广州肉类批发商行', 'sub', '销售出库', 500, '2025-04-02 09:00:00'),
('冷冻虾仁', '郑州冷冻仓库', '广州肉类批发商行', 'add', '补货入库', 900, '2025-04-03 10:00:00'),
('冷冻虾仁', '郑州冷冻仓库', '广州肉类批发商行', 'sub', '损坏报废', 300, '2025-04-04 11:00:00'),
('冷冻虾仁', '郑州冷冻仓库', '广州肉类批发商行', 'add', '紧急补货', 600, '2025-04-05 12:00:00'),

('冷冻豌豆', '郑州冷冻仓库', '深圳海鲜市场', 'add', '初次入库', 1800, '2025-04-01 08:00:00'),
('冷冻豌豆', '郑州冷冻仓库', '深圳海鲜市场', 'sub', '销售出库', 600, '2025-04-02 09:00:00'),
('冷冻豌豆', '郑州冷冻仓库', '深圳海鲜市场', 'add', '补货入库', 1200, '2025-04-03 10:00:00'),
('冷冻豌豆', '郑州冷冻仓库', '深圳海鲜市场', 'sub', '损坏报废', 400, '2025-04-04 11:00:00'),
('冷冻豌豆', '郑州冷冻仓库', '深圳海鲜市场', 'add', '紧急补货', 800, '2025-04-05 12:00:00'),

('冷冻玉米粒', '郑州冷冻仓库', '杭州粮油贸易公司', 'add', '初次入库', 2100, '2025-04-01 08:00:00'),
('冷冻玉米粒', '郑州冷冻仓库', '杭州粮油贸易公司', 'sub', '销售出库', 700, '2025-04-02 09:00:00'),
('冷冻玉米粒', '郑州冷冻仓库', '杭州粮油贸易公司', 'add', '补货入库', 1400, '2025-04-03 10:00:00'),
('冷冻玉米粒', '郑州冷冻仓库', '杭州粮油贸易公司', 'sub', '损坏报废', 500, '2025-04-04 11:00:00'),
('冷冻玉米粒', '郑州冷冻仓库', '杭州粮油贸易公司', 'add', '紧急补货', 1000, '2025-04-05 12:00:00'),

('冷冻牛肉片', '郑州冷冻仓库', '北京绿野蔬菜供应有限公司', 'add', '初次入库', 2400, '2025-04-01 08:00:00'),
('冷冻牛肉片', '郑州冷冻仓库', '北京绿野蔬菜供应有限公司', 'sub', '销售出库', 800, '2025-04-02 09:00:00'),
('冷冻牛肉片', '郑州冷冻仓库', '北京绿野蔬菜供应有限公司', 'add', '补货入库', 1600, '2025-04-03 10:00:00'),
('冷冻牛肉片', '郑州冷冻仓库', '北京绿野蔬菜供应有限公司', 'sub', '损坏报废', 600, '2025-04-04 11:00:00'),
('冷冻牛肉片', '郑州冷冻仓库', '北京绿野蔬菜供应有限公司', 'add', '紧急补货', 1200, '2025-04-05 12:00:00'),

('冷冻鱼丸', '郑州冷冻仓库', '上海鲜果配送中心', 'add', '初次入库', 2700, '2025-04-01 08:00:00'),
('冷冻鱼丸', '郑州冷冻仓库', '上海鲜果配送中心', 'sub', '销售出库', 900, '2025-04-02 09:00:00'),
('冷冻鱼丸', '郑州冷冻仓库', '上海鲜果配送中心', 'add', '补货入库', 1800, '2025-04-03 10:00:00'),
('冷冻鱼丸', '郑州冷冻仓库', '上海鲜果配送中心', 'sub', '损坏报废', 700, '2025-04-04 11:00:00'),
('冷冻鱼丸', '郑州冷冻仓库', '上海鲜果配送中心', 'add', '紧急补货', 1500, '2025-04-05 12:00:00'),

('冷冻青豆', '郑州冷冻仓库', '广州肉类批发商行', 'add', '初次入库', 3000, '2025-04-01 08:00:00'),
('冷冻青豆', '郑州冷冻仓库', '广州肉类批发商行', 'sub', '销售出库', 1000, '2025-04-02 09:00:00'),
('冷冻青豆', '郑州冷冻仓库', '广州肉类批发商行', 'add', '补货入库', 2000, '2025-04-03 10:00:00'),
('冷冻青豆', '郑州冷冻仓库', '广州肉类批发商行', 'sub', '损坏报废', 800, '2025-04-04 11:00:00'),
('冷冻青豆', '郑州冷冻仓库', '广州肉类批发商行', 'add', '紧急补货', 1600, '2025-04-05 12:00:00'),

('冷冻胡萝卜丁', '郑州冷冻仓库', '深圳海鲜市场', 'add', '初次入库', 3300, '2025-04-01 08:00:00'),
('冷冻胡萝卜丁', '郑州冷冻仓库', '深圳海鲜市场', 'sub', '销售出库', 1100, '2025-04-02 09:00:00'),
('冷冻胡萝卜丁', '郑州冷冻仓库', '深圳海鲜市场', 'add', '补货入库', 2200, '2025-04-03 10:00:00'),
('冷冻胡萝卜丁', '郑州冷冻仓库', '深圳海鲜市场', 'sub', '损坏报废', 900, '2025-04-04 11:00:00'),
('冷冻胡萝卜丁', '郑州冷冻仓库', '深圳海鲜市场', 'add', '紧急补货', 1800, '2025-04-05 12:00:00'),

('冷冻豆腐皮', '郑州冷冻仓库', '杭州粮油贸易公司', 'add', '初次入库', 3600, '2025-04-01 08:00:00'),
('冷冻豆腐皮', '郑州冷冻仓库', '杭州粮油贸易公司', 'sub', '销售出库', 1200, '2025-04-02 09:00:00'),
('冷冻豆腐皮', '郑州冷冻仓库', '杭州粮油贸易公司', 'add', '补货入库', 2400, '2025-04-03 10:00:00'),
('冷冻豆腐皮', '郑州冷冻仓库', '杭州粮油贸易公司', 'sub', '损坏报废', 1000, '2025-04-04 11:00:00'),
('冷冻豆腐皮', '郑州冷冻仓库', '杭州粮油贸易公司', 'add', '紧急补货', 2000, '2025-04-05 12:00:00'),

('冷藏酸奶', '郑州冷藏仓库', '北京绿野蔬菜供应有限公司', 'add', '初次入库', 900, '2025-04-01 08:00:00'),
('冷藏酸奶', '郑州冷藏仓库', '北京绿野蔬菜供应有限公司', 'sub', '销售出库', 300, '2025-04-02 09:00:00'),
('冷藏酸奶', '郑州冷藏仓库', '北京绿野蔬菜供应有限公司', 'add', '补货入库', 600, '2025-04-03 10:00:00'),
('冷藏酸奶', '郑州冷藏仓库', '北京绿野蔬菜供应有限公司', 'sub', '损坏报废', 100, '2025-04-04 11:00:00'),
('冷藏酸奶', '郑州冷藏仓库', '北京绿野蔬菜供应有限公司', 'add', '紧急补货', 300, '2025-04-05 12:00:00'),

('冷藏牛奶', '郑州冷藏仓库', '上海鲜果配送中心', 'add', '初次入库', 1200, '2025-04-01 08:00:00'),
('冷藏牛奶', '郑州冷藏仓库', '上海鲜果配送中心', 'sub', '销售出库', 400, '2025-04-02 09:00:00'),
('冷藏牛奶', '郑州冷藏仓库', '上海鲜果配送中心', 'add', '补货入库', 600, '2025-04-03 10:00:00'),
('冷藏牛奶', '郑州冷藏仓库', '上海鲜果配送中心', 'sub', '损坏报废', 200, '2025-04-04 11:00:00'),
('冷藏牛奶', '郑州冷藏仓库', '上海鲜果配送中心', 'add', '紧急补货', 400, '2025-04-05 12:00:00'),

('冷藏果汁', '郑州冷藏仓库', '广州肉类批发商行', 'add', '初次入库', 1500, '2025-04-01 08:00:00'),
('冷藏果汁', '郑州冷藏仓库', '广州肉类批发商行', 'sub', '销售出库', 500, '2025-04-02 09:00:00'),
('冷藏果汁', '郑州冷藏仓库', '广州肉类批发商行', 'add', '补货入库', 900, '2025-04-03 10:00:00'),
('冷藏果汁', '郑州冷藏仓库', '广州肉类批发商行', 'sub', '损坏报废', 300, '2025-04-04 11:00:00'),
('冷藏果汁', '郑州冷藏仓库', '广州肉类批发商行', 'add', '紧急补货', 600, '2025-04-05 12:00:00'),

('冷藏橙子', '郑州冷藏仓库', '深圳海鲜市场', 'add', '初次入库', 1800, '2025-04-01 08:00:00'),
('冷藏橙子', '郑州冷藏仓库', '深圳海鲜市场', 'sub', '销售出库', 600, '2025-04-02 09:00:00'),
('冷藏橙子', '郑州冷藏仓库', '深圳海鲜市场', 'add', '补货入库', 1200, '2025-04-03 10:00:00'),
('冷藏橙子', '郑州冷藏仓库', '深圳海鲜市场', 'sub', '损坏报废', 400, '2025-04-04 11:00:00'),
('冷藏橙子', '郑州冷藏仓库', '深圳海鲜市场', 'add', '紧急补货', 800, '2025-04-05 12:00:00'),

('冷藏苹果', '郑州冷藏仓库', '杭州粮油贸易公司', 'add', '初次入库', 2100, '2025-04-01 08:00:00'),
('冷藏苹果', '郑州冷藏仓库', '杭州粮油贸易公司', 'sub', '销售出库', 700, '2025-04-02 09:00:00'),
('冷藏苹果', '郑州冷藏仓库', '杭州粮油贸易公司', 'add', '补货入库', 1400, '2025-04-03 10:00:00'),
('冷藏苹果', '郑州冷藏仓库', '杭州粮油贸易公司', 'sub', '损坏报废', 500, '2025-04-04 11:00:00'),
('冷藏苹果', '郑州冷藏仓库', '杭州粮油贸易公司', 'add', '紧急补货', 1000, '2025-04-05 12:00:00'),

('冷藏草莓', '郑州冷藏仓库', '北京绿野蔬菜供应有限公司', 'add', '初次入库', 2400, '2025-04-01 08:00:00'),
('冷藏草莓', '郑州冷藏仓库', '北京绿野蔬菜供应有限公司', 'sub', '销售出库', 800, '2025-04-02 09:00:00'),
('冷藏草莓', '郑州冷藏仓库', '北京绿野蔬菜供应有限公司', 'add', '补货入库', 1600, '2025-04-03 10:00:00'),
('冷藏草莓', '郑州冷藏仓库', '北京绿野蔬菜供应有限公司', 'sub', '损坏报废', 600, '2025-04-04 11:00:00'),
('冷藏草莓', '郑州冷藏仓库', '北京绿野蔬菜供应有限公司', 'add', '紧急补货', 1200, '2025-04-05 12:00:00'),

('冷藏蓝莓', '郑州冷藏仓库', '上海鲜果配送中心', 'add', '初次入库', 2700, '2025-04-01 08:00:00'),
('冷藏蓝莓', '郑州冷藏仓库', '上海鲜果配送中心', 'sub', '销售出库', 900, '2025-04-02 09:00:00'),
('冷藏蓝莓', '郑州冷藏仓库', '上海鲜果配送中心', 'add', '补货入库', 1800, '2025-04-03 10:00:00'),
('冷藏蓝莓', '郑州冷藏仓库', '上海鲜果配送中心', 'sub', '损坏报废', 700, '2025-04-04 11:00:00'),
('冷藏蓝莓', '郑州冷藏仓库', '上海鲜果配送中心', 'add', '紧急补货', 1500, '2025-04-05 12:00:00'),

('冷藏香蕉', '郑州冷藏仓库', '广州肉类批发商行', 'add', '初次入库', 3000, '2025-04-01 08:00:00'),
('冷藏香蕉', '郑州冷藏仓库', '广州肉类批发商行', 'sub', '销售出库', 1000, '2025-04-02 09:00:00'),
('冷藏香蕉', '郑州冷藏仓库', '广州肉类批发商行', 'add', '补货入库', 2000, '2025-04-03 10:00:00'),
('冷藏香蕉', '郑州冷藏仓库', '广州肉类批发商行', 'sub', '损坏报废', 800, '2025-04-04 11:00:00'),
('冷藏香蕉', '郑州冷藏仓库', '广州肉类批发商行', 'add', '紧急补货', 1600, '2025-04-05 12:00:00'),

('冷藏菠萝', '郑州冷藏仓库', '深圳海鲜市场', 'add', '初次入库', 3300, '2025-04-01 08:00:00'),
('冷藏菠萝', '郑州冷藏仓库', '深圳海鲜市场', 'sub', '销售出库', 1100, '2025-04-02 09:00:00'),
('冷藏菠萝', '郑州冷藏仓库', '深圳海鲜市场', 'add', '补货入库', 2200, '2025-04-03 10:00:00'),
('冷藏菠萝', '郑州冷藏仓库', '深圳海鲜市场', 'sub', '损坏报废', 900, '2025-04-04 11:00:00'),
('冷藏菠萝', '郑州冷藏仓库', '深圳海鲜市场', 'add', '紧急补货', 1800, '2025-04-05 12:00:00'),

('冷藏葡萄', '郑州冷藏仓库', '杭州粮油贸易公司', 'add', '初次入库', 3600, '2025-04-01 08:00:00'),
('冷藏葡萄', '郑州冷藏仓库', '杭州粮油贸易公司', 'sub', '销售出库', 1200, '2025-04-02 09:00:00'),
('冷藏葡萄', '郑州冷藏仓库', '杭州粮油贸易公司', 'add', '补货入库', 2400, '2025-04-03 10:00:00'),
('冷藏葡萄', '郑州冷藏仓库', '杭州粮油贸易公司', 'sub', '损坏报废', 1000, '2025-04-04 11:00:00'),
('冷藏葡萄', '郑州冷藏仓库', '杭州粮油贸易公司', 'add', '紧急补货', 2000, '2025-04-05 12:00:00'),

('常温大米', '郑州常温仓库', '北京绿野蔬菜供应有限公司', 'add', '初次入库', 900, '2025-04-01 08:00:00'),
('常温大米', '郑州常温仓库', '北京绿野蔬菜供应有限公司', 'sub', '销售出库', 300, '2025-04-02 09:00:00'),
('常温大米', '郑州常温仓库', '北京绿野蔬菜供应有限公司', 'add', '补货入库', 600, '2025-04-03 10:00:00'),
('常温大米', '郑州常温仓库', '北京绿野蔬菜供应有限公司', 'sub', '损坏报废', 100, '2025-04-04 11:00:00'),
('常温大米', '郑州常温仓库', '北京绿野蔬菜供应有限公司', 'add', '紧急补货', 300, '2025-04-05 12:00:00'),

('常温面粉', '郑州常温仓库', '上海鲜果配送中心', 'add', '初次入库', 1200, '2025-04-01 08:00:00'),
('常温面粉', '郑州常温仓库', '上海鲜果配送中心', 'sub', '销售出库', 400, '2025-04-02 09:00:00'),
('常温面粉', '郑州常温仓库', '上海鲜果配送中心', 'add', '补货入库', 600, '2025-04-03 10:00:00'),
('常温面粉', '郑州常温仓库', '上海鲜果配送中心', 'sub', '损坏报废', 200, '2025-04-04 11:00:00'),
('常温面粉', '郑州常温仓库', '上海鲜果配送中心', 'add', '紧急补货', 400, '2025-04-05 12:00:00'),

('常温食用油', '郑州常温仓库', '广州肉类批发商行', 'add', '初次入库', 1500, '2025-04-01 08:00:00'),
('常温食用油', '郑州常温仓库', '广州肉类批发商行', 'sub', '销售出库', 500, '2025-04-02 09:00:00'),
('常温食用油', '郑州常温仓库', '广州肉类批发商行', 'add', '补货入库', 900, '2025-04-03 10:00:00'),
('常温食用油', '郑州常温仓库', '广州肉类批发商行', 'sub', '损坏报废', 300, '2025-04-04 11:00:00'),
('常温食用油', '郑州常温仓库', '广州肉类批发商行', 'add', '紧急补货', 600, '2025-04-05 12:00:00'),

('常温酱油', '郑州常温仓库', '深圳海鲜市场', 'add', '初次入库', 1800, '2025-04-01 08:00:00'),
('常温酱油', '郑州常温仓库', '深圳海鲜市场', 'sub', '销售出库', 600, '2025-04-02 09:00:00'),
('常温酱油', '郑州常温仓库', '深圳海鲜市场', 'add', '补货入库', 1200, '2025-04-03 10:00:00'),
('常温酱油', '郑州常温仓库', '深圳海鲜市场', 'sub', '损坏报废', 400, '2025-04-04 11:00:00'),
('常温酱油', '郑州常温仓库', '深圳海鲜市场', 'add', '紧急补货', 800, '2025-04-05 12:00:00'),

('常温醋', '郑州常温仓库', '杭州粮油贸易公司', 'add', '初次入库', 2100, '2025-04-01 08:00:00'),
('常温醋', '郑州常温仓库', '杭州粮油贸易公司', 'sub', '销售出库', 700, '2025-04-02 09:00:00'),
('常温醋', '郑州常温仓库', '杭州粮油贸易公司', 'add', '补货入库', 1400, '2025-04-03 10:00:00'),
('常温醋', '郑州常温仓库', '杭州粮油贸易公司', 'sub', '损坏报废', 500, '2025-04-04 11:00:00'),
('常温醋', '郑州常温仓库', '杭州粮油贸易公司', 'add', '紧急补货', 1000, '2025-04-05 12:00:00'),

('常温白糖', '郑州干货仓库', '北京绿野蔬菜供应有限公司', 'add', '初次入库', 2400, '2025-04-01 08:00:00'),
('常温白糖', '郑州干货仓库', '北京绿野蔬菜供应有限公司', 'sub', '销售出库', 800, '2025-04-02 09:00:00'),
('常温白糖', '郑州干货仓库', '北京绿野蔬菜供应有限公司', 'add', '补货入库', 1600, '2025-04-03 10:00:00'),
('常温白糖', '郑州干货仓库', '北京绿野蔬菜供应有限公司', 'sub', '损坏报废', 600, '2025-04-04 11:00:00'),
('常温白糖', '郑州干货仓库', '北京绿野蔬菜供应有限公司', 'add', '紧急补货', 1200, '2025-04-05 12:00:00'),

('常温盐', '郑州干货仓库', '上海鲜果配送中心', 'add', '初次入库', 2700, '2025-04-01 08:00:00'),
('常温盐', '郑州干货仓库', '上海鲜果配送中心', 'sub', '销售出库', 900, '2025-04-02 09:00:00'),
('常温盐', '郑州干货仓库', '上海鲜果配送中心', 'add', '补货入库', 1800, '2025-04-03 10:00:00'),
('常温盐', '郑州干货仓库', '上海鲜果配送中心', 'sub', '损坏报废', 700, '2025-04-04 11:00:00'),
('常温盐', '郑州干货仓库', '上海鲜果配送中心', 'add', '紧急补货', 1500, '2025-04-05 12:00:00'),

('常温味精', '郑州干货仓库', '广州肉类批发商行', 'add', '初次入库', 3000, '2025-04-01 08:00:00'),
('常温味精', '郑州干货仓库', '广州肉类批发商行', 'sub', '销售出库', 1000, '2025-04-02 09:00:00'),
('常温味精', '郑州干货仓库', '广州肉类批发商行', 'add', '补货入库', 2000, '2025-04-03 10:00:00'),
('常温味精', '郑州干货仓库', '广州肉类批发商行', 'sub', '损坏报废', 800, '2025-04-04 11:00:00'),
('常温味精', '郑州干货仓库', '广州肉类批发商行', 'add', '紧急补货', 1600, '2025-04-05 12:00:00'),

('常温花椒', '郑州干货仓库', '深圳海鲜市场', 'add', '初次入库', 3300, '2025-04-01 08:00:00'),
('常温花椒', '郑州干货仓库', '深圳海鲜市场', 'sub', '销售出库', 1100, '2025-04-02 09:00:00'),
('常温花椒', '郑州干货仓库', '深圳海鲜市场', 'add', '补货入库', 2200, '2025-04-03 10:00:00'),
('常温花椒', '郑州干货仓库', '深圳海鲜市场', 'sub', '损坏报废', 900, '2025-04-04 11:00:00'),
('常温花椒', '郑州干货仓库', '深圳海鲜市场', 'add', '紧急补货', 1800, '2025-04-05 12:00:00'),

('常温辣椒粉', '郑州干货仓库', '杭州粮油贸易公司', 'add', '初次入库', 3600, '2025-04-01 08:00:00'),
('常温辣椒粉', '郑州干货仓库', '杭州粮油贸易公司', 'sub', '销售出库', 1200, '2025-04-02 09:00:00'),
('常温辣椒粉', '郑州干货仓库', '杭州粮油贸易公司', 'add', '补货入库', 2400, '2025-04-03 10:00:00'),
('常温辣椒粉', '郑州干货仓库', '杭州粮油贸易公司', 'sub', '损坏报废', 1000, '2025-04-04 11:00:00'),
('常温辣椒粉', '郑州干货仓库', '杭州粮油贸易公司', 'add', '紧急补货', 2000, '2025-04-05 12:00:00'),

('常温黑胡椒', '郑州干货仓库', '北京绿野蔬菜供应有限公司', 'add', '初次入库', 900, '2025-04-01 08:00:00'),
('常温黑胡椒', '郑州干货仓库', '北京绿野蔬菜供应有限公司', 'sub', '销售出库', 300, '2025-04-02 09:00:00');


-- 创建任务表(tb_task)
CREATE TABLE tb_task (
taskId INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT '任务ID',
taskName VARCHAR(50) NOT NULL COMMENT '任务名称',
taskDescription VARCHAR(500) COMMENT '任务描述',
taskStatus VARCHAR(20) COMMENT '任务状态',
taskType VARCHAR(10) COMMENT '任务类型',
createTime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
updateTime DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
endTime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
taskAdminName VARCHAR(50) COMMENT '任务管理员名称',
taskUserNames TEXT COMMENT '执行人员数组'
);

-- 插入20条任务数据
INSERT INTO tb_task (taskName, taskDescription, taskStatus, taskType, createTime, updateTime, endTime, taskAdminName, taskUserNames) VALUES
('入库搬运任务1', '将货物从A仓库搬到B仓库', '进行中', '入库搬运', NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 2 DAY), '张伟', '["刘洋", "陈静", "杨柳"]'),
('入库统计任务1', '统计A仓库的所有货物数量', '待处理', '入库统计', NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 3 DAY), '张伟', '["刘洋"]'),
('出库搬运任务1', '将货物从B仓库搬出去', '已完成', '出库搬运', DATE_SUB(NOW(), INTERVAL 5 DAY), DATE_SUB(NOW(), INTERVAL 4 DAY), DATE_SUB(NOW(), INTERVAL 3 DAY), '李娜', '["周杰", "吴梅", "郑刚"]'),
('出库统计任务1', '统计B仓库的所有货物数量', '进行中', '出库统计', DATE_SUB(NOW(), INTERVAL 7 DAY), DATE_SUB(NOW(), INTERVAL 6 DAY), DATE_SUB(NOW(), INTERVAL 5 DAY), '李娜', '["周杰"]'),
('入库搬运任务2', '将货物从C仓库搬到D仓库', '待处理', '入库搬运', NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 4 DAY), '王强', '["马丽", "黄涛", "朱红"]'),
('入库统计任务2', '统计C仓库的所有货物数量', '进行中', '入库统计', NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 5 DAY), '王强', '["马丽"]'),
('出库搬运任务2', '将货物从D仓库搬出去', '已完成', '出库搬运', DATE_SUB(NOW(), INTERVAL 9 DAY), DATE_SUB(NOW(), INTERVAL 8 DAY), DATE_SUB(NOW(), INTERVAL 7 DAY), '赵敏', '["徐明", "林芳", "宋辉"]'),
('出库统计任务2', '统计D仓库的所有货物数量', '待处理', '出库统计', DATE_SUB(NOW(), INTERVAL 11 DAY), DATE_SUB(NOW(), INTERVAL 10 DAY), DATE_SUB(NOW(), INTERVAL 9 DAY), '赵敏', '["徐明"]'),
('入库搬运任务3', '将货物从E仓库搬到F仓库', '进行中', '入库搬运', NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 6 DAY), '孙丽', '["何军", "郭美", "田亮"]'),
('入库统计任务3', '统计E仓库的所有货物数量', '已完成', '入库统计', NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 7 DAY), '孙丽', '["何军"]'),
('入库搬运任务4', '将货物从G仓库搬到H仓库', '进行中', '入库搬运', NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 8 DAY), '张伟', '["罗艳", "莫勇", "程莉"]'),
('入库统计任务4', '统计G仓库的所有货物数量', '待处理', '入库统计', NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 9 DAY), '张伟', '["罗艳"]'),
('出库搬运任务3', '将货物从H仓库搬出去', '已完成', '出库搬运', DATE_SUB(NOW(), INTERVAL 13 DAY), DATE_SUB(NOW(), INTERVAL 12 DAY), DATE_SUB(NOW(), INTERVAL 11 DAY), '李娜', '["潘华", "戴强", "齐晓"]'),
('出库统计任务3', '统计H仓库的所有货物数量', '进行中', '出库统计', DATE_SUB(NOW(), INTERVAL 15 DAY), DATE_SUB(NOW(), INTERVAL 14 DAY), DATE_SUB(NOW(), INTERVAL 13 DAY), '李娜', '["潘华"]'),
('入库搬运任务5', '将货物从I仓库搬到J仓库', '待处理', '入库搬运', NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 10 DAY), '王强', '["冯洁", "熊飞", "钱丽"]'),
('入库统计任务5', '统计I仓库的所有货物数量', '进行中', '入库统计', NOW(), NOW(), DATE_ADD(NOW(), INTERVAL 11 DAY), '王强', '["冯洁"]'),
('出库搬运任务4', '将货物从J仓库搬出去', '已完成', '出库搬运', DATE_SUB(NOW(), INTERVAL 17 DAY), DATE_SUB(NOW(), INTERVAL 16 DAY), DATE_SUB(NOW(), INTERVAL 15 DAY), '赵敏', '["苏浩", "刘洋", "陈静"]'),
('出库统计任务4', '统计J仓库的所有货物数量', '待处理', '出库统计', DATE_SUB(NOW(), INTERVAL 19 DAY), DATE_SUB(NOW(), INTERVAL 18 DAY), DATE_SUB(NOW(), INTERVAL 17 DAY), '赵敏', '["苏浩"]');

-- 创建审核表
CREATE TABLE tb_examine (
examineId INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT '日志ID',
examineName VARCHAR(200) NOT NULL COMMENT '审核名称',
examineData VARCHAR(500) COMMENT '执行数据',
examineStatus VARCHAR(10) COMMENT '审核状态',
examineType VARCHAR(500) COMMENT '审核类型',
createTime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
updateTime DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间'
);

-- 创建审核任务
INSERT INTO `boke`.`tb_examine`(`examineId`, `examineName`, `examineData`, `examineStatus`, `examineType`, `createTime`, `updateTime`)
VALUES (16, '库存创建', '{\"productName\":\"冷冻豌豆\",\"stashName\":\"郑州冷冻仓库\",\"supplierName\":\"深圳海鲜市场\",\"type\":\"add\",\"quantity\":180}', '0', 'add', NOW(), NOW());

INSERT INTO `boke`.`tb_examine`(`examineId`, `examineName`, `examineData`, `examineStatus`, `examineType`, `createTime`, `updateTime`)
VALUES (17, '库存更新', '{\"productName\":\"冷冻豌豆\",\"stashName\":\"郑州冷冻仓库\",\"supplierName\":\"深圳海鲜市场\",\"type\":\"sub\",\"quantity\":50}', '0', 'sub', NOW(), NOW());

INSERT INTO `boke`.`tb_examine`(`examineId`, `examineName`, `examineData`, `examineStatus`, `examineType`, `createTime`, `updateTime`)
VALUES (18, '库存创建', '{\"productName\":\"冷冻玉米粒\",\"stashName\":\"郑州冷冻仓库\",\"supplierName\":\"杭州粮油贸易公司\",\"type\":\"add\",\"quantity\":180}', '0', 'add', NOW(), NOW());

INSERT INTO `boke`.`tb_examine`(`examineId`, `examineName`, `examineData`, `examineStatus`, `examineType`, `createTime`, `updateTime`)
VALUES (19, '库存更新', '{\"productName\":\"冷冻玉米粒\",\"stashName\":\"郑州冷冻仓库\",\"supplierName\":\"杭州粮油贸易公司\",\"type\":\"sub\",\"quantity\":30}', '0', 'sub', NOW(), NOW());

INSERT INTO `boke`.`tb_examine`(`examineId`, `examineName`, `examineData`, `examineStatus`, `examineType`, `createTime`, `updateTime`)
VALUES (20, '库存创建', '{\"productName\":\"冷冻牛肉片\",\"stashName\":\"郑州冷冻仓库\",\"supplierName\":\"北京绿野蔬菜供应有限公司\",\"type\":\"add\",\"quantity\":180}', '0', 'add', NOW(), NOW());

INSERT INTO `boke`.`tb_examine`(`examineId`, `examineName`, `examineData`, `examineStatus`, `examineType`, `createTime`, `updateTime`)
VALUES (21, '库存更新', '{\"productName\":\"冷冻牛肉片\",\"stashName\":\"郑州冷冻仓库\",\"supplierName\":\"北京绿野蔬菜供应有限公司\",\"type\":\"sub\",\"quantity\":40}', '0', 'sub', NOW(), NOW());

INSERT INTO `boke`.`tb_examine`(`examineId`, `examineName`, `examineData`, `examineStatus`, `examineType`, `createTime`, `updateTime`)
VALUES (22, '库存创建', '{\"productName\":\"冷冻鱼丸\",\"stashName\":\"郑州冷冻仓库\",\"supplierName\":\"上海鲜果配送中心\",\"type\":\"add\",\"quantity\":180}', '0', 'add', NOW(), NOW());

INSERT INTO `boke`.`tb_examine`(`examineId`, `examineName`, `examineData`, `examineStatus`, `examineType`, `createTime`, `updateTime`)
VALUES (23, '库存更新', '{\"productName\":\"冷冻鱼丸\",\"stashName\":\"郑州冷冻仓库\",\"supplierName\":\"上海鲜果配送中心\",\"type\":\"sub\",\"quantity\":20}', '0', 'sub', NOW(), NOW());

INSERT INTO `boke`.`tb_examine`(`examineId`, `examineName`, `examineData`, `examineStatus`, `examineType`, `createTime`, `updateTime`)
VALUES (24, '库存创建', '{\"productName\":\"冷冻青豆\",\"stashName\":\"郑州冷冻仓库\",\"supplierName\":\"广州肉类批发商行\",\"type\":\"add\",\"quantity\":180}', '0', 'add', NOW(), NOW());

INSERT INTO `boke`.`tb_examine`(`examineId`, `examineName`, `examineData`, `examineStatus`, `examineType`, `createTime`, `updateTime`)
VALUES (25, '库存更新', '{\"productName\":\"冷冻青豆\",\"stashName\":\"郑州冷冻仓库\",\"supplierName\":\"广州肉类批发商行\",\"type\":\"sub\",\"quantity\":60}', '0', 'sub', NOW(), NOW());

INSERT INTO `boke`.`tb_examine`(`examineId`, `examineName`, `examineData`, `examineStatus`, `examineType`, `createTime`, `updateTime`)
VALUES (26, '库存创建', '{\"productName\":\"冷冻胡萝卜丁\",\"stashName\":\"郑州冷冻仓库\",\"supplierName\":\"深圳海鲜市场\",\"type\":\"add\",\"quantity\":180}', '0', 'add', NOW(), NOW());

INSERT INTO `boke`.`tb_examine`(`examineId`, `examineName`, `examineData`, `examineStatus`, `examineType`, `createTime`, `updateTime`)
VALUES (27, '库存更新', '{\"productName\":\"冷冻胡萝卜丁\",\"stashName\":\"郑州冷冻仓库\",\"supplierName\":\"深圳海鲜市场\",\"type\":\"sub\",\"quantity\":70}', '0', 'sub', NOW(), NOW());

INSERT INTO `boke`.`tb_examine`(`examineId`, `examineName`, `examineData`, `examineStatus`, `examineType`, `createTime`, `updateTime`)
VALUES (28, '库存创建', '{\"productName\":\"冷冻豆腐皮\",\"stashName\":\"郑州冷冻仓库\",\"supplierName\":\"杭州粮油贸易公司\",\"type\":\"add\",\"quantity\":180}', '0', 'add', NOW(), NOW());

INSERT INTO `boke`.`tb_examine`(`examineId`, `examineName`, `examineData`, `examineStatus`, `examineType`, `createTime`, `updateTime`)
VALUES (29, '库存更新', '{\"productName\":\"冷冻豆腐皮\",\"stashName\":\"郑州冷冻仓库\",\"supplierName\":\"杭州粮油贸易公司\",\"type\":\"sub\",\"quantity\":80}', '0', 'sub', NOW(), NOW());


-- 创建日志表（tb_log）
CREATE TABLE tb_log (
logId INTEGER PRIMARY KEY AUTO_INCREMENT COMMENT '日志ID',
type VARCHAR(20) COMMENT '任务类型',
hisData VARCHAR(2000) NOT NULL COMMENT '历史数据',
carrySQL VARCHAR(500) COMMENT '执行SQL',
updateData VARCHAR(2000) COMMENT '更新数据',
actorName VARCHAR(500) COMMENT '操作人',
actionTime DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间'
);



