package examples.dto;

public class DirectorDTO {
	
	private String Dssn;
	private String Dname;
	
	public DirectorDTO(String dssn, String dname) {
		super();
		Dssn = dssn;
		Dname = dname;
	}
	
	public String getDssn() {
		return Dssn;
	}
	public void setDssn(String dssn) {
		Dssn = dssn;
	}
	public String getDname() {
		return Dname;
	}
	public void setDname(String dname) {
		Dname = dname;
	}
	
	@Override
	public String toString() {
		return "DirectorDTO [Dssn=" + Dssn + ", Dname=" + Dname + "]";
	}
	
}
