package com.zoctan.api.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.model.Code;
import com.zoctan.api.model.ContestProblem;
import com.zoctan.api.service.CodeService;
import com.zoctan.api.service.ContestProblemService;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@RestController
@RequestMapping("/contest/problem")
public class ContestProblemController {
    @Resource
    private ContestProblemService contestProblemService;
    @Resource
    private CodeService codeService;

    @PostMapping
    public Result add(@RequestBody final ContestProblem contestProblem) {
        this.contestProblemService.save(contestProblem);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable final Long id) {
        this.contestProblemService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody final ContestProblem contestProblem) {
        this.contestProblemService.update(contestProblem);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result list(@PathVariable final Long id,
                       @RequestParam(defaultValue = "0") final Integer page,
                       @RequestParam(defaultValue = "0") final Integer size,
                       final Principal user) {
        PageHelper.startPage(page, size);
        final Condition condition = new Condition(ContestProblem.class);
        condition.createCriteria().andCondition("contest_id = ", id);
        final List<ContestProblem> list = this.contestProblemService.findByCondition(condition).stream()
                .peek(contestProblem -> {
                    // from user submit code -> select current problem accept
                    List<Code> codes = this.codeService.findAllUserProblemSubmitCode(id, contestProblem.getId(), user.getName());
                    boolean flag = false;
                    for (Code code : codes) {
                        if (code.getStatus() == 0) {
                            flag = true;
                            break;
                        }
                    }
                    contestProblem.setCurrentUserSubmitStatus(flag);
                }).collect(toList());
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
