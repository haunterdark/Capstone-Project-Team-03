package entities;

public class Coupon {
	private String coupon_id;
	private String campaign_id;
	private String customer_id;
	private String coupon_createdate;
	private String coupon_status;

	public Coupon() {

	}

	public String getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(String coupon_id) {
		this.coupon_id = coupon_id;
	}

	public String getCampaign_id() {
		return campaign_id;
	}

	public void setCampaign_id(String campaign_id) {
		this.campaign_id = campaign_id;
	}

	public String getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(String customer_id) {
		this.customer_id = customer_id;
	}

	public String getCoupon_createdate() {
		return coupon_createdate;
	}

	public void setCoupon_createdate(String coupon_createdate) {
		this.coupon_createdate = coupon_createdate;
	}

	public String getCoupon_status() {
		return coupon_status;
	}

	public void setCoupon_status(String coupon_status) {
		this.coupon_status = coupon_status;
	}

}
