package com.mukutech.seapersonservice.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    Date date = new Date();


    @Override
    public void insertFill(MetaObject metaObject) {

        this.strictInsertFill(metaObject, "createtime", Date.class, date);
        this.strictUpdateFill(metaObject, "updatetime", Date.class, date);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updatetime", null);
        this.strictUpdateFill(metaObject, "updatetime", Date.class, date);
    }
}
