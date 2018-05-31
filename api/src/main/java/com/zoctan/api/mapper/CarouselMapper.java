package com.zoctan.api.mapper;

import com.zoctan.api.core.mapper.MyMapper;
import com.zoctan.api.model.Carousel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Zoctan
 * @date 2018/5/31
 */
public interface CarouselMapper extends MyMapper<Carousel> {
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
    Carousel find(@Param("carouselId") Long carouselId);
}