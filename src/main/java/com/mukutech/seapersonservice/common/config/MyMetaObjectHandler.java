package com.mukutech.seapersonservice.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    Date date = new Date();
    String strDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);


    @Override
    public void insertFill(MetaObject metaObject) {
       ///this.strictInsertFill(metaObject, "createBy", Long.class, 10L);
        this.strictInsertFill(metaObject, "createtime", String.class,  strDate);
        this.strictUpdateFill(metaObject, "updatetime", String.class,  strDate);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updatetime", null);
       // metaObject.setValue("updateBy", null);
        this.strictUpdateFill(metaObject, "updatetime", String.class,  strDate);
       // this.strictUpdateFill(metaObject, "updateBy", Long.class, 10L);
    }
}
