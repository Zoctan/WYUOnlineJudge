package com.zoctan.api.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.model.AdminProblem;
import com.zoctan.api.service.AdminProblemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@RestController
@RequestMapping("/admin/problem")
public class AdminProblemController {
    @Resource
    private AdminProblemService adminProblemService;

    @PostMapping
    public Result add(@RequestBody final AdminProblem adminProblem) {
        this.adminProblemService.save(adminProblem);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable final Long id) {
        this.adminProblemService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody final AdminProblem adminProblem) {
        this.adminProblemService.update(adminProblem);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable final Long id) {
        final AdminProblem adminProblem = this.adminProblemService.findById(id);
        return ResultGenerator.genOkResult(adminProblem);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") final Integer page,
                       @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<AdminProblem> list = this.adminProblemService.findAll();
        final PageInfo pageInfo = new PageInfo<>(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
