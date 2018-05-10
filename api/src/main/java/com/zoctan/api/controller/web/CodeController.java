package com.zoctan.api.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.databaseModel.Code;
import com.zoctan.api.service.CodeService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/code")
public class CodeController {
    @Resource
    private CodeService codeService;

    @PostMapping
    public Result runOrSubmit(@RequestParam("status") final boolean status, @RequestBody final Code code) {
        System.out.println(status);
        System.out.println(code.getCode());
        System.out.println(code.getLanguage());
        System.out.println(code.getProblemId());
        System.out.println(code.getUserId());
        //this.codeService.save(code);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable final Long id) {
        this.codeService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody final Code code) {
        this.codeService.update(code);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{problemId}")
    public Result listSubmitCode(@PathVariable final Long problemId,
                                 @RequestParam(defaultValue = "0") final Integer page,
                                 @RequestParam(defaultValue = "0") final Integer size,
                                 @AuthenticationPrincipal final UserDetails userDetails) {
        PageHelper.startPage(page, size);
        final List<Code> list = this.codeService.findAllUserProblemSubmitCode(problemId, userDetails.getUsername());
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") final Integer page,
                       @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<Code> list = this.codeService.findAll();
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
