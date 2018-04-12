package com.zoctan.api.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.model.Favorite;
import com.zoctan.api.service.FavoriteService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
        this.favoriteService.delete(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody final Favorite favorite) {
        this.favoriteService.update(favorite);
        return ResultGenerator.genOkResult();
    }

    @GetMapping
    public Result listUserFavorite(@AuthenticationPrincipal final UserDetails userDetails,
                                   @RequestParam(defaultValue = "0") final Integer page,
                                   @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        // mybatis 一对多关联查询 + pagehelper 分页
        // pagehelper分页对最先的查询语句 limit 导致查询的页数错误
        // 暂时办法 先查询用户的收藏夹
        //fixme
        this.favoriteService.findUserFavoriteByUsername(userDetails.getUsername());
        final List<Favorite> list = this.favoriteService.findUserDetailFavoriteByUsername(userDetails.getUsername());
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
