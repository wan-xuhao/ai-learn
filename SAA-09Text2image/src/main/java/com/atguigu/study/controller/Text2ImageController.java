package com.atguigu.study.controller;

import com.alibaba.cloud.ai.dashscope.image.DashScopeImageOptions;
import jakarta.annotation.Resource;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: https://help.aliyun.com/zh/model-studio/text-to-image?spm=a2c4g.11186623.help-menu-2400256.d_0_5_0.1a457d9dv6o7Kc&accounttraceid=6ec3bf09599f424a91a2a88b27b31570nrdd
 * @Author: wanxuhao
 * @Date: 2026/6/7
 **/
@RestController
public class Text2ImageController {
    // img model
    public static final String IMAGE_MODEL = "wanx2.1-t2i-turbo";

    @Resource
    private ImageModel imageModel;


    /**
     * http://localhost:8009/t2i/image?prompt=刺猬
     * @param prompt
     * @return
     */
    @GetMapping(value = "/t2i/image")
    public String image(@RequestParam(name = "prompt",defaultValue = "刺猬") String prompt) {
        return imageModel.call(new ImagePrompt(prompt, DashScopeImageOptions.builder().withModel(IMAGE_MODEL).build()))
                .getResult().getOutput().getUrl();
    }

}
