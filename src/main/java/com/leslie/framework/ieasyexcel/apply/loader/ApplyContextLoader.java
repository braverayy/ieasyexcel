package com.leslie.framework.ieasyexcel.apply.loader;


import com.leslie.framework.ieasyexcel.apply.context.ApplyContext;

/**
 * @author leslie
 * @date 2021/3/23
 */
public interface ApplyContextLoader {

    ApplyContext next(String key);
}
