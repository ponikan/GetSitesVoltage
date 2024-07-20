
// получение из общего списка строк только тех, где указано напряжение:

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GetVoltageMap {

    public static Map<String, Cabinet> getVoltageMap() {

        List<String> siteNameList = GetAllWorkingSitesList.getWorkingSites();
        List<String> allStringsFromMMlFile = ReadAllStringsFromMMLFile.getAllStrings();
         Map<String, Cabinet> pointsMap = new HashMap<>();

        int counter = -1;
        Pattern pattern = Pattern.compile("\\d.*"); // регулярка на цифры

        for (int i = 0; i < allStringsFromMMlFile.size(); i++) {
            String str = allStringsFromMMlFile.get(i);
            if (str.equals("(Number of results = 1)")) {

                counter++;
                str = allStringsFromMMlFile.get(i - 1).trim();
                str = str.substring(str.length() - 3);
                Matcher matcher = pattern.matcher(str);

                if (!matcher.matches()) { // если не цифры
                    str = allStringsFromMMlFile.get(i - 1).trim();
                    str = str.substring(str.length() - 4);
                    Cabinet cabinet = new OnePowerCabinet(str);
                    pointsMap.put(siteNameList.get(counter), cabinet);
                } else {
                    Double volt = Double.parseDouble(str) / 10;
                    Cabinet cabinet = new OnePowerCabinet(volt);
                    pointsMap.put(siteNameList.get(counter), cabinet);
                }
            } else if (str.startsWith("(Number of results = 2)")) {
                counter++;

                String str1 = allStringsFromMMlFile.get(i - 2).trim();
                str1 = str1.substring(str1.length() - 3);
                String str2 = allStringsFromMMlFile.get(i - 1).trim();
                str2 = str2.substring(str2.length() - 3);
                Matcher matcher1 = pattern.matcher(str1);
                Matcher matcher2 = pattern.matcher(str2);
                if (matcher1.matches() && matcher2.matches()) {
                    Double volt1 = Double.parseDouble(str1) / 10;
                    Double volt2 = Double.parseDouble(str2) / 10;
                    Cabinet cabinet = new TwoPowerCabinet(volt1, volt2);
                    pointsMap.put(siteNameList.get(counter), cabinet);
                } else if (!matcher1.matches() && matcher2.matches()) {

                    str1 = allStringsFromMMlFile.get(i - 2).trim();
                    str1 = str1.substring(str1.length() - 4);

                    String volt1 = str1;
                    Double volt2 = Double.parseDouble(str2) / 10;
                    Cabinet cabinet = new TwoPowerCabinet(volt1,volt2);
                    pointsMap.put(siteNameList.get(counter), cabinet);
                } else if (matcher1.matches() && !matcher2.matches()) {
                    str2 = allStringsFromMMlFile.get(i - 1).trim();
                    str2 = str2.substring(str2.length() - 4);
                    Double volt1 = Double.parseDouble(str1) / 10;
                    String volt2 = str2;
                    Cabinet cabinet = new TwoPowerCabinet(volt1,volt2);
                    pointsMap.put(siteNameList.get(counter), cabinet);
                } else {
                    str1 = allStringsFromMMlFile.get(i - 2).trim();
                    str1 = str1.substring(str1.length() - 4);
                    str2 = allStringsFromMMlFile.get(i - 1).trim();
                    str2 = str2.substring(str2.length() - 4);
                    String volt1 = str1;
                    String volt2 = str2;
                    Cabinet cabinet = new TwoPowerCabinet(volt1,volt2);
                    pointsMap.put(siteNameList.get(counter), cabinet);
                }
            }
        }
        return pointsMap;
    }
}
