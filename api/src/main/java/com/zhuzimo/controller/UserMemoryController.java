package com.zhuzimo.controller;

import com.zhuzimo.photo.aggregate.Photo;
import com.zhuzimo.photo.repository.PhotoRepository;
import com.zhuzimo.common.CommonPaged;
import com.zhuzimo.common.CommonPagedAble;
import com.zhuzimo.common.CommonResp;
import com.zhuzimo.component.UserComponent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户回忆控制器
 *
 * @author t3
 * @date 2023/12/11
 */
@RestController
@RequestMapping("memory")
@Slf4j
public class UserMemoryController {

    @Resource
    private PhotoRepository photoRepository;

    @Resource
    UserComponent userComponent;

    /**
     * 查询分页
     *
     * @param commonPagedAble 可分页
     * @return {@link CommonResp}<{@link CommonPaged}<{@link Photo}>>
     */
    @PostMapping("query-paged")
    public CommonResp<CommonPaged<Photo>> queryPaged(@RequestBody CommonPagedAble commonPagedAble) {
        return CommonResp.buildSuccess(photoRepository.queryPagedByUserId(commonPagedAble, userComponent.getLoginCacheDto().getId()));
    }
}
