package com.assemblychallenger.testes;

import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexVerificaLinhaTest {
        String input;
        @Test
        public void testRegex() {
            this.input = "String input: 30min";
            String regex = "(.*?)(\\d+)min|(.*?)(\\d+)minuteminutes|(.*?)(\\d+) min|(.*?)(\\d+) minute|(.*?)(\\d+) minutes|(.*?)(\\d+) minuto|(.*?)(\\d+) minutos";
            Boolean verificaRegex = false;

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                verificaRegex = input.contains(matcher.group());
                System.out.println(verificaRegex);

            }

        }
    }

