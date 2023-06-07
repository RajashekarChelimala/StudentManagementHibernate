package cgg.sma.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import cgg.sma.model.Student2;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Welcome to student Management System");
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int choice=0;
        String name = null;
		String city=null;
		String phone=null;
		String query=null;
		int idReq=0;
        SessionFactory factory=new Configuration().configure().buildSessionFactory();  
        Session session=factory.openSession();
        
        while(true) {
        	
        	System.out.println("Press1 to Add Student");
			System.out.println("Press2 to Update Student");
			System.out.println("Press3 to Delete Student");
			System.out.println("Press4 to Display Student details");
			System.out.println("Press5 to Exit");
			System.out.println("==========================================================================");
			System.out.println("Enter your choice");
			try {
				choice=Integer.parseInt(bf.readLine());
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(choice==1) {
				
				//Adding a Student
				System.out.println("please Enter the Student Details\n1.Name\n3City\n4.Phone");
				System.out.println("================================================================");
				Student2 s1 = new Student2();
				try {
					name=bf.readLine();
					city =bf.readLine();
					phone=bf.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				s1.setName(name);
				s1.setCity(city);
				s1.setPhone(phone);
				Transaction tx = session.beginTransaction();
				session.save(s1);
				tx.commit();
				System.out.println("Student Id for your Reference : "+s1.getStudentId());
				System.out.println("Student details added successfully....");
				System.out.println("------------------------------------------------------------------------");
			}
			else if(choice==2) {
				//Update the Student
				System.out.println("Enter the id to be Updated: ");
				try {
					idReq=Integer.parseInt(bf.readLine());
					System.out.println("Please Enter the Student Details\n1.Name\n3City\n4.Phone");
					name=bf.readLine();
					city =bf.readLine();
					phone=bf.readLine();
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Student2 st=session.load(Student2.class, idReq);
				
				Transaction tx=session.beginTransaction();
				st.setName(name);
				st.setCity(city);
				st.setPhone(phone);
				tx.commit();
				System.out.println("Details updates successfully...\n----------------------------------------------------");
			}
			else if(choice==3) {
				//Delete the student
				System.out.println("Enter the id to be deleted");
				try {
					idReq=Integer.parseInt(bf.readLine());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Student2 st=session.load(Student2.class, idReq);
				Transaction tx=session.beginTransaction();
				session.delete(st);
				tx.commit();
				System.out.println("Details deleted successfully....\n----------------------------------------------------------");
			}
			else if(choice==4) {
				//Display The Details
				System.out.println("Enter the id to display the details....");
				try {
					idReq=Integer.parseInt(bf.readLine());
				} catch (NumberFormatException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Student2 st=session.load(Student2.class, idReq);
				System.out.println("Name:"+st.getName()+"\nId:"+st.getStudentId()+"\nCity:"+st.getCity()+"\nPhone Number:"+st.getPhone());	
				System.out.println("-------------------------------------------------------------------------------------------");
			}
			else if(choice==5) {
				//Exit
				break;
			}
			
        }
        session.close();
        factory.close();
    }
}
