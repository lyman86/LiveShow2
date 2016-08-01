package com.lly.app.liveshow.http.url;

/**
 * Created by ly on 2016/6/2.
 */
public class RequestUrl {
    //--
    //https://live.myyll.com/api   正式
    //https://test-live.myyll.com:8089/api.php 测试
    public static final String YLL_HOME = "https://live.myyll.com/api";
    public static final String H5_SHOP = "https://shop.myyll.com/index.php?controller=simple&action=app_login_act";
    public static final String H5_LIVE = "https://live.myyll.com";
    /**
     * 登录
     */
    public static final String YLL_LOGIN = YLL_HOME + "/Public/signin";
    /**
     * 注册
     */
    public static final String YLL_REGISY = YLL_HOME + "/Public/signup";
    /**
     * 忘记密码
     */
    public static final String YLL_RESET = YLL_HOME + "/Public/resetPwd ";
    /**
     * 短信验证码
     */
    public static final String YLL_PHONE_CODE = YLL_HOME + "/Public/sendSignupMobileCode";
    /**
     * 找回短信验证码
     */
    public static final String YLL_PHONE_CODE_RESET = YLL_HOME + "/Public/sendFindPwdMobileCode";
    /**
     * 获取系统消息接口
     */
    public static final String YLL_LIVE_SYSTEMMSG = YLL_HOME + "/notice/getChannelNotice";
    /**
     * 关注直播
     */
    public static final String YLL_LIVE_FOLLOW = YLL_HOME + "/Users/Follow";
    /**
     * 获取礼物数据
     */
    public static final String YLL_LIVE_GIFT = YLL_HOME + "/server/getGift";
    /**
     * 退出登录
     */
    public static final String YLL_LOG_OUT = YLL_HOME +"/Users/logout";
    /**
     * 送礼物接口
     */
    public static final String YLL_LIVE_SENDGIFT = YLL_HOME +"/server/sendGift";
    /**
     * 直播获取观众人员列表
     */
    public static final String YLL_LIVE_VIEWER = YLL_HOME + "/server/getUserInfo";
    /**
     * 更新用户信息
     */
    public static final String YLL_UPDATE_USER = YLL_HOME +"/Users/updateUser";
    /**
     * 获取用户信息
     */
    public static final String YLL_GET_USER_INFO = YLL_HOME +"/Users/getUserInfo ";
    /**
     * 添加个人相册
     */
    public static final String YLL_ADD_PERSONAL_ALBUM = YLL_HOME + "/Users/updatePictures";
    /**
     * 主播状态判断
     */
    public static final String YLL_LIVER_STATE = YLL_HOME + "/server/checkAnchor";
    /**
     * 直播上传封面 标题
     */
    public static final String YLL_LIVE_FILLININFO = YLL_HOME + "/server/sendImage";
    /**
     * 直播间销毁
     */
    public static final String YLL_LIVE_DESTROYROOM = YLL_HOME + "/server/destroyChatroom";
    /**
     * 充值
     */
    public static final String YLL_PAY = YLL_HOME +"/Pay/payLists";
    /**
     * 支付宝充值充值
     */
    public static final String YLL_ALIPAY = YLL_HOME +"/Pay/getChongGoodsOrderNo";
    /**
     * 搜索推荐
     */
    public static final String YLL_SEARCH_RECOMMEND = YLL_HOME +"/Index/getReCommend";
    /**
     * 搜索
     */
    public static final String YLL_SEARCH = YLL_HOME +"/Index/searchFollows";
    /**
     * 首页热门
     */
    public static final String YLL_HOME_HOT =  YLL_HOME + "/server/hot";
    /**
     * 广告页面
     */
    public static final String YLL_ADS = YLL_HOME + "/Index/getAds";
    /**
     * 首页热门关注
     */
    public static final String YLL_HOME_HOT_FLLOW = YLL_HOME + "/server/follow";
    /**
     * 获取会员中心相册列表
     */
    public static final String YLL_USERINFO_USER_ALBUM = YLL_HOME + "/UserList/getAlbums";
    /**
     * 获取会员中心直播列表
     */
    public static final String YLL_CURRENT_VIDEO = YLL_HOME + "/UserList/getLives";
    /**
     * 获取会员中心关注列表
     */
    public static final String YLL_CURRENT_FOLLOW = YLL_HOME + "/UserList/getFollows";
    /**
     * 获取会员中心粉丝列表
     */
    public static final String YLL_CURRNET_FANS = YLL_HOME + "/UserList/getFans";
    /**
     * 首页发现最新直播接口
     */
    public static final String YLL_NEW_VIDEO = YLL_HOME + "/server/findAnchor";
    /**
     * 首页发现达人推荐接口
     */
    public static final String YLL_MASTER_RECOMMEND = YLL_HOME + "/server/findMaster";
    /**
     *  首页发现热门图片接口
     */
    public static final String YLL_HOT_PICTURE = YLL_HOME + "/server/findPicture";
    /**
     * 首页发现热门商品接口
     */
    public static final String YLL_HOT_GOODS = YLL_HOME + "/Index/getHotGoods";
    /**
     * 支付宝支付h5
     */
     public static final String YLL_ZFB_PAY = YLL_HOME + "/Pay/shopLists?user_id=";
    /**
     * 主播认证h5
     */
    public static final String YLL_MASTER_AUTH = YLL_HOME + "/setup/auth.html?userId=";
    /**
     * 获取个人名片接口
     */
    public static final String YLL_PERSONAL_CARD = YLL_HOME + "/Users/getUserProfile";
    /**
     * 获取主播商品接口
     */
    public static  final String YLL_GET_LIVE_GOODS = YLL_HOME + "/shop/getAnchorGoods";
    /**
     * 请求回放接口
     */
    public static final String YLL_GET_RECORD_URL = YLL_HOME + "/server/getUrl";
    /**
     * 商品加入购物车
     */
    public static  final String YLL_ADD_BUYCAR = YLL_HOME + "/shop/addToCart";
    /**
     * 商品规格选择
     */
    public static final String YLL_GET_GOODS_SPECIFICATIONS = YLL_HOME + "/shop/getGoodsInfo";
    /**
     * 获取购物车
     */
    public static final String YLL_GET_BUYCAR = YLL_HOME + "/shop/getCart";
    /**
     * 删除购物车
     */
    public static final String YLL_DEL_BUYCAR = YLL_HOME + "/shop/removeCart";
    /**
     * 商品加入购物车
     */
    public static final String YLL_ADD_TO_CART = YLL_HOME + "/shop/addToCart";
    /**
     * 获取货品规格接口
     */
    public static final String YLL_GET_PRODUCT_SPECIFICATION = YLL_HOME + "/shop/getProduct";
    /**
     * 获取地区接口，省 市 区，街道
     */
    public static final String YLL_GET_AREAR = YLL_HOME + "/Address/getArea";
    /**
     * 去付款接口
     */
    public static final String YLL_GO_PAY = YLL_HOME + "/Order/checkCart";
    /**
     * 添加修改地址接口
     */
    public static final String YLL_ADD_MODIFY_ADDRESS = YLL_HOME + "/Address/addressAction";
    /**
     * 删除地址接口
     */
    public static final String YLL_DELETE_ADDRESS = YLL_HOME + "/Address/addressDel";
    /**
     * 获取地址接口
     */
    public static final String GET_MY_ADDRESS = YLL_HOME + "/Address/getAddress";
    /**
     * 设置默认接口
     */
    public static final String SET_DEFAULT_ADDRESS = YLL_HOME + "/Address/addressDefault";
    /**
     * 确认下单接口
     */
    public static final String SET_ORDER_FINISH = YLL_HOME + "/Order/makeCart";
    /**
     * 获取配送方式接口
     */
    public static final String SET_ORDER_DISPATH = YLL_HOME + "/Order/getDeliveryList";
    /**
     * 获取运费接口
     */
    public static final String SET_ORDER_FREIGHT = YLL_HOME + "/Order/orderDelivery";
    /**
     * 获取支付方式接口
     */
    public static final String SET_ORDER_PAYMENT = YLL_HOME + "/Order/getPaymentList";
    /**
     * 主播守护排行榜
     */
    public static final String GET_GUARD_RANK = YLL_HOME + "/server/guardianRank";
    /**
     * 获取文章分类接口
     */
    public static final String GET_ARTICLE_CONTENT = YLL_HOME + "/Users/getArticleCategory";
    /**
     * 获取相册评论接口
     */
    public static final String GET_ALBUM_COMMENT = YLL_HOME + "/Users/getPicComments";
    /**
     * 获取评论图片接口
     */
    public static final String GET_COMENT_PICTURE = YLL_HOME + "/UserList/getPicture";
    /**
     * 评论照片接口
     */
    public static final String COMMENT_PICTURE = YLL_HOME + "/Users/addPicComment";
    /**
     * 本场守护排行榜
     */
    public static final String CHANNEL_RANK = YLL_HOME + "/server/channelRank";
    /**
     * 潮范h5
     */
    public static final String FASION_H5 = H5_LIVE + "/mobile/article";
    /**
     * 商城h5
     */
    public static final String MALL_H5 = H5_SHOP;
    /**
     * 代付款h5
     */
    public static final String ORDER_WAITE_PAY = "/ucenter/order_waitpay";
    /**
     * 进入主播关店铺h5
     */
    public static final String LIVER_SHOP = "/site/home/id/";
    /**
     * 开店申请h5
     */
    public static final String SIMPLE_SELLER = "https://shop.myyll.com/simple/seller";
    /**
     * 代收货
     */
    public static final String WAIT_COMMIT = "/ucenter/order_waitcommit";
    /**
     * 代评价
     */
    public static final String WAIT_COMMENT = "/ucenter/order_waitcomment";
    /**
     * 售后
     */
    public static final String REFOUNDS = "/ucenter/refunds";
    /**
     * 优惠券
     */
    public static final String RED_PAKECT = "/ucenter/redpacket";
    /**
     * 地址
     */
    public static final String ADDRESS = "/ucenter/address";
    /**
     * 全部订单
     */
    public static final String ALL_ORDER = "/ucenter/order";
    /**
     * 发现商品详情
     */
    public static final String GOODS_INFO = "/site/products/id/";
    /**
     * 通过主播id查主播商铺id
     */
    public static final String YLL_LIVE_SHOP_ID = YLL_HOME + "/shop/getAnchorShop";
    /**
     *点击热门图片的item
     */
    public static final String CLICK_HOME_HOT_ITEM = "/Mobile/Article/detail/id/";



}
