package com.zoctan.api.service;

import com.zoctan.api.core.service.Service;
import com.zoctan.api.model.Carousel;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/31
 */
public interface CarouselService extends Service<Carousel> {

    /**
     * 获取最新的轮播图
     *
     * @return 最新的轮播图列表
     */
    List<Carousel> findNewest();

    /**
     * 获取轮播图对应的内容
     *
     * @param carouselId 轮播图Id
     * @return 轮播图内容
     */
    Carousel find(Long carouselId);
}
