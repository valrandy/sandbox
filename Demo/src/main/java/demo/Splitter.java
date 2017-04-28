package main.java.demo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.EOFException;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Splitter {

	int bufferSize; //the size of the smaller files for example 10000 * 1024 = 100MB
	Set<String> set = new TreeSet<String>();
	
	public Splitter(int bufferSize) {
		this.bufferSize = bufferSize;
	}
	
	/**
	 * Write a temporary file containing the sorted and unique lines
	 * @param set
	 * @return
	 * @throws IOException
	 */
	private File createTempFile(Set<String> set) throws IOException {
		//write to temporary file
		File newFile = File.createTempFile("split", ".txt");
		newFile.deleteOnExit();
		BufferedWriter writer = new BufferedWriter(new FileWriter(newFile));
		try {
			for(String entry : set) {
				//write per line
				writer.write(entry);
				writer.newLine();
			}
		} finally {
			writer.close();
		}
		return newFile;
	}
	
	public List<File> splitFile(List<File> inputFiles) throws IOException {
		List<File> splitFiles = new ArrayList<File>();
		BufferedReader reader;
		for(int i=0; i< inputFiles.size(); i++) {
			File inputFile = inputFiles.get(i);
			reader = new BufferedReader(new FileReader(inputFile));
			String line = "";
			try {
				while(line != null) {
					long temp = 0;
					while(temp < bufferSize && (line = reader.readLine()) != null) {
						//read per line
						set.add(line);
						temp += line.length();
					}
					splitFiles.add(createTempFile(set));
					//increment file count for name and clear set
					set.clear();
				}
			} catch(EOFException  e) {
				if(set.size() > 0) {
					splitFiles.add(createTempFile(set));
					set.clear();
				}
			} finally {
				reader.close();
			}
		}
		return splitFiles;
	}
}
