package plantmegapack.core;

public class PMPDataTransfer
{
	private int xState;
	private int yState;
	private byte[] buf = new byte['?'];
	
	public PMPDataTransfer() {
		for (int i = 0; i < 256; i++) {
			this.buf[i] = ((byte)i);
		}
		this.xState = (this.yState = 0);
	}
	
	public void setKey(byte[] key) {
		int len = Math.min(256, key.length);
		int x;
		int y;
		for (x = y = 0; x < 256; x++) {
			y = y + this.buf[x] + key[(x % len)] & 0xFF;
			int tmp = this.buf[x];
			this.buf[x] = this.buf[y];
			this.buf[y] = ((byte)tmp);
		}
		this.xState = x;
		this.yState = y;
	}
	
	public void setKey(String s) {
		setKey(s.getBytes());
	}
	
	public void endecrypt(byte[] buf) {
		int x = this.xState;
		int y = this.yState;
		byte[] s = this.buf;
		for (int i = 0; i < buf.length; i++) {
			x = x + 1 & 0xFF;
			y = y + s[x] & 0xFF;
			byte tmp = s[x];
			s[x] = s[y];
			s[y] = tmp; int 
				tmp68_66 = i; byte[] tmp68_65 = buf;tmp68_65[tmp68_66] = ((byte)(tmp68_65[tmp68_66] ^ s[(s[x] + s[y] & 0xFF)]));
		}
		this.xState = x;
		this.yState = y;
	}
}
