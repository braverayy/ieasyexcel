package com.okayhu.framework.ieasyexcel.apply.loader;


import com.okayhu.framework.ieasyexcel.context.ApplyContext;

/**
 * @author leslie
 * @date 2021/3/23
 */
public interface ApplyContextLoader {

    ApplyContext next(String key);
}
