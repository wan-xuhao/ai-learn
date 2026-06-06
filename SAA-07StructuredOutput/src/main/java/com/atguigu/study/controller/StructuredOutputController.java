package com.atguigu.study.controller;

import com.atguigu.study.records.StudentRecord;
import jakarta.annotation.Resource;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

/**
 * @Description: TODO
 * @Author: wanxuhao
 * @Date: 2026/6/6
 **/
@RestController
public class StructuredOutputController {

    @Resource(name = "qwenChatClient")
    private ChatClient qwenChatClient;

    /**
     * http://localhost:8007/structuredoutput/chat?sname=李四&email=zzyybs@126.com
     * @param sname
     * @return
     */
    @GetMapping("/structuredoutput/chat")
    public StudentRecord chat(@RequestParam(name = "sname") String sname,
                              @RequestParam(name = "email") String email) {

        return qwenChatClient.prompt().user(new Consumer<ChatClient.PromptUserSpec>() {
            @Override
            public void accept(ChatClient.PromptUserSpec promptUserSpec)
            {
                promptUserSpec.text("学号1001，我叫{sname},大学专业计算机科学与技术,邮箱{email}")
                        .param("sname",sname)
                        .param("email",email);
            }
        }).call().entity(StudentRecord.class);
    }

    /**
     * http://localhost:8007/structuredoutput/chat2?sname=李四&email=zzyybs@126.com
     * @param sname
     * @param email
     * @return
     */
    @GetMapping("/structuredoutput/chat2")
    public StudentRecord chat2(@RequestParam(name = "sname") String sname,
                              @RequestParam(name = "email") String email){
        // 这里你会发现学号和大学专业都没有占位符，但是也自动赋值到StudentRecord的属性当中了，它会识别语义自动填充，但是不推荐
        String templateStr = "学号1003，我叫{sname}，大学专业计算机科学与技术22。邮箱{email}";
        return qwenChatClient.prompt().user(consumer->
                consumer.text(templateStr).param("sname",sname).param("email",email))
                .call().entity(StudentRecord.class);
    }
}
