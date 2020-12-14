package com.allen.upload.constants;

/**
 * <br>
 * <b>功能：通用常量信息</b><br>
 * <b>作者：</b>fengwei@hdvon.com<br>
 * <b>日期：</b> 2019年3月11日 <br>
 * <b>版权所有：<b>广州弘度信息科技有限公司 版权所有(C) 2018<br>
 */
public class Constants {

    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    //目录分隔符
    public static final String FILE_SEPARATOR = "/";

    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 上传目录
     */
    public static final String USER_UPLOAD_HEAD_IMG_DIR = "user";
    /**
     * 工单通知模块常量
     */
    public static final String DICT_NOTICE_WORK_ORDER = "notice_work_order";
    public static final String DICT_NOTICE_WORK_ORDER_ITEM_ATTENTION = "attention";
    public static final String DICT_NOTICE_WORK_ORDER_ITEM_UNTAKEN = "untaken";
    public static final String DICT_NOTICE_WORK_ORDER_ITEM_MODIFIED = "modified";
    public static final String NOTICE_ORDER_SUBSCRIBE_YES = "1";
    public static final String NOTICE_ORDER_SUBSCRIBE_NO = "0";
    public static final String WORK_ORDER_CONSTANT_ORDER_ID = "orderId";
    public static final String WORK_ORDER_CONSTANT_DEPARTMENT_ID = "departmentId";
    public static final String WORK_ORDER_CONSTANT_USER_ID = "userId";
    public static final String WORK_ORDER_CONSTANT_DISPATCH_USERS = "dispatchUsers";
    public static final String TOPIC_AUTO_WORK_ORDER = "autoWorkOrder";

    /**
     * 自动去除表前缀
     */
    public static final String AUTO_REOMVE_PRE = "true";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";

    /**
     * 启用标准
     */
    public static final String ENABLE = "1";


    /**
     * consul中的检测web前缀
     */
    public static final String WEB_KEY_PREFIX = "prometheus/web/";
    /**
     * consul中的检测ping前缀
     */
    public static final String PING_KEY_PREFIX = "prometheus/ping/";


    public static class DistributeSettings {

        private DistributeSettings() {

        }
        /**
         * 字段常量值
         */
        public static final String SEARCH_KEY = "searchKey";
        public static final String RES_ROLE_ID = "resRoleId";
        /**
         * 是否启用
         */
        public static final int ENABLED_OFF = 0;
        public static final int ENABLED_ON = 1;
        /**
         * 方案业务类型
         */
        public static final int BIZ_TYPE_DISTRIBUTE = 2;
        /**
         * 是否展示图标 1-是, 2-否
         */
        public static final int ICON_FLAG_SHOW = 1;
        public static final int ICON_FLAG_HIDDEN = 0;
    }
}
