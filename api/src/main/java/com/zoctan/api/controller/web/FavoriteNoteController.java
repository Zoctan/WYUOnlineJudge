package com.zoctan.api.controller.web;

import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.model.FavoriteNote;
import com.zoctan.api.service.FavoriteNoteService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author Zoctan
 * @date 2018/6/3
 */
@RestController
@RequestMapping("/favorite/note")
public class FavoriteNoteController {
    @Resource
    private FavoriteNoteService favoriteNoteService;

    @PostMapping
    public Result add(@RequestBody final FavoriteNote favoriteNote) {
        this.favoriteNoteService.save(favoriteNote);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping
    public Result delete(@RequestBody final FavoriteNote favoriteNote) {
        this.favoriteNoteService.delete(favoriteNote);
        return ResultGenerator.genOkResult();
    }
}
