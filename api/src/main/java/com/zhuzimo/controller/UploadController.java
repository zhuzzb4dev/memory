package com.zhuzimo.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.zhuzimo.photo.aggregate.Photo;
import com.zhuzimo.photo.repository.PhotoRepository;
import com.zhuzimo.common.CommonResp;
import com.zhuzimo.component.UserComponent;
import com.zhuzimo.dto.PhotoExifInfo;
import com.zhuzimo.util.PhotoExifReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 上传控制器
 *
 * @author t3
 * @date 2023/11/22
 */
@RestController
@RequestMapping("upload")
@Slf4j
public class UploadController {

    @Value("${upload.target.path:/}")
    private String path;

    @Resource
    private PhotoRepository photoRepository;

    @Resource
    private UserComponent userComponent;

    /**
     * 单
     *
     * @param file 文件
     * @return {@link CommonResp}<{@link String}>
     * @throws IOException ioexception
     */
    @PostMapping("single")
    public CommonResp<?> single(MultipartFile file) throws IOException {
        if (Objects.isNull(file)) {
            return CommonResp.buildError("上传失败,文件不能为空");
        }
        trySavePhoto(file);

        return CommonResp.buildSuccess();
    }

    /**
     * 尝试保存照片
     *
     * @param file 文件
     * @throws IOException ioexception
     */
    private void trySavePhoto(MultipartFile file) throws IOException {
        String targetPath = getTargetDirPath();
        String originalFilename = file.getOriginalFilename();
        File targetFile = new File(targetPath
                + File.separator
                + originalFilename);
        file.transferTo(targetFile);
        Photo photo = getPhoto(originalFilename, targetFile);
        boolean exist = photoRepository.findExist(photo);
        if (exist) {
            targetFile.deleteOnExit();
        } else {
            photoRepository.save(photo);
        }
    }

    /**
     * 获取照片
     *
     * @param originalFilename 原始文件名
     * @param targetFile       目标文件
     * @return {@link Photo}
     */
    private Photo getPhoto(String originalFilename, File targetFile) {
        String md5Hex = DigestUtil.md5Hex(targetFile);
        String sha1Hex = DigestUtil.sha1Hex(targetFile);
        long length = targetFile.length();

        PhotoExifInfo photoExifInfo = PhotoExifReader.getPhotoExifInfo(targetFile);
        Photo photo = new Photo();
        photo.setId(null);
        Date time = Optional.ofNullable(photoExifInfo.getTime()).orElse(new Date());
        photo.setTime(time);
        photo.setFormatTime(DateUtil.format(time, "yyyy-MM-dd"));
        photo.setMake(photoExifInfo.getMake());
        photo.setModel(photoExifInfo.getModel());
        photo.setLatitude(photoExifInfo.getLatitude());
        photo.setLongitude(photoExifInfo.getLongitude());
        photo.setMd5Hex(md5Hex);
        photo.setSha1Hex(sha1Hex);
        photo.setLength(length);
        photo.setName(originalFilename);
        photo.setUserId(userComponent.getLoginCacheDto().getId());
        return photo;
    }



    /**
     * 多
     *
     * @param files 文件
     * @return {@link CommonResp}
     * @throws IOException ioexception
     */
    @PostMapping("multi")
    public CommonResp<?> multi(List<MultipartFile> files) throws IOException {
        if (CollectionUtils.isEmpty(files)) {
            return CommonResp.buildError("上传失败,文件不能为空");
        }
        for (MultipartFile file : files) {
            trySavePhoto(file);
        }


        return CommonResp.buildSuccess();
    }

    /**
     * 获取目标目录路径
     *
     * @return {@link String}
     */
    private String getTargetDirPath() {
        Date today = new Date();
        String targetDirPath = path + File.separator
                + userComponent.getLoginCacheDto().getAccountName() + File.separator
                + DateUtil.year(today) + File.separator
                + DateUtil.month(today);
        FileUtil.mkdir(targetDirPath);
        return targetDirPath;
    }
}
