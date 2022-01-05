package dex;



public class Orden
{
		
	private int chk_num;
	private String name;
	private boolean MopEnviado = false;
	
	
	public int getChk() {
		return chk_num;
	}
	public void setChk(int chk_num) {
		this.chk_num = chk_num;
	}
	
	public String getname() {
		return name;
	}
	public void setname(String name) {
		this.name = name;
	}
	public boolean getMopEnv() {
		return MopEnviado;
	}
	public void setMopEnv(boolean MopEnviado) {
		this.MopEnviado = MopEnviado;
	}


}
