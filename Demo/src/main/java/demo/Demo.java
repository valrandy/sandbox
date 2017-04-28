package main.java.demo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Demo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Splitter splitter = new Splitter(8*1024);
		Merger merger = new Merger();
		File file1 = new File("C:\\test\\test1.txt");
		File file2 = new File("C:\\test\\test2.txt");
		List<File> inputFiles = new ArrayList<File>();
		inputFiles.add(file1);
		inputFiles.add(file2);
		List<File> tempFiles = splitter.splitFile(inputFiles);
		merger.mergeFiles(tempFiles, new File("C:\\test\\output.txt"));
	}

}
