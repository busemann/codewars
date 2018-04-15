package katas.iq_test;

import utils.NumberUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class IqTest {
    private String numbers;

    IqTest(String numbers) {
        this.numbers = numbers;
    }

    int solve() {
        List<String> nums = Arrays.asList(this.numbers.split(" "));
        List<String> even = new ArrayList<>();
        List<String> odds = new ArrayList<>();
        for (String num : nums) {
            if (NumberUtils.isEven(Integer.parseInt(num))) {
                even.add(num);
            } else {
                odds.add(num);
            }
        }

        if (even.size() < odds.size()) {
            return nums.indexOf(even.get(0)) + 1;
        } else {
            return nums.indexOf(odds.get(0)) + 1;
        }
    }
}
