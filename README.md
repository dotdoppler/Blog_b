## v2
[Blog](https://github.com/dotdoppler/Blog)的另一个版本，使用MySQL作为数据库,持久层使用MyBatis进行重构。

Another version of Blog, refactoring persistence layer by using [MyBatis](http://www.mybatis.org/mybatis-3/zh/index.html) + MySQL

### Todo List
* 更新、 删除 Post
* 添加、删除、更新标签
* 优化SQL语句，使用mybatis动态SQL
* 重构，Service层抛异常，Controller层捕获处理异常
* 数据库命名 小写+下划线 如 post_status