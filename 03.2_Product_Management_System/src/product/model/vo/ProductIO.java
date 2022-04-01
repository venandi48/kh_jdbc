package product.model.vo;

import java.sql.Timestamp;

public class ProductIO {

	private int no;
	private String product_id;
	private int count;
	private char status;
	private Timestamp ioDatetme;

	public ProductIO() {
		super();
	}

	public ProductIO(int no, String product_id, int count, char status, Timestamp ioDatetme) {
		super();
		this.no = no;
		this.product_id = product_id;
		this.count = count;
		this.status = status;
		this.ioDatetme = ioDatetme;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getProduct_id() {
		return product_id;
	}

	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public Timestamp getIoDatetme() {
		return ioDatetme;
	}

	public void setIoDatetme(Timestamp ioDatetme) {
		this.ioDatetme = ioDatetme;
	}

	@Override
	public String toString() {
		return "ProductIO [no=" + no + ", product_id=" + product_id + ", count=" + count + ", status=" + status
				+ ", ioDatetme=" + ioDatetme + "]";
	}

}
