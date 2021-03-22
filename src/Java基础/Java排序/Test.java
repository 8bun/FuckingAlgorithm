package Java基础.Java排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author cwq
 * @since 2021/03/09
 */
public class Test {
    public static void main(String[] args) {
        /**
         * 对List排序
         */
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(122);
        list.add(-1);
        list.sort((a, b) -> {
            // 逆自然排序，即降序
            return b - a;
        });
        System.out.println(list.toString());
        /**
         * 对对象数组排序
         */
        Integer[] arr = new Integer[]{1, 122, -1};
        Arrays.sort(arr, (a, b) -> b - a);
        System.out.println(Arrays.toString(arr));
    }
}
