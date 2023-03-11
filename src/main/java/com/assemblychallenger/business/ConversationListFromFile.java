package com.assemblychallenger.business;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConversationListFromFile {
    private List<String> conversationList;
    public List<String> getConversationList() {
        return conversationList;
    }
    public void setConversationList(List<String> conversationList) {
        this.conversationList = conversationList;
    }

    public List<String> getConversationListFromFile(String fileName) throws Exception {
        conversationList = new ArrayList<String>();

            File fstream = new File(fileName);
            BufferedReader br = new BufferedReader(new FileReader(fstream));
            String stringLines;
            int i = 0;
            String regex = "(.*?)(\\d+)min|(.*?)(\\d+)minute|(.*?)(\\d+)minutes|(.*?)(\\d+) min|(.*?)(\\d+) minute|(.*?)(\\d+) minutes|(.*?)(\\d+) minuto|(.*?)(\\d+) minutos";
            Boolean verificaRegex = false;
            Matcher matcher;
            Pattern pattern = Pattern.compile(regex);
            while ((stringLines= br.readLine()) != null) {
                matcher = pattern.matcher(stringLines);
                if (matcher.find()) {
                    verificaRegex = stringLines.contains(matcher.group());

                }
                if (verificaRegex == true && !stringLines.contains("maintenance") || verificaRegex == true && stringLines.contains("maintenance")){
                    conversationList.add(stringLines);
                    //stringLines = br.readLine();
                    i++;
                }else {
                    System.out.println("Entrou");
                    break;
                }
            }

        return conversationList;
    }

    public List<String> conversetionFile(String fileName) throws IOException{
        List<String> assemlyLines = new ArrayList<>();
        File file = new File(fileName);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine();
        while ((line) != null) {

            assemlyLines.add(line);
        }
        return conversationList;
    }
}

