package eg.egu.alexu.csd.oop.draw.saveJson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;

import javax.print.DocFlavor.URL;



public class SaveJson implements WriteJson{
	StringBuilder arr = new StringBuilder();
	String path;
	String filename;
	@Override
	public void putJson(String key, String value) {
		// TODO Auto-generated method stub
		arr.append("\"");
		arr.append(key);
		arr.append("\"");
		arr.append(":");
		arr.append("\"");
		arr.append(value);
		arr.append("\"");
		arr.append(",");
		addNewLine();
	}

	@Override
	public void startArr() {
		// TODO Auto-generated method stub
		arr.append("{");
	}

	@Override
	public void endArr() {
		// TODO Auto-generated method stub
		for (int i = arr.length()-1 ; i >= 0 ; i--) {
			if (arr.charAt(i) == ',') {
				arr.deleteCharAt(i);
				break;
			}
		}
		arr.append("}");
		arr.append(System.getProperty("line.separator"));
		arr.append(",");
	}

	@Override
	public void setFile(String Path) {
		// TODO Auto-generated method stub
		path = Path;
		//filename = FileName;
	}

	@Override
	public void saveFile() {
		// TODO Auto-generated method stub
		//String file_n = filename+ ".json";
		String path_n = path ;//+  File.separator + file_n;
		File f = new File(path_n);
		if (!path_n.contains("\\")) {
			 f = new File(path_n);
		} else {
		    f = new File(path_n);
			f.getParentFile().mkdirs(); 
		}
		try {
			f.createNewFile();
			try(FileWriter fileWriter = new FileWriter(path_n)){
				fileWriter.write(arr.toString());
				arr.delete(0, arr.length()-1);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void getArrName(String name) {
		// TODO Auto-generated method stub
		arr.append("\"");
		arr.append(name);
		arr.append("\"");
		arr.append(":");
		addNewLine();
		arr.append("[");
		addNewLine();
	}

	@Override
	public void getEndArr() {
		// TODO Auto-generated method stub
		arr.deleteCharAt(arr.length() - 1);
		arr.append("]");
		addNewLine();
		arr.append("}");
		arr.append(System.getProperty("line.separator"));
	}

	@Override
	public void addNewLine() {
		// TODO Auto-generated method stub
		arr.append(System.getProperty("line.separator"));
	}
	
}
