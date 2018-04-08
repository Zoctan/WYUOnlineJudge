package com.zoctan.api.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.model.FavoriteProblem;
import com.zoctan.api.service.FavoriteProblemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/favorite/problem")
public class FavoriteProblemController {
    @Resource
    private FavoriteProblemService favoriteProblemService;

    @PostMapping
    public Result add(@RequestBody final List<FavoriteProblem> favoriteProblem) {
        this.favoriteProblemService.save(favoriteProblem);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping
    public Result delete(@RequestBody final FavoriteProblem favoriteProblem) {
        this.favoriteProblemService.delete(favoriteProblem);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody final FavoriteProblem favoriteProblem) {
        this.favoriteProblemService.update(favoriteProblem);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable final Long id) {
        final FavoriteProblem favoriteProblem = this.favoriteProblemService.findById(id);
        return ResultGenerator.genOkResult(favoriteProblem);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") final Integer page,
                       @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<FavoriteProblem> list = this.favoriteProblemService.findAll();
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
