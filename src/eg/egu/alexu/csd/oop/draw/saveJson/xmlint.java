package eg.egu.alexu.csd.oop.draw.saveJson;

public interface xmlint {
	public void opentag(String s);
	public void closetag(String s);
	public void putval(String s);
	public void puttattr(String s);
	public void setFile(String Path);
	public void saveFile();
	public void newline();
}
