package com.zoctan.api.controller.web;

import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.databaseModel.Feedback;
import com.zoctan.api.util.EmailUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
    @Resource
    private EmailUtil emailUtil;

    @PostMapping
    public Result email(@RequestBody final Feedback feedback) {
        return this.emailUtil.sendHtmlMail(feedback)
                ? ResultGenerator.genOkResult()
                : ResultGenerator.genFailedResult("邮件发送失败");
    }
}
