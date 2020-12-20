package de.jo.webserver;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

	public String getJarFilePath() {
		File jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").getPath());
		return jarDir.getAbsolutePath();
	}
	
	public static synchronized List<String> readFile(File file) {
		FileReader fileReader;
		List<String> tempList = new ArrayList<String>();

		try {
			fileReader = new FileReader(file);
		} catch (FileNotFoundException e) {

			return tempList;
		}

		BufferedReader reader = null;

		try {
			reader = new BufferedReader(fileReader);
			String line;
			while ((line = reader.readLine()) != null)
				tempList.add(line);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (reader != null)

				try {
					reader.close();
				} catch (IOException e) {

					e.printStackTrace();
				}
		}
		return tempList;
	}
	
}
