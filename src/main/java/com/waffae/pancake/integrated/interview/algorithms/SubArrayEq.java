package com.waffae.pancake.integrated.interview.algorithms;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author: xiaoshuangyi
 * @Date: 2019-02-16 15:58
 * @Description: 和为K的子数组
 * https://leetcode.com/problems/subarray-sum-equals-k/discuss/102106/java-solution-presum-hashmap
 */
@Slf4j
public class SubArrayEq {

    public static void main(String[] args) {
        int[] a =new int[]{1,5,6,5,10,1,20};
        int k=11;
        int result = subArraySum(a,k);
        log.info("result:{}",result);

    }

    public static Integer subArraySum(int a[],int k){
        Map<Integer,Integer> map = Maps.newHashMap();
        int sum=0 ,count =0;
        map.put(0,1);

        for (int i=0,len = a.length;i<len;i++){
            sum +=a[i];
            if(map.containsKey(sum - k)){
                count+=map.get(sum-k);
            }
            map.put(sum ,map.getOrDefault(sum ,0)+1);
        }

        return count;
    }

}
