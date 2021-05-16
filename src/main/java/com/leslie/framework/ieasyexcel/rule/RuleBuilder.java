package com.leslie.framework.ieasyexcel.rule;

import java.util.*;

/**
 * @author leslie
 * @date 2021/5/15
 */
public class RuleBuilder<T> {

    private final Map<Integer, List<Rule<T>>> allRuleMapping = new HashMap<>(16);

    private static final int AND = 1;
    private static final int OR = 0;

    public static <T> RuleBuilder<T> create() {
        return new RuleBuilder<>();
    }


    public RuleBuilder<T> and(List<Rule<T>> ruleList) {
        allRuleMapping.put(AND, ruleList);
        return this;
    }

    public RuleBuilder<T> or(List<Rule<T>> ruleList) {
        allRuleMapping.put(OR, ruleList);
        return this;
    }

    public Result execute(T bizData) {
        Result finalResult = null;

        for (Map.Entry<Integer, List<Rule<T>>> item : allRuleMapping.entrySet()) {
            List<Rule<T>> ruleList = item.getValue();

            switch (item.getKey()) {
                case AND:
                    // 如果是 and 关系，同步执行
                    System.out.println("execute key = " + 1);
                    finalResult = andExecute(bizData, ruleList);
                    if (!finalResult.isAvailable()) {
                        return finalResult;
                    }
                    break;
                case OR:
                    // 如果是 or 关系，并行执行
                    System.out.println("execute key = " + 0);
                    finalResult = orExecute(bizData, ruleList);
                    if (!finalResult.isAvailable()) {
                        return finalResult;
                    }
                    break;
                default:
                    break;
            }
        }
        return finalResult;
    }

    private Result andExecute(T bizData, List<Rule<T>> ruleList) {
        Result result = null;
        for (Rule<T> rule : ruleList) {
            result = rule.execute(bizData);
            if (!result.isAvailable()) {
                // and 关系匹配失败一次，直接返回
                return result;
            }
        }
        return result;
    }

    private Result orExecute(T bizData, List<Rule<T>> ruleList) {
        Result result = null;
        for (Rule<T> rule : ruleList) {
            result = rule.execute(bizData);
            if (result.isAvailable()) {
                // or 关系匹配成功一次，直接返回
                return result;
            }
        }
        return result;
    }
}
