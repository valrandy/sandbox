package main.java.demo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Merger {

	/**
	 * Merge the previously split files into a single one, use a treemap to sort and
	 * remove duplicates from the smaller files, do this per line
	 * @param inputFiles
	 * @param outputFile
	 * 
	 * @throws IOException
	 */
	public void mergeFiles(List<File> inputFiles, File outputFile) throws IOException {
		BufferedReader[] reader = new BufferedReader[inputFiles.size()];
		Map<String, Integer> map = new TreeMap<String, Integer>();
		//sort and remove duplicates in the split files by putting them into a map
		for(int i=0; i<inputFiles.size(); i++) {
			reader[i] = new BufferedReader(new FileReader(inputFiles.get(i)));
			map.put(reader[i].readLine(), i);
		}
		//build the output file
		BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));		
		try {
			while(!map.isEmpty()) {
				String line = map.keySet().iterator().next();
				map.remove(line);
				writer.write(line);
				writer.newLine();
				for(int i=0; i<inputFiles.size(); i++) {
					line = reader[i].readLine();
					if(line !=null)
						map.put(line, i);
				}
			}
		} finally {
			writer.close();
			for(int i=0; i<inputFiles.size(); i++) {
				reader[i].close();
			}
		}
	}
}
