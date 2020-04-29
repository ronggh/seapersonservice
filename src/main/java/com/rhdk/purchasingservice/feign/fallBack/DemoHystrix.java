package com.rhdk.purchasingservice.feign.fallBack;

import com.rhdk.purchasingservice.common.enums.ExceptionEnum;
import com.rhdk.purchasingservice.common.exception.ServiceException;
import com.rhdk.purchasingservice.common.utils.response.ResponseEnvelope;
import com.rhdk.purchasingservice.feign.IDemoFeign;
import com.rhdk.purchasingservice.pojo.entity.Demo;
import com.rhdk.purchasingservice.pojo.vo.DemoVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: LMYOU
 * @create: 2020-04-28
 * @Description:
 */
@Component
public class DemoHystrix implements IDemoFeign {

    @Override
    public ResponseEnvelope searchDemoListPage(DemoVo dto, String token) {
        return new ResponseEnvelope(new ServiceException(ExceptionEnum.ERROR_RPC_SERVICE.getStatus(),ExceptionEnum.ERROR_RPC_SERVICE.getMessage()));
    }
}
