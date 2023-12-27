权限
spring security
jwt
rbac
redis存储

log日志

mybatis-plius

druid连接池

beanutils

hutuUtils java对象转map 


# 主流框架 & 特性

- Spring Boot 2.7.2
- Spring MVC
- MyBatis + MyBatis Plus 数据访问（开启分页）
- Spring Boot 调试工具和项目处理器
- Spring AOP 切面编程
- Spring Scheduler 定时任务
- Spring 事务注解

# 数据存储

- MySQL 数据库
- Redis 内存数据库
- Elasticsearch 搜索引擎
- minio对象存储

# 工具类

- Easy Excel 表格处理
- jackson解析库
- Apache Commons Lang3 工具类
- Lombok 注解

# 业务特性

- Spring Session Redis 分布式登录
- 全局请求响应拦截器（记录日志）
- 全局异常处理器
- 自定义错误码
- 封装通用响应类
- Swagger + Knife4j 接口文档
- 自定义权限注解 + 全局校验
- 全局跨域处理
- 长整数丢失精度解决
- 多环境配置

# 业务功能

- 提供示例 SQL（用户、帖子、帖子点赞、帖子收藏表）
- 用户登录、注册、注销、更新、检索、权限管理
- 帖子创建、删除、编辑、更新、数据库检索、ES 灵活检索
- 帖子点赞、取消点赞
- 帖子收藏、取消收藏、检索已收藏帖子
- 帖子全量同步 ES、增量同步 ES 定时任务
- 支持分业务的文件上传

spring security的简单原理：
使用众多的拦截器对url拦截，以此来管理权限。但是这么多拦截器,主要讲里面核心流程的两个.
首先，权限管理离不开登陆验证的，所以登陆验证拦截器AuthenticationProcessingFilter要讲；
还有就是对访问的资源管理吧，所以资源管理拦截器AbstractSecurityInterceptor要讲；

但拦截器里面的实现需要一些组件来实现，所以就有了AuthenticationManager、accessDecisionManager等组件来支撑。

现在先大概过一遍整个流程，
用户登陆，会被AuthenticationProcessingFilter拦截(即认证管理)，调用AuthenticationManager的实现，而且AuthenticationManager会调用ProviderManager来获取用户验证信息（不同的Provider调用的服务不同，因为这些信息可以是在数据库上，可以是在LDAP服务器上，可以是xml配置文件上等），
如果验证通过后会将用户的权限信息封装一个User放到spring的全局缓存SecurityContextHolder中，以备后面访问资源时使用。

访问资源（即授权管理），访问url时，会通过AbstractSecurityInterceptor拦截器拦截，
其中会调用FilterInvocationSecurityMetadataSource的方法来获取被拦截url所需的全部权限，
在调用授权管理器AccessDecisionManager，这个授权管理器会通过spring的全局缓存SecurityContextHolder获取用户的权限信息，还会获取被拦截的url和被拦截url所需的全部权限，然后根据所配的策略（有：一票决定，一票否定，少数服从多数等），如果权限足够，则返回，权限不够则报错并调用权限不足页面。

数据库表设计
数据库表为5个分别是： 权限表、角色表、用户表、用户角色关系表、角色权限关系表

//java.util.Optional 是 Java 8 中引入的一个类，用于表示一个值可能存在或不存在的情况。在 Java 之前，通常使用 null 值来表示一个值不存在，但是使用 null 值会带来一些问题，如空指针异常等。

Optional 类通过提供一组 API 来解决这个问题，可以让我们更加优雅地处理可能为空的值。它有以下几个主要的方法：

of(T value)：创建一个非空的 Optional 对象，如果传入的值为 null，则会抛出 NullPointerException 异常。
ofNullable(T value)：创建一个 Optional 对象，如果传入的值为 null，则返回一个空的 Optional 对象。
isEmpty()：判断 Optional 对象是否为空。
get()：获取 Optional 对象中的值，如果对象为空，则会抛出 NoSuchElementException 异常。
orElse(T other)：获取 Optional 对象中的值，如果对象为空，则会返回传入的默认值。
orElseGet(Supplier<? extends T> other)：获取 Optional 对象中的值，如果对象为空，则会执行传入的 Supplier 并返回其结果。
orElseThrow(Supplier<? extends X> exceptionSupplier)：获取 Optional 对象中的值，如果对象为空，则会抛出传入的异常。
ifPresent(Consumer<? super T> consumer)：如果 Optional 对象不为空，则执行传入的 Consumer。
通过使用 Optional 类，我们可以更好地处理可能为空的值，并且可以避免出现空指针异常等问题。

角色唯一标识 ---一个用户有多个角色 select distinct 排除相同的url 

业务异常需要捕获-----------

校验以及异常捕获

验证码

es

websocket

minio 视频 热榜


