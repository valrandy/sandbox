package test.java.demo;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import main.java.demo.Splitter;

public class SplitterTest {

	@Test
	public void testSplitFile() throws IOException {
		ClassLoader classLoader = getClass().getClassLoader();
		File file1 = new File(classLoader.getResource("test1.txt").getFile());
		File file2 = new File(classLoader.getResource("test2.txt").getFile());
		
		List<File> inputFiles = new ArrayList<File>();

		inputFiles.add(file1);
		inputFiles.add(file2);
		Splitter splitter = new Splitter(1024);
		List<File> tempFiles = splitter.splitFile(inputFiles);
		assertNotNull(tempFiles);
		assertTrue(tempFiles.size()<6);
		for(int i=0; i<tempFiles.size(); i++) {
			tempFiles.get(i).delete();
		}
	}
}
