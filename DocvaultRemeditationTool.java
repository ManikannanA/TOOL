package net.nwie.awdtool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DocvaultRemeditationTool {

	static final String sourceFilePath = "F:\\AWD\\docvault\\recovery";
	static final String successFilePath = "F:\\AWD\\docvault\\recovery";
	static final String searchFilePath = "F:\\AWD\\server\\success";
	static final String reprocessFilePath = "F:\\AWD\\docvault";

	public static void main(String[] args) {
		File sourceDir = new File(sourceFilePath);

		File[] nameFilterfiles = sourceDir.listFiles(nameFilter);
		moveFile(nameFilterfiles, successFilePath);

		File[] sizeFilterfiles = sourceDir.listFiles(sizeFilter);
		moveFile(sizeFilterfiles, successFilePath);

		File[] searchFilterfiles = sourceDir.listFiles(searchFilter);
		searchFile(searchFilterfiles);

		File[] reprocessFile = sourceDir.listFiles(processFilter);
		moveFile(reprocessFile, reprocessFilePath);
	}

	private static void searchFile(File[] files) {
		if (files.length == 0) {
			System.out.println("There is no such files");
		} else {
			for (File aFile : files) {
				try {
					if (aFile.getName().contains("fn-awd")) {
						System.out.println("aFile.getName()>>>>" + aFile.getName());
						File searchDir = new File(searchFilePath);
						Path path = Paths.get(aFile.getName());
						Path fileName = path.getFileName();
						File[] searchFilterfiles = searchDir.listFiles();
						System.out.println("searchFilterfilesut " + searchFilterfiles.length);
						try {
							deleteLine(aFile.getAbsolutePath(), searchFilterfiles);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void deleteLine(String fileName, File[] searchFilterfiles) {
		try {
			BufferedReader file = new BufferedReader(new FileReader(fileName));
			String line;
			String input = "";
			while ((line = file.readLine()) != null) {
				System.out.println(line);
				Pattern p = Pattern.compile(".*\\{ *(.*) *\\}.*");
				Matcher m = p.matcher(line);
				m.find();
				String guidString = m.group(1);
				System.out.println("guidString" + guidString);

				for (File xmlFile : searchFilterfiles) {
					String guidMatchContents = convertDocumentToString(xmlFile);
					if (guidMatchContents.contains(guidString)) {
						line = "";
						System.out.println("Line deleted.");
					}
				}
				if (line != "") {
					input += line + '\n';
				}
				FileOutputStream File = new FileOutputStream(fileName);
				File.write(input.getBytes());
				File.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problem reading file.");
		}
	}

	private static String convertDocumentToString(File file)
			throws ParserConfigurationException, SAXException, IOException {

		// an instance of factory that gives a document builder
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// an instance of builder to parse the specified xml file
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document doc = db.parse(file);

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer transformer;
		try {
			transformer = tf.newTransformer();
			StringWriter writer = new StringWriter();
			transformer.transform(new DOMSource(doc), new StreamResult(writer));
			String output = writer.getBuffer().toString();
			return output;
		} catch (TransformerException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void moveFile(File[] files, String filePath) {
		File destDir = new File(filePath);
		if (files.length == 0) {
			System.out.println("There is no available file in recovery folder....");
		} else {
			for (File aFile : files) {
				try {
					if (!destDir.exists()) {
						destDir.mkdir();
					} else {
						aFile.renameTo(new File(destDir + "\\" + aFile.getName()));
						System.out.println(aFile.getName() + " - " + aFile.length());

					}
				} catch (Exception exp) {
					System.out.println(exp);
				}

			}
		}
	}

	static FilenameFilter nameFilter = new FilenameFilter() {
		public boolean accept(File file, String name) {
			if (name.contains("csr-void")) {
				// filters files whose extension is "csr-void"
				return true;
			} else {
				return false;
			}
		}
	};

	static FileFilter sizeFilter = new FileFilter() {
		public boolean accept(File file) {
			if (file.isFile() && file.length() <= 1 * 1024) {
				// filters files whose size less than or equal to 1KB
				return true;
			} else {
				return false;
			}
		}
	};

	static FileFilter searchFilter = new FileFilter() {
		public boolean accept(File file) {
			if (file.isFile() && file.length() <= 1 * 1024) {
				// filters files whose size more than to 1KB
				return true;
			} else {
				return false;
			}
		}
	};

	static FilenameFilter processFilter = new FilenameFilter() {
		public boolean accept(File file, String name) {
			if (name.contains("csr-void")) {
				// filters files whose extension is "csr-void"
				return true;
			} else {
				return false;
			}
		}
	};
}
