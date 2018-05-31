package com.zoctan.api.service.impl;

import com.zoctan.api.core.service.AbstractService;
import com.zoctan.api.mapper.CarouselMapper;
import com.zoctan.api.model.Carousel;
import com.zoctan.api.service.CarouselService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CarouselServiceImpl extends AbstractService<Carousel> implements CarouselService {
    @Resource
    private CarouselMapper carouselMapper;

}
