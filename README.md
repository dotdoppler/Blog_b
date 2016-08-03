## v2
[Blog](https://github.com/dotdoppler/Blog)的另一个版本，使用MySQL作为数据库,持久层使用MyBatis进行重构。

Another version of Blog, refactoring persistence layer by using [MyBatis](http://www.mybatis.org/mybatis-3/zh/index.html) + MySQL

## 部署使用

使用前请先安装MySQL并执行blog.sql

#### 使用Maven
    
将项目clone至本地,在项目路径下，执行 

    mvn package
运行（java 8） 

    java -jar Blog-b 1.0-SNAPSHOT.jar
    

## Deployment

please install MySQL and execute blog.sql 

#### Maven
maven package
    
    mvn package
run it（java 8） 

    java -jar Blog-b 1.0-SNAPSHOT.jar 
    

### Todo List
- [x] 添加、更新、 删除 Post
- [x] 添加、删除、更新标签
- [x] 重构，Service层抛异常，Controller层捕获处理异常
- [x] 数据库命名 小写+下划线 如 post_status