package com.waffle.pancake.jieba;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author yixiaoshuang
 * @date 2021/3/25 09:33
 */
public class JiebaTest extends TestCase {

    @Test
    public void testDemo() {
        JiebaSegmenter segmenter = new JiebaSegmenter();
        String s = "2、它还将支持中国公司与获得中国政府优惠贷款的国家签定合同";

        String[] sentences =
                new String[]{"2、它还将支持中国公司与获得中国政府优惠贷款的国家签定合同，进行资源开发和生产制造。"};
//        for (String sentence : sentences) {
//            System.out.println(segmenter.process(sentence, JiebaSegmenter.SegMode.INDEX).toString());
//        }

        List<SegToken> tokens = segmenter.process(s, JiebaSegmenter.SegMode.SEARCH);
        for (SegToken token : tokens) {
            System.out.println(token.toString());
        }

    }


}
