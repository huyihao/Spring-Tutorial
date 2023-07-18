package life.majiang.community;

import java.io.*;

public class Book {
    public static void main(String[] args) throws IOException {
        FileInputStream fis = null;
        InputStreamReader isr = null;
        FileOutputStream fos = null;
        OutputStreamWriter osr = null;
        try {
            fis = new FileInputStream("C:\\Users\\huyihao\\Downloads\\jxs.txt");
            isr = new InputStreamReader(fis, "UTF-8");

            fos = new FileOutputStream("C:\\Users\\huyihao\\Downloads\\jxs2.txt");
            osr = new OutputStreamWriter(fos, "UTF-8");

            int count = 0;
            char[] chars = new char[60];
            while (isr.read(chars) != -1) {
                osr.write(String.valueOf(chars) + "\n");
                System.out.println("写入第" + (++count) + "行");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                fis.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (osr != null) {
                osr.close();
            }
            if (fos != null) {
                fos.close();
            }
        }
    }
}
