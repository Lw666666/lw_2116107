package com.nuaa.bank.service;

import com.nuaa.bank.bean.Customer;

/**
 * CustomerList为Customer对象的管理模块，内部使用数组管理一组Customer对象
 * @author LW
 *
 */
public class CustomerList {
	private Customer[] customers;
	private int total;
	
	//用于初始化最多可容纳客户的数量
	public CustomerList(int totalCustomer) {
		customers = new Customer[totalCustomer];
	}
	
	public boolean addCustomer(Customer customer) {
		if(total>=customers.length) {
			return false;
		}
		customers[total++]=customer;
		return true;
	}
	
	public boolean replaceCustomer(int index,Customer customer) {
		if(index<0 || index>=total) {
			return false;
		}
		customers[index]=customer;
		return true;
	}
	
	//删除指定位置的客户
	public boolean deleteCustomer(int index) {
		if(index<0 || index>=total) {
			return false;
		}
		for(int i=index;i<total-1;i++) {
			customers[i]=customers[i+1];
		}
		customers[--total]=null;
		return true;
	}
	
	public Customer[] getAllCustomers() {
		//创建实际客户数量的数组用于复制客户的地址
		Customer[] custs=new Customer[total];
		for(int i=0;i<total;i++) {
			custs[i]=customers[i];
		}
		return custs;
	}
	
	public Customer getCustomer(int index) {
		if(index<0 || index>=total) {
			return null;
		}
		return customers[index];
	}
	
	public int getTotal() {
		return total;
	}
}
