package com.nuaa.bank.view;

import com.nuaa.bank.bean.Customer;
import com.nuaa.bank.service.CustomerList;
import com.nuaa.bank.util.CMUtility;

public class CustomerView {
	//先创建能够容纳10个客户对象的客户列表
	private CustomerList customerList=new CustomerList(10);
	
	public CustomerView() {
		Customer cust=new Customer("liwei",'男',20,"12354323453","dsa@qq.com");
		customerList.addCustomer(cust);
	}
	
	//主菜单
	public void enterMainMenu() {
		boolean isFlag=true;
		while(isFlag) {
			System.out.println("--------------客户信息管理软件---------------------------");
			System.out.println("\t\t  1 添 加 客 户  ");
			System.out.println("\t\t  2 修 改 客 户   ");
			System.out.println("\t\t  3 删 除 客 户   ");
			System.out.println("\t\t  4 客 户 列 表   ");
			System.out.println("\t\t  5 退        出    ");
			System.out.print("\t\t     请选择（1-5）：");
			
			char menu=CMUtility.readMenuSelection();
			switch(menu) {
			case '1':
				addNewCustomer();
				break;
			case '2':
				modifyCustomer();
				break;
			case '3':
				deleteCustomer();
				break;
			case '4':
				listAllCustomers();
				break;
			case '5':
				System.out.print("确认是否退出(Y/N)：");
				char ye=CMUtility.readConfirmSelection();
				if(ye=='Y') {
					isFlag=false;
				}
			}
		}
	}
	
	//添加新客户操作
	private void addNewCustomer() {
		System.out.println("-------------添加客户信息---------------");
		System.out.print("姓名：");
		String name = CMUtility.readString(10);
		System.out.print("性别：");
		char gender = CMUtility.readChar();
		System.out.print("年龄：");
		int age = CMUtility.readInt();
		System.out.print("电话：");
		String phone = CMUtility.readString(11);
		System.out.print("邮箱：");
		String email = CMUtility.readString(30);
		
		//将客户信息封装成客户对象
		Customer customer=new Customer(name, gender, age, phone, email);
		boolean isSuccess=customerList.addCustomer(customer);
		if(isSuccess) {
			System.out.println("------------添加成功！----------");
		}else {
			System.out.println("----------记录已满，无法添加！--------------");
		}
	}
	
	
	//修改客户信息操作
	private void modifyCustomer() {
		System.out.println("-----------修改客户信息----------------");
		Customer cust=null;
		int number=0;
		while(true) {
			System.out.print("请选择待修改的客户编号（从1开始，-1表示退出）：");
			number=CMUtility.readInt();
			if(number==-1) {
				return;
			}
			cust=customerList.getCustomer(number-1);
			if(cust==null) {
				System.out.println("编号错误，无法找到指定用户！");
			}else {
				break;//表示找到了指定用户
			}
		}
		//修改客户信息
		System.out.print("姓名（"+cust.getName()+"):");
		String name = CMUtility.readString(10, cust.getName());
		
		System.out.print("性别（"+cust.getGender()+"):");
		char gender = CMUtility.readChar(cust.getGender());
		
		System.out.print("年龄（"+cust.getAge()+"):");
		int age = CMUtility.readInt(cust.getAge());
		
		System.out.print("电话（"+cust.getPhone()+"):");
		String phone = CMUtility.readString(11, cust.getPhone());
		
		System.out.print("邮箱（"+cust.getEmail()+"):");
		String email = CMUtility.readString(20, cust.getEmail());
		//将修改的客户信息封装成客户对象
		cust=new Customer(name, gender, age, phone, email);
		//将修改后的客户对象更新到对象数组中
		boolean isReplace=customerList.replaceCustomer(number-1, cust);
		if(isReplace) {
			System.out.println("-----------修改成功！------------");
		}
	}
	
	
	//删除客户信息操作
	private void deleteCustomer() {
		System.out.println("-----------删除客户信息----------------");
		Customer cust=null;
		int number=0;
		while(true) {
			System.out.print("请选择待删除的客户编号（-1表示退出）：");
			number=CMUtility.readInt();
			if(number==-1) {
				return;
			}
			cust=customerList.getCustomer(number-1);
			if(cust==null) {
				System.out.println("编号错误，无法找到指定用户！");
			}else {
				break;//表示找到了指定用户
			}
		}
		//删除指定客户
		System.out.println("确定是否删除(Y/N)：");
		char delect = CMUtility.readConfirmSelection();
		if(delect=='N') {
			return;
		}
		boolean isDelete = customerList.deleteCustomer(number-1);
		if(isDelete) {
			System.out.println("-----------删除客户信息成功！----------------");
		}else {
			System.out.println("-----------删除失败！----------------");
		}
	}
	
	//显示所有客户信息
	private void listAllCustomers() {
		System.out.println("-----------显示所有客户信息---------------------------");
		Customer[] custs=customerList.getAllCustomers();
		if(custs.length==0) {
			System.out.println("没有客户记录！");
		}else {
			System.out.println("编号\t姓名\t性别\t年龄\t电话\t\t邮箱");
			for(int i=0;i<custs.length;i++) {
				System.out.println((i+1)+"\t"+custs[i].getName()+"\t"+custs[i].getGender()+"\t"+
			custs[i].getAge()+"\t"+custs[i].getPhone()+"\t"+custs[i].getEmail());
			}
		}
		System.out.println("-----------客户信息显示完毕！---------------------------");
	}

	
	public static void main(String[] args) {
		CustomerView custView=new CustomerView();
		custView.enterMainMenu();
	}

}
