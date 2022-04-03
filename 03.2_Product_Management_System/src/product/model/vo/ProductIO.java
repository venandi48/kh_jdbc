package product.model.vo;

import java.sql.Timestamp;

public class ProductIO {

	private int no;
	private String productId;
	private int count;
	private char status;
	private Timestamp ioDatetme;

	public ProductIO() {
		super();
	}

	public ProductIO(int no, String productId, int count, char status, Timestamp ioDatetme) {
		super();
		this.no = no;
		this.productId = productId;
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

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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
		return "ProductIO [no=" + no + ", productId=" + productId + ", count=" + count + ", status=" + status
				+ ", ioDatetme=" + ioDatetme + "]";
	}

}
