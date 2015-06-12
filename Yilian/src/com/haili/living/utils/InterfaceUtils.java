package com.haili.living.utils;

public class InterfaceUtils {
	public static String RESULT_SUCCESS = "1";

	/*
	 * 生活馆图片类型
	 */
	public static class ShopPicType {
		public static String MORNING_SPECIAL = "1";// 早市特卖
		public static String KEY_MORNING_SPECIAL = "APP_zaoshi";// 早市特卖map键
		public static String EVENING_SPECIAL = "2";// 晚市特卖
		public static String KEY_EVENING_SPECIAL = "APP_wanshi";// 晚市特卖map键
		public static String DINNER_OUT = "3,4,5";// 餐饮外卖
		public static String KEY_DINNER_OUT = "APP_canyin";// 餐饮外卖map键
		public static String TODAY_NEW = "0";// 今日新品特卖
		public static String KEY_TODAY_NEW = "APP_temai";// 今日新品特卖map键
		public static String GROUP_BUY = "";// 团购
		public static String KEY_GROUP_BUY = "APP_tuangou";// 团购map键
	}

	/*
	 * 排序类型
	 */
	public static class SortStyle {
		public static String DEFAULT = "0";// 默认
		public static String SALES = "1";// 销量
		public static String MULTIPLE = "2";// 综合 || 人气
		public static String PRICE = "3";// 价格
	}

	/*
	 * 排序方向
	 */
	public static class SortDirect {
		public static String POSITIVE = "1";// 正序
		public static String REVERSE = "2";// 倒序
	}

	/*
	 * 商城类型 搜索地图店铺使用
	 */
	public static class ShopType {
		public static String MALL = "1";// 商城
		public static String LIVE = "2";// 生活馆
		public static String FARM = "3";// 农庄
	}

	/*
	 * 商品类型 搜索商品分类传入
	 */
	public static class GoodType {
		public static String ALL = "1";// 全部分类
		public static String HAPPY_FARM = "3";// 开心农庄分类
	}

	private static String BASE_URI = "http://www.zq2014.com/haili/mobile/index.php?";
	private static String MOBILE_PATH = "";

	public static String getGroupBuyList(){
		return getBaseURI() + "act=life&op=groupbuy_list";
	}
	/**
	 * 常见问题 index.php?act=life&op=life_question
	 */
	public static String getQuestionAndAnswer() {
		return getBaseURI() + "act=life&op=life_question";
	}

	/**
	 * 接口：index.php?act=life&op=goods_body 传值：goods_id(商品id) 返回：
	 * 
	 * 
	 * "result": 1表示正常，0表示空值。-1表示异常
	 * 
	 * "goods_body" : 图片地址
	 */
	public static String getGoodBody() {
		return getBaseURI() + "act=life&op=goods_body";
	}

	/*
	 * 18、获取全部评价、好评、中评、差评数量 19、获取分类评价下的评价列表 act=goods&op=goods_comment
	 * POST数据：goods_id
	 * 
	 * 
	 * 
	 * 返回：
	 * 
	 * "result": "1" 返回值正常
	 * 
	 * all 评价个数 goods 好评个数 normal 中评个数 bad 差评个数
	 * 
	 * good_percent 好评% normal_percent 中评% bad_percent 差评%
	 */
	public static String getGoodEvaluation() {
		return getBaseURI() + "act=goods&op=goods_comment";
	}

	/*
	 * 14、商品加入购物车 act=member_cart&op=cart_add POST数据参数： goods_id 商品ID quantity
	 * 数量 返回 "result": 1表示正常，0表示程序执行未插入数据库，-1表示异常 "datas" : 返回状态，成功返回
	 * 1，异常返回空值，0表示为POST传goods_id不存在
	 */
	public static String addGoodToCart() {
		return getBaseURI() + "act=member_cart&op=cart_add";
	}

