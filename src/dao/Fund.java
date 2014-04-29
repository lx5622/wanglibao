package dao;

public class Fund {

	private String head; //信息头
	private int number; //人数
	private String  rates;//利率
	private String  start;//起步价
	public String getHead() {
		return head;
	}
	public void setHead(String head) {
		this.head = head;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getRates() {
		return rates;
	}
	public void setRates(String rates) {
		this.rates = rates;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	@Override
	public String toString() {
		return "Fund [head=" + head + ", number=" + number + ", rates=" + rates
				+ ", start=" + start + "]";
	}
	
	
}
