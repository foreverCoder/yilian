package com.haili.living.utils;

public class InterfaceUtils {
	public static String RESULT_SUCCESS = "1";

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

	public static String getResponseResult(String result) {
		return UnicodeUtils.decodeUnicode(result);
	}

	private static String getBaseURI() {
		return BASE_URI;
	}
}
