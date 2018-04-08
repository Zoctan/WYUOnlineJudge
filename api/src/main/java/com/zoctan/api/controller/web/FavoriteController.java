package com.zoctan.api.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.model.Favorite;
import com.zoctan.api.service.FavoriteService;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Condition;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/favorite")
public class FavoriteController {
    @Resource
    private FavoriteService favoriteService;

    @PostMapping
    public Result add(@RequestBody final Favorite favorite) {
        this.favoriteService.save(favorite);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable final Long id) {
        this.favoriteService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody final Favorite favorite) {
        this.favoriteService.update(favorite);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{userId}")
    public Result listUserFavorite(@PathVariable final Long userId,
                                   @RequestParam(defaultValue = "0") final Integer page,
                                   @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final Condition condition = new Condition(Favorite.class);
        condition.createCriteria().andCondition("user_id = ", userId);
        final List<Favorite> list = this.favoriteService.findByCondition(condition);
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
