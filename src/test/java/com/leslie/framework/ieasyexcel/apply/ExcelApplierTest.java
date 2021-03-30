package com.leslie.framework.ieasyexcel.apply;

import com.leslie.framework.ieasyexcel.apply.excutor.DefaultExcelApplyExecutor;
import com.leslie.framework.ieasyexcel.apply.excutor.ExcelApplyExecutor;
import com.leslie.framework.ieasyexcel.apply.loader.ApplyContextLoader;
import com.leslie.framework.ieasyexcel.apply.loader.ApplyContextPageLoaderAdapter;
import com.leslie.framework.ieasyexcel.context.holder.ExcelContextHolder;
import com.leslie.framework.ieasyexcel.repository.ExcelReadData;
import com.leslie.framework.ieasyexcel.repository.ExcelReadDataRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * @author leslie
 * @date 2021/3/30
 */
@ExtendWith(MockitoExtension.class)
class ExcelApplierTest {

    @Mock
    private ExcelReadDataRepository excelReadDataRepository;

    @Test
    void apply() {
        long excelReadRecord = 1L;
        String key = String.valueOf(excelReadRecord);

        when(excelReadDataRepository.find(any(), any())).thenAnswer(invocation -> {
            Pageable pageable = invocation.getArgument(1, Pageable.class);
            int pageNumber = pageable.getPageNumber();
            if (pageNumber == 0) {
                List<ExcelReadData> excelDataList = new ArrayList<>();
                excelDataList.add(new ExcelReadData(1L, "00001"));
                excelDataList.add(new ExcelReadData(2L, "00002"));
                return new PageImpl<>(excelDataList, pageable, 5);
            }
            if (pageNumber == 1) {
                List<ExcelReadData> excelDataList = new ArrayList<>();
                excelDataList.add(new ExcelReadData(3L, "00003"));
                excelDataList.add(new ExcelReadData(4L, "00004"));
                return new PageImpl<>(excelDataList, pageable, 5);
            }
            if (pageNumber == 2) {
                List<ExcelReadData> excelDataList = new ArrayList<>();
                excelDataList.add(new ExcelReadData(5L, "00005"));
                return new PageImpl<>(excelDataList, pageable, 5);
            }
            return null;
        });

        ApplyContextLoader pageLoader = new ApplyContextPageLoaderAdapter<>(
                2, pageable -> excelReadDataRepository.find(excelReadRecord, pageable));

        new Thread(() -> {
            while (true) {
                ExcelContextHolder.APPLY.getContext(key).ifPresent(System.out::println);
            }
        }).start();

        ExcelApplyExecutor<ExcelReadData> executor = new DefaultExcelApplyExecutor<>(pageLoader);
        executor.execute(key, (excelReadData, context) -> System.out.println(excelReadData));
    }
}