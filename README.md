# IEasyExcel
基于 Alibaba EasyExcel的两段式导入入库方案

## 前言

两段式导入入库方案：通过 Excel 导入数据到业务表时，并不会直接的导入业务表。而是通过一个临时表来存储，再将临时表中数据经过清洗、转换等步骤保存至业务表

## 优点

- 基于 `Bean Validator` 注解验证 Excel 字段的正确性 [Model](https://github.com/leslie-gl/ieasyexcel-examples/blob/master/src/main/java/com/leslie/framework/ieasyexcel/example/entity/excel/CityExcel.java)
- 提供了 [ReadContext](https://github.com/leslie-gl/ieasyexcel/blob/master/src/main/java/com/leslie/framework/ieasyexcel/context/ReadContext.java)、[ApplyContext](https://github.com/leslie-gl/ieasyexcel/blob/master/src/main/java/com/leslie/framework/ieasyexcel/context/ApplyContext.java)，用于获取读 Excel 和 Excel 入库时的上下文
- 提供了对简单 Excel Head 的验证

## 快速开始

如果你并未使用过 [EasyExcel](https://github.com/alibaba/easyexcel)，请先进行相关的了解

**完整的示例，请参考 [ieasyexcel-examples](https://github.com/leslie-gl/ieasyexcel-examples)**

### Excel 读

```java
// 构建 Excel 读参数
ExcelReadParam readParam = ExcelReadParam.builder()
    .key(key)
    .excelReader((excelDataList, context) -> {

        // save data to temporary table

    }).build();
// 创建监听器
ExcelReadListener<? extends BasedExcelReadModel> readListener = new ExcelReadListener<>(readParam);
// 读取 Excel
EasyExcel.read(inputStream, EXCEL_BIZ_TYPE.CITY.excelClazz, readListener).sheet().doRead();
```

### Excel 入库

```java
ApplyContextLoader contextLoader = new ApplyContextPageLoaderAdapter<>(pageable -> {
    // 分页查询临时表数据
});
// 构建 Excel 入库参数
ExcelApplyParam applyParam = ExcelApplyParam.builder()
    .key(applyKey)
    .contextLoader(contextLoader)
    .excelApplier((data, context) -> {

        // 验证数据合法性并保存到业务表

    }).build();
// 入库执行器
ExcelApplyExecutor<ExcelRow> applyExecutor = new ExcelApplyExecutor<>(applyParam);
// 执行入库
applyExecutor.execute();
```