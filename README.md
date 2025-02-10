# bike
与boke对应的后端
表单设计；
1. 用户表：tb_user
   字段：
   userId（主键）:integer自增
   userName（用户名）：string
   password（密码）：string
2. 仓库:tb_stash
   字段：
   stashId（仓库编号）:integer自增
   stashName（仓库名称）：string
   stashAddress（仓库地址）：string
   openTime（仓库编号）:string
   warehouseman（仓库管理员）：string
   storageTemperature（存储温度）：string
   stashArea（仓库面积）:string
   shelvesNumber（货架数量）：integer
   stashPicture（仓库图片）：string
   remark（备注）：string
3. 货物:tb_goods

4. 供应商:tb_supplier