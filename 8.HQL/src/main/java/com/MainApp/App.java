package com.MainApp;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import com.ConnectionFactory.ConnectionFactory;
import com.Model.Employee;

public class App 
{
    public static void main( String[] args )
    {
    	 Session s = ConnectionFactory.getSession();
    	 Transaction t = s.beginTransaction();
    	 Scanner sc = new Scanner(System.in);
         
         while(true) {
      	   System.out.println("Enter 1 to insert\nEnter 2 to Read\nEnter 3 to delete\nEnter 4 to Update\nEnter 5 to exit");
      	   int choice = sc.nextInt();
      	   
      	   if(choice==1) {
      		   insert(s,sc,t);
      	   }else if(choice==2) {
      		  read(s);
      	   }else if(choice==3) {
      		   delete(s,sc,t);
      	   }else if(choice==4) {
      		   update(s,sc,t);
      	   }else if(choice==5) {
      		   break;
      	   }else {
      		   System.out.println("Invalid Choice");
      	   }
         }
   
    }

	private static void update(Session s, Scanner sc, Transaction t) {
		String hql = "update Employee set name=:n,salary=:s where id=:i";
		Query<Employee> q = s.createQuery(hql);
		System.out.println("Enter ID :");
		q.setParameter("i", sc.nextInt());
		System.out.println("Enter new Name:");
		q.setParameter("n", sc.next());
		System.out.println("Enter new Salary:");
		q.setParameter("s", sc.nextDouble());
		
		q.executeUpdate();
		t.commit();
		t.begin();
		System.out.println("Updated");
		
	}

	
	private static void delete(Session s, Scanner sc, Transaction t) {
		
		String hql = "delete from Employee where id=:i";
		Query<Employee> q = s.createQuery(hql);
		System.out.println("Enter id :");
		q.setParameter("i",sc.nextInt());
		q.executeUpdate();
		t.commit();
		t.begin();
		System.out.println("Deleted");
		
	}

	private static void read(Session s) {
	
		String hql = "from Employee";
		Query q = s.createQuery(hql);
		List<Employee> l = q.list();
		for(Employee e : l) {
			System.out.println(e);
		}
	}

	private static void insert(Session s, Scanner sc, Transaction t) {
		
		String hql = "insert into Employee(id,name,salary) select id,name,salary from Demo";
		Query<Employee> q = s.createQuery(hql);
		q.executeUpdate();
		t.commit();
		t.begin();
		System.out.println("done");
		
	}
}
