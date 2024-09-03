package com.waffle.pancake.service.pg;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.cookie.GlobalCookieManager;
import com.alibaba.fastjson.JSON;

import java.net.HttpCookie;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @desc: 动态规划-背包问题测试
 * @author: yixiaoshuang
 * @date: 2023/9/13
 **/

public class LclTest {

    public static void main(String[] args) {

    }



    /**
     * 简单背包问题：对于一组不同重量、不可分割的物品，我们需要选择一些装入背包，在满足背包最大重量限制的前提下，背包中物品总重量的最大值是多少呢？
     */
    public static void f1() {

    }

    public void testImportFeedBill(String file) throws Exception{
        // 先模拟登录
        globalLogin();
        String pickUpUrl = "https://supply-chain.qa.aukeyit.com";

        String url = pickUpUrl + "/pickupPlan/importFeeBill";
        URI uri;
        try {
            uri = new URI(pickUpUrl);
        } catch (URISyntaxException e) {
            throw new Exception("url异常");
        }
        List<HttpCookie> cookieList = GlobalCookieManager.getCookieManager().getCookieStore().get(uri);
        HttpRequest request = HttpUtil.createPost(url);
        request.setFollowRedirectsCookie(true);
        request.setFollowRedirects(true);
        request.setMaxRedirectCount(10);
        HttpResponse response = request
                .header("X-Xsrf-Token", cookieList.stream().filter(httpCookie -> StrUtil.equals(httpCookie.getName(), "XSRF-TOKEN")).map(HttpCookie::getValue).findFirst().orElse(""))
                .header(Header.CONTENT_TYPE, "multipart/form-data; boundary=----WebKitFormBoundaryVvUXpBAawY0spb5K")
                .form("file", FileUtil.file(file))
                .execute();
        System.out.println("importFeedBill response:"+ JSON.toJSONString(response));
    }

    public List<HttpCookie> globalLogin() {
        String loginUrl = "https://cas.qa.aukeyit.com";
        String loginUsername = "tianchaoqiang@aukeys.com";
        String loginPassword ="0IMxq8lRGORCrUF/PWSsHA==";
        Map<String, Object> param = new HashMap<>();
        param.put("username", loginUsername);
        param.put("password", loginPassword);
        String url = loginUrl + "/login";

        HttpRequest request = HttpUtil.createPost(url);
        request.setFollowRedirectsCookie(true);
        request.setFollowRedirects(true);
        request.setMaxRedirectCount(10);
        HttpResponse response = request.form(param).execute();
        return GlobalCookieManager.getCookieManager().getCookieStore().getCookies();
    }
}
