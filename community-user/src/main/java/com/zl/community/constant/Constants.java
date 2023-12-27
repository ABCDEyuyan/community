package com.zl.community.constant;

/**
 * @Author : ZL
 */
public class Constants {
    /**
     * 通用常量
     */
    public static class CommonConstant {
        /**
         * 升序
         */
        public static final String SORT_ORDER_ASC = "ascend";

        /**
         * 降序
         */
        public static final String SORT_ORDER_DESC = " descend";
    }

    /**
     * 用户常量
     */
    public static class UserConstant {

//        /**
//         * 用户登录态键
//         */
//        public static final String USER_LOGIN_STATE = "user_login";
//
//        //  region 权限
//
//        /**
//         * 默认角色
//         */
//        public static final String DEFAULT_ROLE = "user";
//
//        /**
//         * 管理员角色
//         */
//        public static final String ADMIN_ROLE = "admin";
//
//        /**
//         * 被封号
//         */
//        public static final String BAN_ROLE = "ban";
//
//        // endregion
        /**
         * 启用/正常/成功
         */
        public static final Integer ENABLE = 0;

        /**
         * 禁用/删除/失败
         */
        public static final Integer DISABLE = 1;

        /**
         * 普通用户
         */
        public static final Integer USER = 0;

        /**
         * 管理员
         */
        public static final Integer ADMIN = 1;

        /**
         * 页面
         */
        public static final Integer PAGE = 1;

        /**
         * 按钮
         */
        public static final Integer BUTTON = 2;

        /**
         * 星号
         */
        public static final String SYMBOL_STAR = "*";

        /**
         * 邮箱符号
         */
        public static final String SYMBOL_EMAIL = "@";

        /**
         * 默认当前页码
         */
        public static final Integer DEFAULT_CURRENT_PAGE = 1;

        /**
         * 默认每页条数
         */
        public static final Integer DEFAULT_PAGE_SIZE = 10;

        /**
         * 匿名用户 用户名
         */
        public static final String ANONYMOUS_NAME = "匿名用户";

        /**
         * 请求头
         */
        public static final String HEADER = "Authorization";

    }

    /**
     * 时间常量
     */
    public static class Date {
        public static final String DATE_PATTERN = "yyyy-MM-dd";
        public static final String TIME_PATTERN = "HH:mm:ss";
        public static final String DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    }

    public static class Other {
        public static final String OPTIONS = "OPTIONS";
    }

    public static class Jwt {
        /**
         * JWT 在 Redis 中保存的key前缀
         */
        public static final String REDIS_JWT_KEY_PREFIX = "security:jwt:";
    }
}
