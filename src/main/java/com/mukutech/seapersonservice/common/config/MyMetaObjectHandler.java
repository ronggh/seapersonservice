package com.mukutech.seapersonservice.common.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {




    @Override
    public void insertFill(MetaObject metaObject) {

        Date date = new Date();
        this.strictInsertFill(metaObject, "createtime", Date.class, date);
        this.strictUpdateFill(metaObject, "updatetime", Date.class, date);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Date date = new Date();
        metaObject.setValue("updatetime", null);
        this.strictUpdateFill(metaObject, "updatetime", Date.class, date);
    }
}
