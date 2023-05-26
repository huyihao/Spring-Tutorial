package demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;

public class FileLine {

	public static void main(String[] args) throws IOException {
		String line = System.getProperty("line.separator");
		FileInputStream fis = null;
		FileOutputStream fos = null;
		InputStreamReader isr = null;
		OutputStreamWriter osw = null;
		BufferedReader br = null;
		BufferedWriter bw = null;
		try {
			fis = new FileInputStream("C:\\Users\\huyihao\\Downloads\\all.txt");
			fos = new FileOutputStream("C:\\Users\\huyihao\\Downloads\\all2.txt");
			isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
			osw = new OutputStreamWriter(fos, Charset.forName("UTF-8"));
			br = new BufferedReader(isr);
			bw = new BufferedWriter(osw);
			
			char[] chars = new char[60];
			int linenum = 0;
			while (br.read(chars) != -1) {
				bw.write(chars);
				bw.write(line);
				bw.write(line);
				System.out.println("line " + ++linenum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			br.close();
			bw.close();
			isr.close();
			osw.close();
			fis.close();
			fos.close();
		}
	}
	
}
