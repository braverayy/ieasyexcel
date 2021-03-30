package com.leslie.framework.ieasyexcel.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author leslie
 * @date 2021/3/30
 */
public interface ExcelReadDataRepository {

    Page<ExcelReadData> find(Long excelRecordId, Pageable pageable);
}
