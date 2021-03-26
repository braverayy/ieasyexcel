package com.leslie.easyexcel.apply.loader;


import com.leslie.easyexcel.apply.context.ApplyContext;
import com.leslie.easyexcel.apply.context.ApplyContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author leslie
 * @date 2021/3/23
 */
public class ApplyContextPageLoaderAdapter<T> implements ApplyContextLoader {

    private Page<T> page;

    private int pageSize = 50;

    private final ApplyContextPageLoader<T> pageContextLoader;

    public ApplyContextPageLoaderAdapter(ApplyContextPageLoader<T> pageContextLoader) {
        this.pageContextLoader = pageContextLoader;
    }

    public ApplyContextPageLoaderAdapter(int pageSize, ApplyContextPageLoader<T> pageContextLoader) {
        this.pageSize = pageSize;
        this.pageContextLoader = pageContextLoader;
    }

    @Override
    public ApplyContext next(String key) {
        ApplyContext applyContext = ApplyContextHolder.getContext(key).orElse(null);

        if (applyContext == null) {

            page = pageContextLoader.load(PageRequest.of(0, pageSize));

            applyContext = new ApplyContext();
            applyContext.setCurrentData(page.getContent().isEmpty() ? null : page.getContent().get(0));
            applyContext.setTotal(page.getTotalElements());
            applyContext.setIndex(0L);

            ApplyContextHolder.setContext(key, applyContext);
        } else {

            long index = (long) applyContext.getIndex();
            int indexOfElements = (int) (index - (page.getNumber() * page.getSize()));

            List<T> content = page.getContent();

            if (applyContext.isLast()) {

                applyContext.setIndex(index + 1);
                return applyContext;

            } else if (indexOfElements < content.size() - 1) {

                applyContext.setCurrentData(content.get(indexOfElements + 1));

            } else {

                // 下一页
                page = pageContextLoader.load(page.nextPageable());
                applyContext.setCurrentData(page.getContent().isEmpty() ? null : page.getContent().get(0));
            }

            applyContext.setIndex(index + 1);
        }
        return applyContext;
    }
}
