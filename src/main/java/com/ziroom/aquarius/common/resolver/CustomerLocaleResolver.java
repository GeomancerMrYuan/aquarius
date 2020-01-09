package com.ziroom.aquarius.common.resolver;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Component
@Slf4j
public class CustomerLocaleResolver implements LocaleResolver {


    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        Locale locale = request.getLocale();
        String localeFlag = request.getParameter("locale");
        //url链接有传locale参数的情况，eg:zh_CN
        if (!StringUtils.isEmpty(localeFlag)) {
            String[] split = localeFlag.split("_");
            locale = new Locale(split[0], split[1]);
        }
        //没传的情况，默认返回request.getHeader("Accept-Language")的值
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
    }
}