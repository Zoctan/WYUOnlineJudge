package com.zoctan.api.controller.web;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zoctan.api.core.response.Result;
import com.zoctan.api.core.response.ResultGenerator;
import com.zoctan.api.model.Carousel;
import com.zoctan.api.service.CarouselService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@RestController
@RequestMapping("/carousel")
public class CarouselController {
    @Resource
    private CarouselService carouselService;

    @PostMapping
    public Result add(@RequestBody final Carousel carousel) {
        this.carouselService.save(carousel);
        return ResultGenerator.genOkResult();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable final Long id) {
        this.carouselService.deleteById(id);
        return ResultGenerator.genOkResult();
    }

    @PutMapping
    public Result update(@RequestBody final Carousel carousel) {
        this.carouselService.update(carousel);
        return ResultGenerator.genOkResult();
    }

    @GetMapping("/{id}")
    public Result detail(@PathVariable final Long id) {
        final Carousel carousel = this.carouselService.findById(id);
        return ResultGenerator.genOkResult(carousel);
    }

    @GetMapping
    public Result list(@RequestParam(defaultValue = "0") final Integer page,
                       @RequestParam(defaultValue = "0") final Integer size) {
        PageHelper.startPage(page, size);
        final List<Carousel> list = this.carouselService.findAll()
                .stream()
                .peek(carousel -> carousel.setDescription(null))
                .collect(toList());
        final PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genOkResult(pageInfo);
    }
}
