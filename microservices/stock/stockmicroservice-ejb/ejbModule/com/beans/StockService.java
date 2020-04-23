package com.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.entities.Drugstore;
import com.entities.Product;
import com.entities.ProductFromDrugstore;
import com.utils.ProductAdapter;

/**
 * Session Bean implementation class StockService
 */
@Stateless
@LocalBean
public class StockService implements StockServiceRemote, StockServiceLocal {

    /**
     * Default constructor.
     */
    public StockService() {

    }

	@Override
	public List<Product> getProducts() {
		return Product.getProducts();
	}

	@Override
	public boolean addProduct( String name, String description, String location, String image, float price, int threshold, int amount ,String keyword) {

		boolean succesfulltransaction = false;

		try {

			Product product = new Product();
			product.setName(name);
			product.setDescription(description);
			product.setAmount(amount);
			product.setPrice(price);
			product.setImage(image);
			product.setThreshold(threshold);
			product.setLocation(location);
			product.setKeywords(keyword);
			product.save();

			succesfulltransaction = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return succesfulltransaction;
	}

	@Override
	public boolean removeProduct( int id ) {
		boolean succesfulltransaction = false;
		try {
			Product product = Product.getProduct(id);
			product.removeProduct();
			succesfulltransaction=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return succesfulltransaction;
	}

	@Override
	public List<Product> checkRunningOut() {
		// TODO Auto-generated method stub
		ArrayList<Product> productsRunningOut = new ArrayList<Product>();
		try {
			List<Product> products = Product.getProducts();
			for (Product product : products) {
				if (product.getAmount() < product.getThreshold()) {
					productsRunningOut.add(product);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return productsRunningOut;
	}

	@Override
	public boolean addDrugstore(String address, String email, String name, String phone, String uri) {
		Drugstore drugstore=new Drugstore();
		drugstore.setAddress(address);
		drugstore.setEmail(email);
		drugstore.setName(name);
		drugstore.setPhone(phone);
		drugstore.setUri(uri);
		return drugstore.save();
	}

	@Override
	public List<Drugstore> getDrugstores() {
		return Drugstore.getDrugstores();
	}

	@Override
	public boolean modifyDrugstore(int id, String address, String email, String name, String phone, String uri) {
		return Drugstore.UpdateDrugstoreById(id, address, email, name, phone, uri);
		
	}

	@Override
	public boolean deleteDrugstore(int id) {
		return Drugstore.deleteById(id);
	}

	@Override
	public List<ProductAdapter> getCatalog() {
		List<ProductAdapter> products=new ArrayList<ProductAdapter>();
		List<Product> prds=this.getProducts();
		List<ProductFromDrugstore> prdsfd=this.getProductsFromDrugstore();
		for(Product p:prds) {
			products.add(new ProductAdapter(p));
		}
		for(ProductFromDrugstore p:prdsfd) {
			products.add(new ProductAdapter(p));
		}
		return products;
	}

	@Override
	public List<ProductFromDrugstore> getProductsFromDrugstore() {
		return ProductFromDrugstore.getProducts();
	}

	@Override
	public boolean addProductFromDrugstore(String name, String description, String keywords, float price,int drugstore_id) {
		boolean succesfulltransaction = false;

		try {

			ProductFromDrugstore product = new ProductFromDrugstore();
			Drugstore drugstore=Drugstore.getDrugstore(drugstore_id);
			product.setDrugstore(drugstore);
			product.setName(name);
			product.setDescription(description);
			product.setPrice(price);
			product.setKeywords(keywords);
			

			succesfulltransaction = product.save();;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return succesfulltransaction;
	}

	@Override
	public boolean modifyProduct(int id, String name, String description, String location, String image, Float price,
			Integer threshold, Integer amount, String keyword) {
		return Product.UpdateProductById(id, name, description, location, image, price,threshold, amount,keyword);
	}

}
