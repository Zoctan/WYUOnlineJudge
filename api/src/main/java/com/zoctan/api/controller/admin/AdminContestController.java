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
 */
@RestController
@RequestMapping("/admin/contest")
public class AdminContestController {
    @Resource
    private AdminContestService adminContestService;

    @PostMapping
    public Result add(@RequestBody final AdminContest adminContest) {
        this.adminContestService.save(adminContest);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable final Long id) {
        this.adminContestService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody final AdminContest adminContest) {
        this.adminContestService.update(adminContest);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable final Long id) {
        final AdminContest adminContest = this.adminContestService.findById(id);
        return ResultGenerator.genOkResult(adminContest);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") final Integer page,
                       @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<AdminContest> list = this.adminContestService.findAll();
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
