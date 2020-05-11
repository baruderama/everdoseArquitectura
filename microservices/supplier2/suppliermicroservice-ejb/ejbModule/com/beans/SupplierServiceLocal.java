package com.beans;

import java.util.List;

import javax.ejb.Local;

import com.entities.Productfromsupplier;
import com.entities.Supplier;
import com.entities.Supplierorder;

@Local
public interface SupplierServiceLocal {
	
	//CRUD tabla Supplier
	public Supplier findSupplier(int id);
	 public String addSupplier(String name, String addres,String phone, String email,String uri);
	 public String updateSupplier(int id, String supplier_name,String address, String phone, String email, String uri);
	 public String deleteSupplier(String name, int supplierId);
	 public List<Supplier> getAllSuppliers();
	 
	 //CRUD tabla Productfromsupplier
	 public String addSupplierProduct(String name, String description,float price, String keywords,int supplierId);
	 public Productfromsupplier findSupplierProduct(int supplierProductId);
	 public String updateSupplierProduct( int id,String name, String description, float price, String keywords,int supplierId);
	 public String deleteSupplierProduct(int supplierProductId);
	 public List<Productfromsupplier> getAllSupplierProducts();
	 
	 //CRUD tabla Supplierorder
	 public Supplierorder findSupplierOrder(int id);
	 public String addSupplierOrder(String productName, int supplierId,int productId, float productPrice,int amount);
	 public String updateSupplierOrder( int id,String productName, int supplierId, int productId, float productPrice, int amount,int payed);
	 public String deleteSupplierOrder( int supplierOrderId);
	 public List<Supplierorder> getAllSupplierOrders();
	 
	 //pagar al final del día todos los pedidos
	 public String paySuppliers();
	 
	 //se crea una orden y se envía por correo al correo del proveedor
	 public String orderToSuppliers(String name, int amount);
	 

}
