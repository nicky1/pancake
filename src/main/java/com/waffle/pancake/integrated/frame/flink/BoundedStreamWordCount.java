package com.waffle.pancake.integrated.frame.flink;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @desc: flink流式计算-单词统计demo
 * @author: yixiaoshuang
 * @date: 2022/6/20
 **/
public class BoundedStreamWordCount {

    public static void main(String[] args) throws Exception {
        // 1.创建流式的执行环境
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 2. 读取文件-继承dataStream
        DataStreamSource<String> source = env.readTextFile("input/words.txt");

        // 3.转换计算
        SingleOutputStreamOperator<Tuple2<String, Long>> tuple = source.flatMap((String line, Collector<Tuple2<String, Long>> out) -> {
            String[] words = line.split(" ");
            for (String s : words) {
                out.collect(Tuple2.of(s, 1L));
            }
        }).returns(Types.TUPLE(Types.STRING, Types.LONG));

        // 4.分组
        KeyedStream<Tuple2<String, Long>, String> stream = tuple.keyBy(data -> data.f0);

        // 5.累计统计
        SingleOutputStreamOperator<Tuple2<String, Long>> operator = stream.sum(1);

        operator.print();

        // 启动执行，相当于需要有个线程挂起执行
        env.execute();
    }
}
