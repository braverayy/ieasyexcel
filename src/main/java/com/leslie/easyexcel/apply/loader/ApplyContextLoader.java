package com.leslie.easyexcel.apply.loader;


import com.leslie.easyexcel.apply.context.ApplyContext;

/**
 * @author leslie
 * @date 2021/3/23
 */
public interface ApplyContextLoader {

    ApplyContext next(String key);
}
