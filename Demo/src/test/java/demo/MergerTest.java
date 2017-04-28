package test.java.demo;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.java.demo.Merger;
import main.java.demo.Splitter;

public class MergerTest {

	@Test
	public void testMergeFiles() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file1 = new File(classLoader.getResource("test1.txt").getFile());
		File file2 = new File(classLoader.getResource("test2.txt").getFile());
		
		List<File> inputFiles = new ArrayList<File>();

		inputFiles.add(file1);
		inputFiles.add(file2);
		Splitter splitter = new Splitter(8*1024);
		List<File> tempFiles = splitter.splitFile(inputFiles);

		File outputFile = File.createTempFile("output", ".txt");
		Merger merger = new Merger();
		merger.mergeFiles(tempFiles, outputFile);
		BufferedReader reader = new BufferedReader(new FileReader(outputFile));
		int cnt = 0;
		while(reader.readLine() != null) {
			cnt++;
		}
		reader.close();
		assertEquals(cnt, 12);
		outputFile.delete();
	}
}
