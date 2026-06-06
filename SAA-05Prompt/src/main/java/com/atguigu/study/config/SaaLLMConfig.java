package com.atguigu.study.config;

import com.alibaba.cloud.ai.dashscope.api.DashScopeApi;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatModel;
import com.alibaba.cloud.ai.dashscope.chat.DashScopeChatOptions;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.ChatOptions;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @auther zzyybs@126.com
 * @create 2025-07-25 18:53
 * @Description ChatModel+ChatClient+多模型共存
 */
@Configuration
public class SaaLLMConfig
{
    // 模型名称常量定义
    private final String DEEPSEEK_MODEL = "deepseek-v3";
    private final String QWEN_MODEL = "qwen-plus";
    private final String QWEN3_MIX6B = "qwen3:0.6b";

    @Bean(name = "deepseek")
    public ChatModel deepSeek()
    {
        return DashScopeChatModel.builder()
                        .dashScopeApi(DashScopeApi.builder()
                                    .apiKey(System.getenv("aliQwen-api"))
                                .build())
                .defaultOptions(
                        DashScopeChatOptions.builder().withModel(DEEPSEEK_MODEL).build()
                )
                .build();
    }

    @Bean(name = "qwen")
    public ChatModel qwen()
    {
        return DashScopeChatModel.builder().dashScopeApi(DashScopeApi.builder()
                        .apiKey(System.getenv("aliQwen-api"))
                        .build())
                .defaultOptions(
                        DashScopeChatOptions.builder()
                                .withModel(QWEN_MODEL)
                                .build()
                )
                .build();
    }

    @Bean(name = "qwen3Mix6b")
    public ChatModel qwen3Mix6b()
    {
        return OllamaChatModel.builder()
                .ollamaApi(OllamaApi.builder()
                        .build())
                .defaultOptions(
                        OllamaOptions.builder().model(QWEN3_MIX6B).build()
                )
                .build();
    }

    @Bean(name = "deepseekChatClient")
    public ChatClient deepseekChatClient(@Qualifier("deepseek") ChatModel deepSeek)
    {
        return ChatClient.builder(deepSeek)
                .defaultOptions(ChatOptions.builder()
                        .model(DEEPSEEK_MODEL)
                        .build())
                .build();
    }


    @Bean(name = "qwenChatClient")
    public ChatClient qwenChatClient(@Qualifier("qwen") ChatModel qwen) {
        return ChatClient.builder(qwen)
                .defaultOptions(ChatOptions.builder()
                        .model(QWEN_MODEL)
                        .build())
                .build();
    }


    @Bean(name = "qwen3Mix6bClient")
    public ChatClient qwen3Mix6bClient(@Qualifier("qwen3Mix6b") ChatModel qwen3Mix6b) {
        return ChatClient.builder(qwen3Mix6b)
                .defaultOptions(ChatOptions.builder()
                        .model(QWEN3_MIX6B)
                        .build())
                .build();
    }
}