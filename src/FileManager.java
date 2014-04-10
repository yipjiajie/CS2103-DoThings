import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.logging.Logger;

class FileManager {
	private static final String MESSAGE_ERROR_LOGGER_READ = "Error reading from file ";
	private static final String MESSAGE_ERROR_LOGGER_WRITE = "Error writing to file ";
	@SuppressWarnings("deprecation")
	public static final String filepath = new File(URLDecoder.decode(DoThingsGUI.class.getProtectionDomain().getCodeSource().getLocation().getPath())).getParent() + System.getProperty("file.separator");
	private static Logger LOGGER = Logger.getLogger(FileManager.class.getName());
	
	private static BufferedReader getReader(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		BufferedReader bw;
		try {
			bw = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
			return bw;
		} catch (UnsupportedEncodingException e) {
			//LOGGER.info("Unsupported file encoding");
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static BufferedWriter getWriter(String fileName) throws IOException {
		File file = new File(fileName);
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF8"));
		return bw;
	}
	
	protected static ArrayList<String> readFromFile(String fileName) {
		//LOGGER.info("Reading from file " + fileName);
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			BufferedReader reader = getReader(filepath + fileName);
			String line;
			while ((line = reader.readLine()) != null) {
				list.add(line);
			}
		} catch (IOException e) {
			LOGGER.info( MESSAGE_ERROR_LOGGER_READ + fileName);
		}
		
		return list;
	}
	
	protected static void writeToFile(String fileName, ArrayList<String> list) {
		//LOGGER.info("Writing to file " + fileName);
		try {
			BufferedWriter writer = getWriter(filepath + fileName);
			for(int i = 0; i < list.size(); i++) {
				writer.write(list.get(i) + System.getProperty("line.separator"));
			}
			writer.flush();
			writer.close();
		} catch (IOException e) {
			//LOGGER.info(MESSAGE_ERROR_LOGGER_WRITE + fileName);
			e.printStackTrace();
		} finally {
			
		}
	}
}
