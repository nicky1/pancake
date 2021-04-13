package com.waffae.pancake.integrated.frame.jieba;

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

        String s = "1、8．寻找复活节彩蛋的习俗，据民间传说，是由一位德国公爵夫人兴起的。";
        String s2 ="比热容";

        List<SegToken> tokens = segmenter.process(s, JiebaSegmenter.SegMode.INDEX);
        System.out.println(tokens.toString());



    }
}
