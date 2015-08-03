package com.ga.erp.biz.comm;

public class String2PinYin {

	//��ĸZʹ����������ǩ�������У�����ֵ  
    //i, u, v��������ĸ, ����ǰ�����ĸ  
    private char[] chartable =  
            {  
                '��', '��', '��', '��', '��', '��', '��', '��', '��',  
                '��', '��', '��', '��', '��', 'Ŷ', 'ž', '��', 'Ȼ',  
                '��', '��', '��', '��', '��', '��', 'ѹ', '��', '��'  
            };  
  
    private char[] alphatable =  
            {  
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',  
                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',   
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'  
            };  
  
  
    private int[] table = new int[27];  
    //��ʼ��  
    {  
        for (int i = 0; i < 27; ++i) {  
            table[i] = gbValue(chartable[i]);  
        }  
    }  
  
    //������,�����ַ�,�õ�������ĸ,  
    //Ӣ����ĸ���ض�Ӧ�Ĵ�д��ĸ  
    //�����Ǽ��庺�ַ��� '0'  
  
    public char Char2Alpha(char ch) {  
  
        if (ch >= 'a' && ch <= 'z')  
            return (char) (ch - 'a' + 'A');  
        if (ch >= 'A' && ch <= 'Z')  
            return ch;  
        int gb = gbValue(ch);  
        if (gb < table[0])  
            return ch;  
        int i;  
        for (i = 0; i < 26; ++i) {  
            if (match(i, gb)) break;  
        }  
        if (i >= 26)  
            return '0';  
        else  
            return alphatable[i];  
    }  
  
    //����һ���������ֵ��ַ�������һ������ƴ������ĸ���ַ���  
    public String String2Alpha(String SourceStr){  
          
        String Result = "";  
        int StrLength = SourceStr.length();  
        int i;  
        try {  
            for (i = 0; i < StrLength; i++) {  
                Result += Char2Alpha(SourceStr.charAt(i));  
            }  
        } catch (Exception e) {  
            Result = "";  
        }  
        return Result;  
    }  
  
    private boolean match(int i, int gb) {  
          
        if (gb < table[i])  
            return false;  
        int j = i + 1;  
  
        //��ĸZʹ����������ǩ  
        while (j < 26 && (table[j] == table[i])) ++j;  
        if (j == 26)  
            return gb <= table[j];  
        else  
            return gb < table[j];  
    }  
  
    //ȡ�����ֵı���  
    private int gbValue(char ch) {  
          
        String str = new String();  
        str += ch;  
        try {  
            byte[] bytes = str.getBytes("GB2312");  
            if (bytes.length < 2)  
                return 0;  
            return (bytes[0] << 8 & 0xff00) + (bytes[1] &  
                    0xff);  
        } catch (Exception e) {  
            return 0;  
        }  
    }  
  
    /**
     * ���ַ���ת����ƴ������ĸ
     * @param sourceStr ��Ҫת�����ַ���
     * @return  ƴ������ĸ
     */
    public String transform(String sourceStr) {  
        return String2Alpha(sourceStr);  
    } 
    
}
