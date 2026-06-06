package com.atguigu.study.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: TODO
 * @Author: wanxuhao
 * @Date: 2026/6/6
 **/
@RestController
public class ChatClientController {
    private final ChatClient chatClient;

    public ChatClientController(@Qualifier("ollamaChatModel") ChatModel chatModel) {
        this.chatClient = ChatClient.builder(chatModel).build();
    }

    @GetMapping("/chatclient/dochat")
    public String doChat(@RequestParam(name = "msg",defaultValue = "你是本地模型还是云端模型") String msg) {
        String content = chatClient.prompt().user(msg).call().content();
        System.out.println(content);
        return content;
    }
}
