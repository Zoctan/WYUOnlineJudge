package com.zoctan.api.controller.web;

import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.model.FavoriteProblem;
import com.zoctan.api.service.FavoriteProblemService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@RestController
@RequestMapping("/favorite/problem")
public class FavoriteProblemController {
    @Resource
    private FavoriteProblemService favoriteProblemService;

    @PostMapping
    public Result add(@RequestBody final FavoriteProblem favoriteProblem) {
        this.favoriteProblemService.save(favoriteProblem);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping
    public Result delete(@RequestBody final FavoriteProblem favoriteProblem) {
        this.favoriteProblemService.delete(favoriteProblem);
        return ResultGenerator.genOkResult();
    }
}
