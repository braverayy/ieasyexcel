package com.leslie.framework.ieasyexcel.apply.loader;


import com.leslie.framework.ieasyexcel.context.ApplyContext;
import com.leslie.framework.ieasyexcel.context.holder.ContextHolder;
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

    private final ContextHolder<String, ApplyContext> applyContextHolder;

    private final ApplyContextPageLoader<T> pageContextLoader;

    public ApplyContextPageLoaderAdapter(ContextHolder<String, ApplyContext> applyContextHolder,
                                         ApplyContextPageLoader<T> pageContextLoader) {
        this.applyContextHolder = applyContextHolder;
        this.pageContextLoader = pageContextLoader;
    }

    public ApplyContextPageLoaderAdapter(int pageSize,
                                         ContextHolder<String, ApplyContext> applyContextHolder,
                                         ApplyContextPageLoader<T> pageContextLoader) {
        this.pageSize = pageSize;
        this.applyContextHolder = applyContextHolder;
        this.pageContextLoader = pageContextLoader;
    }

    @Override
    public ApplyContext next(String key) {
        ApplyContext applyContext = applyContextHolder.getContext(key).orElse(null);

        if (applyContext == null) {

            page = pageContextLoader.load(PageRequest.of(0, pageSize));

            applyContext = new ApplyContext();
            applyContext.setCurrentData(page.getContent().isEmpty() ? null : page.getContent().get(0));
            applyContext.setTotal(page.getTotalElements());
            applyContext.setIndex(0L);

            applyContextHolder.setContext(key, applyContext);
        } else {

            long index = applyContext.getIndex();
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
