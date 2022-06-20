package com.waffle.pancake.integrated.frame.flink;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * @desc: flink helloword
 * @author: xsyi
 * @date: 2022/6/19
 **/
public class BatchWordCount {


    /**
     * 模拟从文件中读取数据,计算词组出现概率
     */
    public static void main(String[] args) throws Exception {
        // 创建执行环境
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        // 2.从文件中读取数据
        DataSource<String> source = env.readTextFile("input/words.txt");

        // 3. 转换-二元组
        FlatMapOperator<String, Tuple2<String, Long>> tuple = source.flatMap((String line, Collector<Tuple2<String, Long>> out) -> {
            String[] words = line.split(" ");
            for (String s : words) {
                out.collect(Tuple2.of(s, 1L));
            }
        }).returns(Types.TUPLE(Types.STRING, Types.LONG));

        // 4.按照word进行分组
        UnsortedGrouping<Tuple2<String, Long>> group = tuple.groupBy(0);

        // 5.分组内进行聚合统计
        AggregateOperator<Tuple2<String, Long>> agg = group.sum(1);

        // 6.打印结果
        agg.print();
    }
}
