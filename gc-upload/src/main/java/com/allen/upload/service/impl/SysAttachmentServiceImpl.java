package com.allen.upload.service.impl;

import com.allen.upload.config.UploadConfig;
import com.allen.upload.entity.SysAttachment;
import com.allen.upload.mapper.SysAttachmentMapper;
import com.allen.upload.service.ISysAttachmentService;
import com.allen.upload.util.DifferenceUtils;
import com.allen.upload.util.ImageUtil;
import com.allen.upload.vo.SysAttachmentVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysAttachmentServiceImpl extends ServiceImpl<SysAttachmentMapper, SysAttachment> implements ISysAttachmentService {

    @Autowired
    private UploadConfig uploadConfig;

    /**
     * 更新附件与业务关系信息
     *
     * @param fileIds      附件id集合，已','分割
     * @param businessId   业务id
     * @param businessType 附件类型 参考 FileBusinessType 枚举类型
     */
    @Override
    public void updateAttachment(String fileIds, String businessId, String businessType) {
        String[] fileIdArr = fileIds.split(",");
        List<String> fileIdList = Arrays.asList(fileIdArr);
        //获取以前绑定附件信息
        List<SysAttachmentVo> attaList = getAttachmentList(businessId, businessType);
        if (CollectionUtils.isEmpty(attaList)) {
            saveAttachment(fileIdList, businessId, businessType, false);
            return;
        }
        List<String> idList = attaList.stream().map(SysAttachmentVo::getId).collect(Collectors.toList());
        //比较list获取相同部分
        List<String> needAddOpenidList = DifferenceUtils.getNeedAddOpenidList(fileIdList, idList);
        //找到filedIds 需要删除的数据
        List<String> delList = DifferenceUtils.getDifferenceList(idList, needAddOpenidList);
        if (CollectionUtils.isNotEmpty(delList)) {
            saveAttachment(delList, businessId, businessType, true);
        }
        //找到filedIds 需要新增的数据
        List<String> saveList = DifferenceUtils.getDifferenceList(fileIdList, needAddOpenidList);
        if (CollectionUtils.isNotEmpty(saveList)) {
            saveAttachment(fileIdList, businessId, businessType, false);
        }

    }

    /**
     * 更新附件信息
     *
     * @param fileIdArr    附件id集合
     * @param businessId   业务id
     * @param businessType 附件类型 参考 FileBusinessType 枚举类型
     * @param delFlag      是否删除  true 是 false 否
     */
    private void saveAttachment(List<String> fileIdArr, String businessId, String businessType, boolean delFlag) {
        for (String fileId : fileIdArr) {
            SysAttachment attachment = new SysAttachment();
            attachment.setId(fileId);
            attachment.setBusinessId(businessId);
            attachment.setBusinessType(businessType);
            attachment.setDelFlag(delFlag);
            baseMapper.updateById(attachment);
        }
    }



    @Override
    public List<SysAttachmentVo> getAttachmentList(String businessId, String businessType) {
        LambdaQueryWrapper<SysAttachment> queryWrapper = new QueryWrapper<SysAttachment>().lambda();
        queryWrapper.eq(SysAttachment::getBusinessId, businessId);
        queryWrapper.eq(SysAttachment::getBusinessType, businessType);
        queryWrapper.eq(SysAttachment::getDelFlag,false);
        List<SysAttachment> sysAttachments = baseMapper.selectList(queryWrapper);

        List<SysAttachmentVo> sysAttachmentVoList = adapterData(sysAttachments);
        return sysAttachmentVoList;
    }

    @Override
    public List<SysAttachmentVo> getMultiAttachmentList(List<String> businessIds) {
        LambdaQueryWrapper<SysAttachment> queryWrapper = new QueryWrapper<SysAttachment>().lambda();
        queryWrapper.in(SysAttachment::getBusinessId, businessIds);
        List<SysAttachment> sysAttachments = baseMapper.selectList(queryWrapper);

        List<SysAttachmentVo> sysAttachmentVoList = adapterData(sysAttachments);

        return sysAttachmentVoList;
    }

    @Override
    public List<SysAttachmentVo> getMultiAttachmentListByFileIds(List<String> ids) {
        LambdaQueryWrapper<SysAttachment> wrapper = Wrappers.lambdaQuery();
        wrapper.in(SysAttachment::getId,ids);
        List<SysAttachment> sysAttachments = list(wrapper);
        List<SysAttachmentVo> sysAttachmentVoList = adapterData(sysAttachments);

        return sysAttachmentVoList;
    }

    private List<SysAttachmentVo> adapterData(List<SysAttachment> sysAttachments) {
        List<SysAttachmentVo> sysAttachmentVoList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(sysAttachments)) {
            sysAttachments.forEach(sysAttachment -> {
                getNewFilePath(sysAttachment);
                SysAttachmentVo sysAttachmentVo = new SysAttachmentVo();
                BeanUtils.copyProperties(sysAttachment, sysAttachmentVo);
                sysAttachmentVoList.add(sysAttachmentVo);
            });
        }
        return sysAttachmentVoList;
    }

    public void getNewFilePath(SysAttachment sysAttachment) {
        if (sysAttachment == null) return;
        if (ImageUtil.isImageByName(sysAttachment.getFileName())) {
            String ngxin_url = uploadConfig.getNginxUrl();
            ngxin_url = FilenameUtils.getFullPathNoEndSeparator(ngxin_url);
            String sourceFilePath = sysAttachment.getFilePath();
            String newFilePath = ngxin_url + File.separator + sourceFilePath;
            sysAttachment.setFilePath(newFilePath);
        } else {
            String rootDir = uploadConfig.getUploadRootDir();
            rootDir = FilenameUtils.getFullPathNoEndSeparator(rootDir);
            String sourceFilePath = sysAttachment.getFilePath();
            String newFilePath = rootDir + File.separator + sourceFilePath;
            sysAttachment.setFilePath(newFilePath);
        }
    }
}
