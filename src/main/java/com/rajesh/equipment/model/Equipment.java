package com.rajesh.equipment.model;

public class Equipment 
{
	private String _id;
    private String _rev;
    private String eqipment_number;
    private String address;
    private String contract_start_date;
    private String contract_end_date;
    private boolean status;
    
    
	public String get_id() {
		return _id;
	}
	public void set_id(String _id) {
		this._id = _id;
	}
	public String get_rev() {
		return _rev;
	}
	public void set_rev(String _rev) {
		this._rev = _rev;
	}
	public String getEqipment_number() {
		return eqipment_number;
	}
	public void setEqipment_number(String eqipment_number) {
		this.eqipment_number = eqipment_number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContract_start_date() {
		return contract_start_date;
	}
	public void setContract_start_date(String contract_start_date) {
		this.contract_start_date = contract_start_date;
	}
	public String getContract_end_date() {
		return contract_end_date;
	}
	public void setContract_end_date(String contract_end_date) {
		this.contract_end_date = contract_end_date;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	@Override
	public String toString() {
		return "Equipment [_id=" + _id + ", _rev=" + _rev + ", eqipment_number=" + eqipment_number + ", address="
				+ address + ", contract_start_date=" + contract_start_date + ", contract_end_date=" + contract_end_date
				+ ", status=" + status + "]";
	}
	
}
