package com.unicamp.br.mc322.polemon;

import java.io.*;
import java.nio.file.*;

public final class Input {

	public static String readKeyboard() {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try{
			String ret = br.readLine();
			br.close();
			return ret;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}

		return "";
	}

	public static int countLines (String path) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String strLine;
			int count = 0;
			while ((strLine = br.readLine()) != null) 
				count++;
			
			return count;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return 0;
	}

	public static String readFile (String path) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}

			br.close();

			return sb.toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return "";
	}
	
	public static String readLineFromFile(String path, int n) {
		try {
			return Files.readAllLines(Paths.get(path)).get(n);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "";
	}
}
