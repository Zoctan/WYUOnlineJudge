package com.zoctan.api.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.model.Code;
import com.zoctan.api.model.Problem;
import com.zoctan.api.service.CodeService;
import com.zoctan.api.service.ProblemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@RestController
@RequestMapping("/problem")
public class ProblemController {
    @Resource
    private ProblemService problemService;
    @Resource
    private CodeService codeService;


    @PostMapping
    public Result add(@RequestBody final Problem problem) {
        this.problemService.save(problem);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable final Long id) {
        this.problemService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody final Problem problem) {
        this.problemService.update(problem);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable final Long id) {
        final Problem problem = this.problemService.findById(id);
        return ResultGenerator.genOkResult(problem);
    }

    @GetMapping("/record")
    public Result listRecord(final Principal user) {
        final List<Problem> problems = this.problemService.findAll();
        Integer total = problems.size();
        Long solved = 0L;
        Long easy = 0L;
        Long medium = 0L;
        Long hard = 0L;
        for (final Problem problem : problems) {
            final List<Code> codes = this.codeService.findAllUserProblemSubmitCode(-1L, problem.getId(), user.getName());
            for (final Code code : codes) {
                if (code.getStatus() == 100) {
                    solved++;
                    switch (problem.getLevel()) {
                        case 1:
                            easy++;
                            break;
                        case 2:
                            medium++;
                            break;
                        case 3:
                            hard++;
                            break;
                        default:
                            break;
                    }
                    break;
                }
            }
        }
        final Map<String, Object> map = new HashMap<>(5);
        map.put("total", total);
        map.put("solved", solved);
        map.put("easy", easy);
        map.put("medium", medium);
        map.put("hard", hard);
        return ResultGenerator.genOkResult(map);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") final Integer page,
                       @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<Problem> list = this.problemService.findAll();
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
