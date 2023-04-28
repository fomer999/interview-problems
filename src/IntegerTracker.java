import java.util.HashMap;
import java.util.Map;

// Write a program that tracks integers to calculate min, max, avg and mode of incoming integers

class IntegerTracker {
    private int min, max, sum, count, mode;
    private Map<Integer, Integer> numFrequencyMap;

    public IntegerTracker() {
        min = max = sum = count = 0;
        numFrequencyMap = new HashMap<Integer, Integer>();
    }

    public void track (int number) {
        count++;
        sum += number;

        if (count == 1) {
            min = number;
            max = number;
            mode = number;
        }

        if (number < min) {
            min = number;
        }

        if (number > max) {
            max = number;
        }

        if (numFrequencyMap.containsKey(number)) {
            numFrequencyMap.put(number, numFrequencyMap.get(number) + 1);
        } else {
            numFrequencyMap.put(number, 1);
        }

        if (numFrequencyMap.get(mode) < numFrequencyMap.get(number)){
            mode = number;
        }
    }

    public int min() {
        return min;
    }

    public int max() {
        return max;
    }

    public int avg() {
        if (count <= 0) {
            return 0;
        }

        return sum/count;
    }

    public int mode (){
        return mode;
    }
}


class Solution {
    public static void main(String[] args) {

        int[] intArray = {10,5,10,4,6};
        IntegerTracker integerTracker = new IntegerTracker();

        for (int i = 0; i < intArray.length; i++) {
            integerTracker.track(intArray[i]);
        }
        System.out.println("Max int : " + integerTracker.max());
        System.out.println("Min int : " + integerTracker.min());
        System.out.println("Average : " + integerTracker.avg());
        System.out.println("Mode : " + integerTracker.mode());
    }
}
