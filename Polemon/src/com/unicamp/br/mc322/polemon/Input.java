package com.unicamp.br.mc322.polemon;

import java.io.*;

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
}
