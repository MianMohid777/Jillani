package Lab_3;

import java.util.HashMap;
import java.util.Map;

public class FrequencyCounter {

    private String str;

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void freqFinder()
    {
        HashMap<String,Integer> strMap = new HashMap<>();

        String[] arr = str.split("[\\p{Punct}\\s]+");


        for(String s:arr)
        {
            int count = strMap.getOrDefault(s.toLowerCase(),0);
            strMap.put(s.toLowerCase(),count+1);

        }

        for (Map.Entry<String, Integer> entry : strMap.entrySet()) {
            String word = entry.getKey();
            Integer freq = entry.getValue();
            System.out.println("Word-> : " + word + " with Freq: " + freq);
        }

    }

    public static void main(String[] args)
    {
        FrequencyCounter f = new FrequencyCounter();

        f.setStr("This is a sample text. This text contains sample words for counting words frequency");

        f.freqFinder();
    }
}
