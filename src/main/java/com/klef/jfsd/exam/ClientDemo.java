package com.klef.jfsd.exam;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientDemo {
    public static void main(String[] args) {
        insertDepartment("CSE", "Guntur", "Dr. abc");
        deleteDepartment(2);
    }

    public static void insertDepartment(String name, String location, String hodName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            Department department = new Department();
            department.setName(name);
            department.setLocation(location);
            department.setHodName(hodName);
            session.save(department);
            transaction.commit();
            System.out.println("Department added successfully!");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public static void deleteDepartment(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM Department WHERE id = :id";
            int result = session.createQuery(hql).setParameter("id", id).executeUpdate();
            transaction.commit();
            if (result > 0) {
                System.out.println("Department deleted successfully!");
            } else {
                System.out.println("Department with ID " + id + " not found.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
