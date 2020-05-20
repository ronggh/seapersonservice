package com.rhdk.purchasingservice.feign;

import com.rhdk.purchasingservice.common.config.FeignExecptionConfig;
import com.rhdk.purchasingservice.common.utils.response.ResponseEnvelope;
import com.rhdk.purchasingservice.pojo.query.AssetQuery;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

/**
 * @author: LMYOU
 * @create: 2020-04-28
 * @Description:经租系统，资产基础服务
 */
@FeignClient(value = "${feignName.assetService}", fallback = FeignExecptionConfig.class)
@Component
public interface AssetServiceFeign {
    @RequestMapping(value = "/fileUploadService/uploadSingleFile", method = RequestMethod.POST)
    String uploadSingleFile(@NotNull MultipartFile file, @RequestHeader(value = "Authorization") String token);

    /**
     * 获取送货明细清单数据
     * @param assetQuery
     * @param token
     * @return
     */
    @RequestMapping(value = "/assetservice/assetEntityInfo/searchEntityInfoList", method = RequestMethod.POST)
    ResponseEnvelope searchEntityInfoPage(@NotNull AssetQuery assetQuery, @RequestHeader(value = "Authorization") String token);

    /**
     * 获取固有属性值
     * @param assetQuery
     * @param token
     * @return
     */
    @RequestMapping(value = "/assetservice/assetTmplPrpts/searchValByPrptIds", method = RequestMethod.POST)
    ResponseEnvelope searchValByPrptIds(@NotNull AssetQuery assetQuery, @RequestHeader(value = "Authorization") String token);

}