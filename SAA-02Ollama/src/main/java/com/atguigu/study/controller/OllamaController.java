package com.atguigu.study.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @Description: TODO
 * @Author: wanxuhao
 * @Date: 2026/6/6
 **/
@RestController
public class OllamaController {

    @Resource
    @Qualifier("ollamaChatModel")
    private ChatModel chatModel;


    @GetMapping("/ollama/chat")
    public String chat(@RequestParam(name = "msg",defaultValue = "你是谁") String msg)
    {
        String result = chatModel.call(msg);
        System.out.println("---结果：" + result);
        return result;
    }

    @GetMapping("/ollama/streamChat")
    public Flux<String> streamChat(@RequestParam(name = "msg",defaultValue = "你是谁") String msg)
    {
        Flux<String> stream = chatModel.stream(msg);
        System.out.println("---结果：" + stream);
        return stream;
    }
}
