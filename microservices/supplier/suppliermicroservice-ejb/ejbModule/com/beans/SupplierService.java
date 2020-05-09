package com.beans;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.classes.SupplierProduct;
import com.entities.Drugstore;
import com.entities.ProductFromSupplier;
import com.entities.Supplier;
import com.entities.SupplierOrder;

/**
 * Session Bean implementation class SupplierService
 */
@Stateless
@LocalBean
public class SupplierService implements SupplierServiceRemote, SupplierServiceLocal {

    /**
     * Default constructor. 
     */
    public SupplierService() {
        
    }

	@Override
	public boolean addSupplier(String name, String address, String phone, String email, String uri) {
		boolean succesfulltransaction = false;
		/**
		 * Adds the supplier to the database. 
		 *
		 * @param  name name of the supplier.
		 * @param address address of the supplier.
		 * ...
		 * 
		 * @return      A state; true if the transaction was successful, false otherwise.
		 */
		try {
			
			Supplier supplier = new Supplier();
			supplier.setName(name);
			supplier.setAddress(address);
			supplier.setPhone(phone);
			supplier.setEmail(email);
			supplier.setUri(uri);
			
			supplier.save();
			succesfulltransaction = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return succesfulltransaction;
	}

	@Override
	public boolean addDrugstore(String name, String address, String phone, String email, String uri) {
		boolean succesfulltransaction = false;
		
		try {
			Drugstore drugstore = new Drugstore();
			drugstore.setName(name);
			drugstore.setAddress(address);
			drugstore.setPhone(phone);
			drugstore.setEmail(email);
			drugstore.setUri(uri);
			drugstore.save();
			succesfulltransaction = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return succesfulltransaction;
	}

	@Override
	public boolean addSupplierfinancialInformation() {
		// TODO Agregar información financiera, crear entity.
		return false;
	}

	@Override
	public int orderFromSupplier(List<SupplierProduct> productsToOrder) {
		/**
		 * Orders from supplier, it searches for the product in the database given the keywords, 
		 * orders the cheapest product found. An order is generated.
		 *
		 * @param  name name of the product.
		 * @param  keywords keywords separated by commas describing the product.
		 * @return      The cheapest product that matched the keywords.
		 * @see SupplierOrder
		 */
		List<ProductFromSupplier> products = ProductFromSupplier.getProductsFromSuppliers();
		for (SupplierProduct productToOrder: productsToOrder) {
			String keywords = productToOrder.getKeywords();
			String name = productToOrder.getName();
			int amount = productToOrder.getAmount();
			String[] eachkeyword = keywords.split(",");
			float min = 999999;
			ProductFromSupplier chosenProduct= null;
			Supplier supplier = null;
			for (ProductFromSupplier product : products) {
				if ( product.getName().equals(name) && product.getPrice() < min) {
					chosenProduct = product;
					supplier = Supplier.getSupplier( product.getSupplier_id() );
					min = product.getPrice();
				}
			}
			if (chosenProduct != null ) {
				SupplierOrder order = new SupplierOrder();
				order.setProduct_name(chosenProduct.getName());
				order.setProduct_price(chosenProduct.getPrice());
				order.setSupplier_id( chosenProduct.getSupplier_id() );
				order.setAmount(amount);
				order.save();
			}
			
		}
//		TODO: Llama al servicio de email para pedir el producto.
		return 0;
	}

	@Override
	public HashMap< Integer, Float > paySuppliers() {
		/**
		 * Pays the supplier given the existing orders in the databases.
		 *
		 * @return      A HashMap with suppliers id as keys and payed amount as value.
		 */
		List<SupplierOrder> orders = SupplierOrder.getSupplierOrders();
		HashMap<Integer, ArrayList<SupplierOrder> > orders_grouped = new HashMap<Integer, ArrayList<SupplierOrder>>();
		HashMap<Integer, Float> payment = new HashMap<Integer, Float>();
		for (SupplierOrder supplierOrder : orders) {
			ArrayList<SupplierOrder> existingOrders = orders_grouped.get( supplierOrder.getSupplier_id() );
			if (existingOrders != null) {
				if ( !supplierOrder.isPayed() ) {
					orders_grouped.get( supplierOrder.getSupplier_id() ).add(supplierOrder);
					float total = payment.get(supplierOrder.getSupplier_id()) + supplierOrder.getAmount()*supplierOrder.getProduct_price() ;
					payment.put( supplierOrder.getSupplier_id(), total);
				}
			}
			else {
				ArrayList<SupplierOrder> temp = new ArrayList<SupplierOrder>();
				temp.add(supplierOrder);
				orders_grouped.put( supplierOrder.getSupplier_id()  , temp);
				payment.put( supplierOrder.getSupplier_id(), supplierOrder.getProduct_price() );
			}
		}
//		TODO: Va al servicio de pagos a solicitar el pago, se marca como pagado si fue exitoso.
		return payment;
	}

	@Override
	public boolean addProductToSupplier(int supplier_id, String name, String keywords, String description, float price) {
		Supplier supplier = Supplier.getSupplier(supplier_id);
		if ( supplier != null ) {
			ProductFromSupplier product = new ProductFromSupplier();
			product.setName(name);
			product.setSupplier_id(supplier_id);
			product.setDescription(description);
			product.setKeywords(keywords);
			product.setPrice(price);
			product.save();
			System.out.println("The product from the supplier was succesfully added.");
			return true;
		}
		else {
			System.out.println("The supplier wasn't found");
		}
		return false;
	}

}
