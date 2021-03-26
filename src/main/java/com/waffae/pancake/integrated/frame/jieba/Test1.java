package com.waffae.pancake.integrated.frame.jieba;

import com.google.common.base.Splitter;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;

import java.util.List;

/**
 * @author yixiaoshuang
 * @date 2021/3/25 10:26
 */
public class Test1 {

    public static void main(String[] args) {
        JiebaSegmenter segmenter = new JiebaSegmenter();

        String s = "2、物质的比热容是使单位质量的物质温度升高1度所须加给的热量。";

        List<SegToken> tokens = segmenter.process(s, JiebaSegmenter.SegMode.SEARCH);
        System.out.println(tokens.toString());

        String s3 = "2/m 、/wn 物质/n 的/ude1 比热/n 容/v 是/vshi 使/v 单位/n 质量/n 的/ude1 物质/n 温度/n 升高/v 1/m 度/qv 所/usuo 须/d 加/v 给/u 的/ude1 热量/n 。/wj ";

        List<String> list = Splitter.on("/").trimResults().splitToList(s3);
        for (String str : list) {
            System.out.println(str);
        }
    }
}
