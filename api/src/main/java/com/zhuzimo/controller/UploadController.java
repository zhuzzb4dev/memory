package com.zhuzimo.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.zhuzimo.account.dp.Photo;
import com.zhuzimo.account.repository.PhotoRepository;
import com.zhuzimo.component.UserComponent;
import com.zhuzimo.dto.CommonResp;
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
    public CommonResp single(MultipartFile file) throws IOException {
        if (Objects.isNull(file)) {
            return CommonResp.buildError("上传失败,文件不能为空");
        }
        trySavePhoto(file);

        return CommonResp.buildSuccess();
    }

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
            targetFile.delete();
        } else {
            photoRepository.save(photo);
        }
    }

    private Photo getPhoto(String originalFilename, File targetFile) {
        String md5Hex = DigestUtil.md5Hex(targetFile);
        String sha1Hex = DigestUtil.sha1Hex(targetFile);
        long length = targetFile.length();

        PhotoExifInfo photoExifInfo = PhotoExifReader.getPhotoExifInfo(targetFile);
        Photo photo = new Photo();
        photo.setId(null);
        photo.setTime(photoExifInfo.getTime());
        photo.setMake(photoExifInfo.getMake());
        photo.setModel(photoExifInfo.getModel());
        photo.setLatitude(photoExifInfo.getLatitude());
        photo.setLongitude(photoExifInfo.getLongitude());
        photo.setMd5Hex(md5Hex);
        photo.setSha1Hex(sha1Hex);
        photo.setLength(length);
        photo.setName(originalFilename);
        photo.setUserId(userComponent.getLoginUserId());
        return photo;
    }

    private void prepareTargetDir(String targetPath) {
        File targetDir = new File(targetPath);
        targetDir.mkdirs();
    }

    @PostMapping("multi")
    public CommonResp multi(List<MultipartFile> files) throws IOException {
        if (CollectionUtils.isEmpty(files)) {
            return CommonResp.buildError("上传失败,文件不能为空");
        }
        for (MultipartFile file : files) {
            trySavePhoto(file);
        }


        return CommonResp.buildSuccess();
    }

    private String getTargetDirPath() {
        Date today = new Date();
        String targetDirPath = path + File.separator
                + DateUtil.year(today) + File.separator
                + DateUtil.month(today);
        prepareTargetDir(targetDirPath);
        return targetDirPath;
    }
}
