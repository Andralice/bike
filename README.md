# bike
与boke对应的后端
表单设计；
1. 用户表：tb_user
   字段：
   userId（主键）:integer自增
   userName（用户名）：string
   password（密码）：string
   createTime（创建时间）：string
   Position(职位): string
   telephone(电话): string
   workPlace(工作地点): string

2. 仓库:tb_stash
   字段：
   stashId（仓库编号）:integer自增
   stashName（仓库名称）：string
   stashAddress（仓库地址）：string
   openTime（仓库编号）:string
   warehouseman（仓库管理员）：string
   stashArea（仓库面积）:string
   shelvesNumber（货架数量）：integer
   stashPicture（仓库图片）：string
   remark（备注）：string
3. 商品:tb_goods
   字段：
   skuID（商品编号）:integer自增
   skuName（商品名称）：string
   skuPicture（商品图片）：string
   skuNumber（商品数量）：integer
   skuType（商品类型）：string
   storeTime（入库时间）：string
   storePlace（入库地点）：string
   remainingTime(剩余时间): string
   warehouseman(仓库管理员): string
   telephone(电话): string
4. 供应商:tb_supplier
   supplierId	INTEGER	供应商ID（主键，自增）	1
   supplierName	VARCHAR(50)	供应商名称（唯一，必填）	"XX食品有限公司"
   contactName	VARCHAR(20)	联系人姓名	"李经理"
   contactPhone	VARCHAR(15)	联系电话（格式校验）	"13800138000"
   email	VARCHAR(50)	邮箱	"service@xxfood.com"
   addressProvince	VARCHAR(20)	省份	"广东省"
   addressCity	VARCHAR(20)	城市	"深圳市"
   addressDistrict	VARCHAR(20)	区/县	"南山区"
   addressDetail	VARCHAR(100)	详细地址	"科技园路1号"
   bankName	VARCHAR(50)	开户银行	"中国银行深圳南山支行"
   bankAccountName	VARCHAR(50)	银行账户名	"XX食品有限公司"
   bankAccountNumber	VARCHAR(30)	银行账号	"6228481234567890123"
   supplyProducts	TEXT	主要供应产品（可存储JSON或逗号分隔）	"冷冻肉类,速冻食品"
   cooperationStatus	TINYINT	合作状态（0-停用，1-启用，默认1）	1
   creditRating	VARCHAR(10)	信用等级（A/B/C/D，可选）	"A"
   startDate	DATETIME	合作开始时间	"2023-01-01 00:00:00"
   remark	TEXT	备注	"优质供应商，交货准时"
   createTime	DATETIME	创建时间（自动填充）	"2023-10-01 10:00:00"
   updateTime	DATETIME	更新时间（自动更新）	"2023-10-05 15:30:00"