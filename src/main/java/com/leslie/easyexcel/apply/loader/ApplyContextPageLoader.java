package com.leslie.easyexcel.apply.loader;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author leslie
 * @date 2021/3/23
 */
public interface ApplyContextPageLoader<T> {

    Page<T> load(Pageable pageable);
}
