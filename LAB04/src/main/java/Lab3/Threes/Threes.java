package Lab3.Threes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Threes {
    public List<List<Integer>> sumOfThrees(int[] nums){
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            int opposite = -nums[i]; //przeciwność pierwszego
            int left = i + 1; //drugi
            int right = nums.length - 1;//ostatni

            while (left < right) {
                int sum = nums[left] + nums[right];

                if (sum == opposite) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    //uniknięcie sprawdzania duplikatów
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;
                    left++;
                    right--;
                }
                else if (sum < opposite) {
                    left++; //jeśli suma jest za mala to potrzeba zwiekszyc -> przesuwamy z lewej
                }
                else { //analogicznie -> przesuwamy z prawej
                    right--;
                }
            }
        }
        return result;
    }

}
