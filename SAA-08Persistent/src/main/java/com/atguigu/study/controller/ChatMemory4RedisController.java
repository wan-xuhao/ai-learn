package com.atguigu.study.controller;

import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import static org.springframework.ai.chat.memory.ChatMemory.CONVERSATION_ID;

/**
 * @auther zzyybs@126.com
 * @create 2025-07-28 18:40
 * @Description TODO
 */
@RestController
public class ChatMemory4RedisController
{
    @Resource(name = "qwenChatClient")
    private ChatClient qwenChatClient;

    /**
     * http://localhost:8008/chatmemory/chat?msg=1加2&userId=1
     * zzyybs@126.com
     * @param msg
     * @param userId
     * @return
     */
    @GetMapping("/chatmemory/chat")
    public String chat(String msg, String userId)
    {
        /*return qwenChatClient.prompt(msg).advisors(new Consumer<ChatClient.AdvisorSpec>()
        {
            @Override
            public void accept(ChatClient.AdvisorSpec advisorSpec)
            {
                advisorSpec.param(CONVERSATION_ID, userId);
            }
        }).call().content();*/


        return qwenChatClient
                .prompt(msg)
                .advisors(advisorSpec -> advisorSpec.param(CONVERSATION_ID, userId))
                .call()
                .content();

    }

    /**
     * http://localhost:8008/chatmemory/chat2?msg=再加12&userId=1
     * zzyybs@126.com
     * @param msg
     * @param userId
     * @return
     */
    @GetMapping("/chatmemory/chat2")
    public Flux<String> chat2(String msg, String userId)
    {
        /*return qwenChatClient.prompt(msg).advisors(new Consumer<ChatClient.AdvisorSpec>()
        {
            @Override
            public void accept(ChatClient.AdvisorSpec advisorSpec)
            {
                advisorSpec.param(CONVERSATION_ID, userId);
            }
        }).call().content();*/


        return qwenChatClient.prompt()
                .user(msg)
                .advisors(advisorSpec -> advisorSpec.param(CONVERSATION_ID, userId))
                .stream().content();

    }
}
