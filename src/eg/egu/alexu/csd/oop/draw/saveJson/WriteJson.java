package eg.egu.alexu.csd.oop.draw.saveJson;

public interface WriteJson {
	
	public void putJson (String key, String value);
	public void startArr ();
	public void endArr();
	public void setFile(String Path);
	public void saveFile();
	public void getArrName(String name);
	public void getEndArr();
	public void addNewLine();
}
