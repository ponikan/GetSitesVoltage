
// получение из общего списка строк только тех, где указано напряжение:
// Input Voltage(0.1V)  =  8888888
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class GetVoltageMap {

    public static Map<String, Cabinet> getVoltageMap() {

        List<String> siteNameList = GetAllWorkingSitesNameList.getWorkingSites();
        List<String> allStringsFromMMlFile = ReadAllStringsFromMMLFile.getAllStrings();
         Map<String, Cabinet> pointsMap = new TreeMap<>();

        int counter = -1;
      //  Pattern pattern = Pattern.compile("\\d.*"); // регулярка на цифры
        Pattern patternDigit = Pattern.compile("\\d+$"); // регулярка на цифры
        Pattern patternNotDigit = Pattern.compile("\\D+$"); // регулярка на нецифры


        for (int i = 0; i < allStringsFromMMlFile.size(); i++) {
            String str = allStringsFromMMlFile.get(i);
            if (str.equals("(Number of results = 1)")) {
                counter++;
                str = allStringsFromMMlFile.get(i - 1).trim();
                Matcher matcherDigit = patternDigit.matcher(str);
                if (matcherDigit.find()){ // если цифры
                    str = matcherDigit.group(0);
                    Double volt = Double.parseDouble(str) / 10;
                    Cabinet cabinet = new OnePowerCabinet(volt);
                    pointsMap.put(siteNameList.get(counter), cabinet);
                }else { // если не цифры
                    str = allStringsFromMMlFile.get(i - 1).trim();
                    str = str.substring(str.length() - 4);
                    Cabinet cabinet = new OnePowerCabinet(str);
                    pointsMap.put(siteNameList.get(counter), cabinet);
                }
            } else if (str.startsWith("(Number of results = 2)")) {
                counter++;

                String str1 = allStringsFromMMlFile.get(i - 2).trim();
                String str2 = allStringsFromMMlFile.get(i - 1).trim();

//                str1 = str1.substring(str1.length() - 3);
//                str2 = str2.substring(str2.length() - 3);

                Matcher matcherDigit1 = patternDigit.matcher(str1);
                Matcher matcherDigit2 = patternDigit.matcher(str2);

                if (matcherDigit1.find() && matcherDigit2.find()) {
                    str1 = matcherDigit1.group(0);
                    str2 = matcherDigit2.group(0);
                    Double volt1 = Double.parseDouble(str1) / 10;
                    Double volt2 = Double.parseDouble(str2) / 10;
                    Cabinet cabinet = new TwoPowerCabinet(volt1, volt2);
                    pointsMap.put(siteNameList.get(counter), cabinet);
                } else if (!matcherDigit1.find() && matcherDigit2.find()) {
                  //  str1 = allStringsFromMMlFile.get(i - 2).trim();
                    str1 = str1.substring(str1.length() - 4);
                    String volt1 = str1;
                    str2 = matcherDigit2.group(0);
                    Double volt2 = Double.parseDouble(str2) / 10;
                    Cabinet cabinet = new TwoPowerCabinet(volt1,volt2);
                    pointsMap.put(siteNameList.get(counter), cabinet);
                } else if (matcherDigit1.find() && !matcherDigit2.find()) {
                   // str2 = allStringsFromMMlFile.get(i - 1).trim();
                    str2 = str2.substring(str2.length() - 4);
                    str1 = matcherDigit1.group(0);
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
