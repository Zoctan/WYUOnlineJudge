package com.zoctan.api.controller.admin;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.model.AdminContest;
import com.zoctan.api.service.AdminContestService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@RestController
@RequestMapping("/admin/contest")
public class AdminContestController {
    @Resource
    private AdminContestService adminContestService;

    @PostMapping
    public Result addAdminContest(@RequestBody final AdminContest adminContest) {
        this.adminContestService.save(adminContest);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result deleteAdminContest(@PathVariable final Long id) {
        this.adminContestService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @GetMapping
    public Result listAdminContest(@RequestParam(defaultValue = "0") final Integer page,
                                   @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<AdminContest> list = this.adminContestService.findAll();
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
