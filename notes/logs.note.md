2025.2.7-2025.2.8
1，新添加jwt，运行时无法打包，无法运行
2，依赖下载出问题，下载最新版jjwt， 却出现了0.9版本的parse，更换下载镜像源解决
3，pom文件添加<packaging>和jar包打包依赖（实际上并不需要，后来删除，springboot，maven plugin可以代替），问题仍未解决，察觉到是循环依赖问题，猜测是依赖冲突，手动创建并注入解决
4，maven插件错误，手动下载并安装（maven-reporting-api:jar:4.0.0）
5，添加jwt过滤