	/*
	 * act=goods&op=goods_detail POST数据参数：goods_id 返回数据如下：
	 * 
	 * goods_info为指定商品的详细信息 goods_commend_list 为同类推荐的商品 goods_commonid 商品公共表id
	 * goods_name 商品名称 gc_id 商品分类id store_id 商铺ID store_name 商铺名 farm_type
	 * 农庄商品的类型，1生成验证码，2为农作物，3为养殖，4为发物流 life_type
	 * 生活馆类别,1为早市特卖,2为晚市特卖,3为餐饮外卖-早餐，4为餐饮外卖-午餐，5为餐饮外卖-晚餐 goods_image 商品图片
	 * goods_price 商品价格 goods_marketprice 市场价 goods_commend 商品推荐 1是，0否 默认0
	 * goods_id 商品ID goods_click 商品点击数量 goods_storage 商品库存 goods_salenum 销售数量
	 * goods_collect 收藏数量 evaluation_good_star 好评星级 evaluation_count 评价数
	 * goods_id 商品ID goods_name 商品名称 goods_price 商品价格 goods_image_url 商品图片
	 */
	public static String getTheGoodInfoAndRecommends() {
		return getBaseURI() + "act=goods&op=goods_detail";
	}

	/*
	 * 6. 获取指定商品的图片列表 接口：act=life&op=the_goods POST传值：
	 * 需要传一个商品的$goods_id值，$goods_id是需要查看商品所对应的id值。
	 * 
	 * 返回值：为指定商品的图片
	 * 
	 * "result": 1表示正常，0表示没有值，-1表示异常
	 * 
	 * goods_image 图片地址
	 */
	public static String getTheGoodImgs() {
		return getBaseURI() + "act=life&op=the_goods";
	}

	/*
	 * 5. 分类固定写死八个分类，获取每个分类下的商品列表，带分页
	 * 
	 * 接口：act=life&op=life_classify
	 * 
	 * POST传值： 需要同时传$gc_id和$store_id两个值，$gc_id是需要查看分类所对应的id值，
	 * $store_id是需要查看店铺的id值。
	 * 
	 * 返回值：分类固定写死八个分类，获取每个分类下的商品列表，带分页
	 * 
	 * "result": 1表示正常，0表示没有值，-1表示异常
	 * 
	 * goods_id 商品id(SKU)
	 * 
	 * goods_commonid 商品公共表id
	 * 
	 * goods_name 商品名称
	 * 
	 * store_id 店铺id
	 * 
	 * store_name 店铺名称
	 * 
	 * gc_id 分类id
	 * 
	 * goods_price 商品价格
	 * 
	 * goods_image 图片地址
	 * 
	 * farm_type 农庄商品的类型，1生成验证码，2为农作物，3为养殖
	 */
	public static String getGoodListByShopClass() {
		return getBaseURI() + "act=life&op=life_classify";
	}

	/**
	 * @return 9、生活馆搜索商品的列表信息 接口：act=life&op=life_goods
	 *         POST传状态值：传搜索生活馆商品信息名称$life_search（如：传值，蔬菜。。。）分页传值，
	 * 
	 *         分页： curpage 第几页 默认为5条 例如：&curpage=3
	 * 
	 * 
	 *         {"code":200,"result":"1","hasmore":false,"page_total":1,"datas":[
	 *         {"goods_id":"43","goods_commonid":"20","goods_name":"测试生活馆2",
	 *         "store_id"
	 *         :"11","store_name":"生活馆大学城店","gc_id":"1217","goods_price"
	 *         :"11.00","goods_image":
	 *         "http://www.zq2014.com/haili/data/upload/shop/store/goods/11/11_04805073382771374_360.jpg"
	 *         ,"farm_type":null}]}
	 */
	public static String getShopSearchGoods() {
		return getBaseURI() + "act=life&op=life_goods";
	}

	/*
	 * 6. 获取当前生活馆的信息，如原型图 接口：act=life&op= index POST传值：
	 * 需要传一个商家的$store_id值，$store_id是需要查看商家信息所对应的id值。
	 */
	public static String getLiveShopInfo() {
		return getBaseURI() + "act=life&op=index";
	}

	/**
	 * 7. 获取今日新品、餐饮、早市特卖、晚市特卖的图片 接口：act=life&op= life_images 返回：
	 * 获取今日新品、餐饮、早市特卖、晚市特卖的图片
	 * APP_canyin(餐饮图片)，APP_temai（特卖）,APP_tuangou(团购),APP_wanshi
	 * (晚市),APP_zaoshi(早市)
	 */
	public static String getLiveShopHeadPic() {
		return getBaseURI() + "act=life&op=life_images";
	}

