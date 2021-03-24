package com.bighao.easy;

import java.util.Arrays;

/**
 * @Author: bighao周启豪
 * @Date: 2020/4/17 17:29
 * @Version 1.0
 * <p>
 * https://leetcode-cn.com/problems/maximum-number-of-balloons/
 * <p>
 * 字符串匹配
 */
public class CodeEasyString1189 {

    public static void main(String[] args) {
        String text = "krhizmmgmcrecekgyljqkldocicziihtgpqwbticmvuyznragqoyrukzopfmjhjjxemsxmrsxuqmnkrzhgvtgdgtykhcglurvppvcwhrhrjoislonvvglhdciilduvuiebmffaagxerjeewmtcwmhmtwlxtvlbocczlrppmpjbpnifqtlninyzjtmazxdbzwxthpvrfulvrspycqcghuopjirzoeuqhetnbrcdakilzmklxwudxxhwilasbjjhhfgghogqoofsufysmcqeilaivtmfziumjloewbkjvaahsaaggteppqyuoylgpbdwqubaalfwcqrjeycjbbpifjbpigjdnnswocusuprydgrtxuaojeriigwumlovafxnpibjopjfqzrwemoinmptxddgcszmfprdrichjeqcvikynzigleaajcysusqasqadjemgnyvmzmbcfrttrzonwafrnedglhpudovigwvpimttiketopkvqw";
        //String text = "loonbalxballpoon";
        int i = maxNumberOfBalloons2(text);
        System.out.println(i);
    }

    public static int maxNumberOfBalloons(String text) {
        char[] tc = text.toCharArray();
        int[] res = {0, 0, 0, 0, 0};

        for (int i = 0; i < tc.length; i++) {
            switch (tc[i]) {
                case 'b':
                    res[0] = res[0] + 1;
                    break;
                case 'a':
                    res[1] = res[1] + 1;
                    break;
                case 'l':
                    res[2] = res[2] + 1;
                    break;
                case 'o':
                    res[3] = res[3] + 1;
                    break;
                case 'n':
                    res[4] = res[4] + 1;
                    break;
            }
        }

        res[2] = res[2] / 2;
        res[3] = res[3] / 2;
        Arrays.sort(res);
        return res[0];
    }

    /**
     * 第二种解法，用26个字母的顺序来作为坑位，填坑，然后计算个数
     * @param text
     * @return
     */
    public static int maxNumberOfBalloons2(String text) {
        int[] letters = new int[26];
        for (char ch : text.toCharArray()) {
            //letters[ch - '97']++;
            letters[ch - 97]++;
        }
        letters['l' - 97] /= 2;
        letters['o' - 97] /= 2;
        int min = Integer.MAX_VALUE;
        for (char ch : "balon".toCharArray()) {
            if (letters[ch - 97] < min) {
                min = letters[ch - 97];
            }
        }
        return min;
    }

}