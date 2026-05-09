package com.waffle.pancake.service.pg;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.cookie.GlobalCookieManager;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @desc: 动态规划-背包问题测试
 * @author: yixiaoshuang
 * @date: 2023/9/13
 **/
@Service
@Slf4j
public class LclTest {

    public static void main(String[] args) {

    }


    /**
     * 简单背包问题：对于一组不同重量、不可分割的物品，我们需要选择一些装入背包，在满足背包最大重量限制的前提下，背包中物品总重量的最大值是多少呢？
     */
    public static void f1() {

    }

    /**
     * 提货计划模块-列表页
     */
    public List<HttpCookie> pickUpLogin() {
        globalLogin();
        String pickUpUrl = "https://supply-chain.qa.aukeyit.com";

        String url = pickUpUrl + "/pickupPlan/index?microname=物控管理";
        HttpRequest request = HttpUtil.createGet(url);
        request.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        request.setFollowRedirectsCookie(true);
        request.setFollowRedirects(true);
        request.setMaxRedirectCount(10);
        request.execute();
        List<HttpCookie> cookieAllList = GlobalCookieManager.getCookieManager().getCookieStore().getCookies();
        return cookieAllList;
    }

    public void testImportFeedBill(String filePath) throws Exception {
        // 先模拟登录
        pickUpLogin();

        String pickUpUrl = "https://supply-chain.qa.aukeyit.com";

        String url = pickUpUrl + "/pickupPlan/importFeeBill";
        URI uri;
        try {
            uri = new URI(pickUpUrl);
        } catch (Exception e) {
            throw new Exception("url异常");
        }
        List<HttpCookie> cookieList = GlobalCookieManager.getCookieManager().getCookieStore().get(uri);
        HttpRequest request = HttpUtil.createPost(url);
        request.setFollowRedirectsCookie(true);
        request.setFollowRedirects(true);
        request.setMaxRedirectCount(10);

        String boundary = "----WebKitFormBoundary" + UUID.randomUUID();
        String xsrfToken = cookieList.stream().filter(httpCookie -> StrUtil.equalsIgnoreCase(httpCookie.getName(), "XSRF-TOKEN")).map(HttpCookie::getValue).findFirst().orElse("");
        String jsessionid = cookieList.stream().filter(v -> StrUtil.equals("JSESSIONID", v.getName())).map(v -> v.getValue()).findFirst().orElse("");
        HttpResponse response = request
                .header("x-xsrf-token", xsrfToken)
                .header(Header.CONTENT_TYPE, "multipart/form-data; boundary=" + boundary)
                .header("cookie", "ipCheck=false;JSESSIONID=" + jsessionid)
                .form("file", FileUtil.file(filePath))
                .execute();

        log.info("importFeedBill response:{}", JSON.toJSONString(response));
    }

    public List<HttpCookie> globalLogin() {
        String loginUrl = "https://cas.qa.aukeyit.com";
        String loginUsername = "tianchaoqiang@aukeys.com";
        String loginPassword = "0IMxq8lRGORCrUF/PWSsHA==";
        Map<String, Object> param = new HashMap<>();
        param.put("username", loginUsername);
        param.put("password", loginPassword);
        String url = loginUrl + "/login";

        HttpRequest request = HttpUtil.createPost(url);
        request.setFollowRedirectsCookie(true);
        request.setFollowRedirects(true);
        request.setMaxRedirectCount(10);
        HttpResponse response = request.form(param).execute();
        List<HttpCookie> cookies = GlobalCookieManager.getCookieManager().getCookieStore().getCookies();
        log.info("globalLogin response:{}, cookies:{}", JSON.toJSONString(response), JSON.toJSONString(cookies));
        return cookies;
    }


    public void buildUploadStream(String url, String xsrfToken) throws Exception {
        String file1 = "D:\\workspace\\pickup_plan.xlsx";
        String fileName = "pickup_plan.xlsx";

        String newLine = "\r\n";
        String boundaryPrefix = "--";
        String boundary = "----WebKitFormBoundary" + UUID.randomUUID().toString();

        URL postUrl = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) postUrl.openConnection();

        conn.setRequestMethod("POST");
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        conn.setRequestProperty("connection", "Keep-Alive");
        conn.setRequestProperty("Charset", "UTF-8");
        conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        conn.setRequestProperty("X-Xsrf-Token", xsrfToken);

        OutputStream out = conn.getOutputStream();
        StringBuilder sb = new StringBuilder();

        sb.append(boundaryPrefix);
        sb.append(boundary);
        sb.append(newLine);

        sb.append("Content-Disposition: form-data; name=\"file\"; filename=\""
                + fileName + "\"");
        sb.append("Content-Type: application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        sb.append(newLine);
        sb.append(newLine);

        out.write(sb.toString().getBytes());

        File file = new File(file1);
        FileInputStream in = new FileInputStream(file);
        byte[] bufferOut = new byte[1024];
        int bytes = 0;
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        out.write(newLine.getBytes());
        in.close();
        byte[] end_data = (newLine + boundaryPrefix + boundary + boundaryPrefix + newLine)
                .getBytes();
        out.write(end_data);
        out.flush();
        out.close();


    }

}
