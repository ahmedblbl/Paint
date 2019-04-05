package eg.egu.alexu.csd.oop.draw.saveJson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class xml implements xmlint{
	StringBuilder arr = new StringBuilder();
	String path;
	String filename;
	String attr = "";
	@Override
	public void opentag(String s) {
		// TODO Auto-generated method stub
		arr.append("<");
		arr.append(s);
		if(!attr.equals("")) {
			arr.append(" id=");
			arr.append("\"");
			arr.append(attr);
			arr.append("\"");
			attr = "";
		}
		arr.append(">");
	}

	@Override
	public void closetag(String s) {
		// TODO Auto-generated method stub
		arr.append("</");
		arr.append(s);
		arr.append(">");
		arr.append(System.getProperty("line.separator"));
	}

	@Override
	public void putval(String s) {
		// TODO Auto-generated method stub
		arr.append(s);
	}

	@Override
	public void puttattr(String s) {
		// TODO Auto-generated method stub
		attr = s;
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
		//String file_n = filename+ ".xml";
		String path_n = path;// +  File.separator + file_n;
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
	public void newline() {
		// TODO Auto-generated method stub
		arr.append(System.getProperty("line.separator"));
	}
	
}
