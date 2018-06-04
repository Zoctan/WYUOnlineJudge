package com.zoctan.api.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.model.Code;
import com.zoctan.api.service.CodeService;
import net.dongliu.requests.Requests;
import net.dongliu.requests.json.TypeInfer;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@RestController
@RequestMapping("/code")
public class CodeController {
    @Resource
    private CodeService codeService;

    @PostMapping
    public Result runOrSubmit(@RequestParam("run") final boolean run, @RequestBody final Code code) {
        List<Code> codeList = Requests
                .post("http://localhost:20000/run")
                .jsonBody(code)
                .send().readToJson(new TypeInfer<List<Code>>() {
                });

        //this.codeService.save(code);
        return ResultGenerator.genOkResult(codeList);
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
                                 final Principal user) {
        PageHelper.startPage(page, size);
        final List<Code> list = this.codeService.findAllUserProblemSubmitCode(-1L, problemId, user.getName());
        final PageInfo pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") final Integer page,
                       @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<Code> list = this.codeService.findAll();
        final PageInfo pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
