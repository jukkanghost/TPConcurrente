public class Buffer {
	private int id;
	private RedDePetri rdp;
	
	public Buffer(RedDePetri rdp, int id) {
		this.rdp=rdp;
		this.id=id;
	}
	
	public int getElementos() {
		if(id==1) {
			return rdp.getElementosBuffer1();
		}
		else if(id==2) {
			return rdp.getElementosBuffer2();
		}
		return -1;
	}

}

