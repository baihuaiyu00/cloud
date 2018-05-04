package com.xupt.cloud.manager.web.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * Created by baihuaiyu on 2018/5/1
 */
public class ApplicationController<T> {

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
//        binder.registerCustomEditor(String.class, new StringEscapeEditor(true, true, true));
    }
}
