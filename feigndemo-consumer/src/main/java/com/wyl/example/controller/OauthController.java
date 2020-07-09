package com.wyl.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.util.Arrays;

@RestController
@RequestMapping("/oauth")
public class OauthController {

    private static final Logger logger = LoggerFactory.getLogger(ConfigController.class);

    @GetMapping("/get")
    public String findDistrictGrade(String app_id, String app_auth_code) throws Exception {
        logger.info("appId:{}", app_id);
        logger.info("app_auth_code:{}", app_auth_code);
        logger.info("收到请求啦");
        return "config get";
    }

    //    public static void main(String[] args) {
//        String result = "http://zfb.jiayijiaoyu.cn:9898/oauth/get?app_id=2021001162633169&source=alipay_app_auth&app_auth_code=2131a4f741e84996bed21d17007a7X24";
//        System.out.println(URLEncoder.encode("http://zfb.jiayijiaoyu.cn:9898/oauth/get"));
//        String url = "https://openauth.alipay.com/oauth2/appToAppAuth.htm?app_id=2021001162633169&redirect_uri=http%3A%2F%2Fzfb.jiayijiaoyu.cn%3A9898%2Foauth%2Fget";
//
//
//        String newAuth = "http://zfb.jiayijiaoyu.cn:9898/oauth/get?app_id=2021001162633169&source=alipay_app_auth&app_auth_code=bb057273832f4dc98968d8749de5bE11";
//    }
    public static void main(String[] args) {
        System.out.println(URLEncoder.encode("http://zfb.jiayijiaoyu.cn:18779/zfb.html?app_id=2021001162633169&source=alipay_app_auth&app_auth_code=bb057273832f4dc98968d8749de5bE11"));
//        String str = "leetcodeisgreat";
//        for (int i = 0;i<str.length();i++){
//            char ch = str.charAt(i);
//            switch (ch){
//                case 'a': break;
//                case 'e': break;
//                case 'i': break;
//                case 'o': break;
//                case 'u': break;
//                default: break;
//            }
//        }
    }

    public int findTheLongestSubstring(String s) {
        int n = s.length();
        int[] pos = new int[1 << 5];
        Arrays.fill(pos, -1);
        int ans = 0, status = 0;
        pos[0] = 0;
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == 'a') {
                status ^= (1 << 0);
            } else if (ch == 'e') {
                status ^= (1 << 1);
            } else if (ch == 'i') {
                status ^= (1 << 2);
            } else if (ch == 'o') {
                status ^= (1 << 3);
            } else if (ch == 'u') {
                status ^= (1 << 4);
            }
            if (pos[status] >= 0) {
                ans = Math.max(ans, i + 1 - pos[status]);
            } else {
                pos[status] = i + 1;
            }
        }
        return ans;
    }

}