	/**
	 * 8.获取今日新品的商品列表 接口：act=life&op= life_new POST传值：
	 * 需要传一个商家的$store_id值，$store_id是需要查看商家所对应的id值。
	 */
	public static String getGoodListByPicType() {
		return getBaseURI() + "act=life&op= life_new";
	}

	/*
	 * 1、获取用户当前位置 3公里内的生活馆信息
	 * http://qxu1193880138.my3w.com/haili/mobile/index.php
	 * ?act=life&op=mapgetlife POST传值 经度 参数：lng 纬度参数：lat 返回： [store_id] 店铺ID
	 * [store_name] 店铺名 [type_id] 店铺类型 1商城 2，生活馆；3农庄 [maplnglat] 店铺的经纬度
	 * 117.18365190,31.81160903
	 */
	public static String getLbsShops() {
		return getBaseURI() + "act=life&op=mapgetlife";
	}

	/*
	 * 2、获取当前位置最近的生活馆信息
	 * http://www.zq2014.com/haili/mobile/index.php?act=life&op=mapnearbylife
	 * POST传值 经度 参数：lng 纬度参数：lat 返回： [store_id] 生活馆店铺ID [store_name] 生活馆店铺名
	 * [type_id] 店铺类型 1商城 2，生活馆；3农庄 [maplnglat] 生活馆店铺的经纬度 117.259462,31.860109
	 */
	public static String getNearShop() {
		return getBaseURI() + "act=life&op=mapnearbylife";
	}

	/*
	 * 1.获取商品的所有分类，包括农庄和商城
	 * 接口：http://www.zq2014.com/haili/mobile/index.php?act=classify&op=index
	 * POST传状态值：状态值style为1表示全部分类值，3表示开心农庄分类值
	 */
	public static String getGoodClassify() {
		return getBaseURI() + "act=classify&op=index";
	}

	/*
	 * 2.获取某个分类下排序后(缺省综合、销量、价格、距离)的商品列表，带分页
	 * 接口：http://www.zq2014.com/haili/mobile/index.php?act=classify&op=class
	 * POST传值： $key是排序类型（比如2综合、1销量、3价格)，
	 * $order是排序方向（1为正序，默认倒叙），$gc_id是查询分类的id(比如生活馆的分类gc_id是1211)
	 * &page是一页显示几条信息，&curpage=是请求第几页
	 */
	public static String getGoodListByClassify() {
		return getBaseURI() + "act=classify&op=class";
	}

	/*
	 * 5.搜索商品排序后(缺省综合、销量、价格、距离)的信息，带分页 act=goods&op=goods_search 传参数：GET keyword
	 * 用户搜索框添加的内容；key 为排序方式 0为默认，1为销量，2为人气，3为价格；order 为排序 1为升序，2为降序 分页： curpage
	 * 第几页 pagesize 一页几条信息 不传值默认为5条
	 * &keyword=ddd&key=0&order=0&curpage=3&pagesize=2
	 */
	public static String getSearchGoodList() {
		return getBaseURI() + "act=goods&op=goods_search";
	}

	/**
	 * http://www.zq2014.com/haili/mobile/index.php?act=life&op=getstorelifeaddr
	 * 传值：POST store_id 生活馆店铺ID 返回数据： life_id 配送城市序号 seller_id 卖家id province
	 * 配送省份 city 配送市 district 配送区 street 配送街道
	 */
	public static String getShopDistribution() {
		return getBaseURI() + "act=life&op=getstorelifeaddr";

	}

	/**
	 * http://www.zq2014.com/haili/mobile/index.php?act=life&op=life_new POST传值：
	 * 需要传一个商家的$store_id值，$store_id是需要查看商家所对应的id值,
	 * 需要传状态值$life_type,life_type表示(早市特卖
	 * （life_type=1），晚市特卖(life_type=2），为餐饮外卖-早餐(
	 * life_type=3），4为餐饮外卖-午餐(life_type=
	 * 4），为餐饮外卖-晚餐(life_type=5）,为今日新品（life_type=""）)，
	 * **/
	public static String getGoodsByTodayType() {
		return getBaseURI() + "act=life&op=life_new";

	}

	public static String getResponseResult(String result) {
		return UnicodeUtils.decodeUnicode(result);
	}

	private static String getBaseURI() {
		return BASE_URI;
	}
}
