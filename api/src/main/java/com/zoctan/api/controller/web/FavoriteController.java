package com.zoctan.api.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.model.Favorite;
import com.zoctan.api.service.FavoriteService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
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

    @GetMapping("/problem")
    public Result listUserFavoriteProblem(@RequestParam(defaultValue = "0") final Integer page,
                                          @RequestParam(defaultValue = "0") final Integer size,
                                          final Principal user) {
        PageHelper.startPage(page, size);
        // mybatis 一对多关联查询 + pagehelper 分页
        // pagehelper分页对最先的查询语句 limit 导致查询的页数错误
        // 暂时办法 先查询用户的收藏夹
        //fixme
        this.favoriteService.findUserFavoriteByUsernameAndClassification(user.getName(), Short.valueOf("1"));
        final List<Favorite> list = this.favoriteService.findUserFavoriteProblemByUsername(user.getName());
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }

    @GetMapping("/note")
    public Result listUserFavoriteNote(@RequestParam(defaultValue = "0") final Integer page,
                                       @RequestParam(defaultValue = "0") final Integer size,
                                       final Principal user) {
        PageHelper.startPage(page, size);
        //fixme
        this.favoriteService.findUserFavoriteByUsernameAndClassification(user.getName(), Short.valueOf("2"));
        final List<Favorite> list = this.favoriteService.findUserFavoriteNoteByUsername(user.getName());
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
