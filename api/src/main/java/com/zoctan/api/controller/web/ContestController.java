package com.zoctan.api.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.model.Contest;
import com.zoctan.api.service.ContestService;
import com.zoctan.api.service.UserContestService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@RestController
@RequestMapping("/contest")
public class ContestController {
    @Resource
    private ContestService contestService;
    @Resource
    private UserContestService userContestService;
    @Resource
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public Result add(@RequestBody final Contest contest) {
        this.contestService.save(contest);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable final Long id) {
        this.contestService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody final Contest contest) {
        this.contestService.update(contest);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/user/{contestId}")
    public Result userJoinContest(@PathVariable final Long contestId, final Principal user) {
        this.userContestService.save(user.getName(), contestId);
        return ResultGenerator.genOkResult();
    }

    @PostMapping("/password")
    public Result validatePassword(@RequestBody final Contest contest) {
        final Contest contest1 = this.contestService.findById(contest.getId());
        final boolean isValidate = this.passwordEncoder.matches(contest.getPassword(), contest1.getPassword());
        return ResultGenerator.genOkResult(isValidate);
    }

    @DeleteMapping("/user/{contestId}")
    public Result userOutContest(@PathVariable final Long contestId, final Principal user) {
        this.userContestService.delete(user.getName(), contestId);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable final Long id, final Principal user) {
        final Contest contest = this.contestService.findOne(id, user.getName());
        return ResultGenerator.genOkResult(contest);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") final Integer page,
                       @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<Contest> list = this.contestService.findAll();
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @GetMapping("/hot")
    public Result listHot(@RequestParam(defaultValue = "0") final Integer page,
                          @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<Contest> list = this.contestService.findHot();
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
