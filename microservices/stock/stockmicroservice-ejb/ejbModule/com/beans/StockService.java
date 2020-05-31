package com.beans;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jboss.resteasy.util.FindAnnotation;

import com.classes.DeliveryOrder;
import com.classes.SupplierOrder;
import com.classes.SupplierProduct;
import com.entities.Drugstore;
import com.entities.Product;
import com.entities.ProductFromDrugstore;
import com.google.gson.Gson;
import com.utils.DeliveryProduct;
import com.utils.ProductAdapter;

/**
 * Session Bean implementation class StockService
 */
@Stateless
@LocalBean
public class StockService implements StockServiceRemote, StockServiceLocal {

	public boolean post(String url, StringEntity entity) throws Exception {
		HttpPost post = new HttpPost(url);
        post.setEntity(entity);
        
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
        	int code = response.getStatusLine().getStatusCode();
        	if (code >= 200 && code < 300) {
        		return true;
        	}
        }
        return false;
	}
	
    /**
     * Default constructor.
     */
	private static String DELIVERY_URL="http://127.0.0.1:8080/deliverymicroservice-web-0.0.1-SNAPSHOT/DeliverOrder";
	private static String SUPPLYPRODUCT_URL="http://127.0.0.1:8080/suppliermicroservice-web-0.0.1-SNAPSHOT/OrderFromSupplier";
	
    public StockService() {

    }

	@Override
	public List<Product> getProducts() {
		System.out.println("Servicio obtener la lista de productos de stock");
		return Product.getProducts();
	}

	@Override
	public boolean addProduct( String name, String description, String location, String image, float price, int threshold, int amount ,String keyword) {

		System.out.println("Servicio de añadir un producto al stock");
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
		System.out.println("Sacando remover un producto de stock");
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
		
		System.out.println("Servicio de checkeo de que se acabo algun producto");
		ArrayList<Product> productsRunningOut = new ArrayList<Product>();
		SupplierOrder supplierOrder = new SupplierOrder();
		try {
			List<Product> products = Product.getProducts();
			for (Product product : products) {
				if (product.getAmount() < product.getThreshold()) {
					productsRunningOut.add(product);
					SupplierProduct supplierProduct = new SupplierProduct();
					supplierProduct.setName(product.getName());
					supplierProduct.setKeywords(product.getKeywords());
					supplierProduct.setAmount(product.getThreshold());
					supplierOrder.addProduct(supplierProduct);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Gson gson = new Gson();
		String supplierOrderJson = gson.toJson(supplierOrder);
		
		try {
			StringEntity entity = new StringEntity(supplierOrderJson);
			System.out.println("DeliveryOrder: ");
			System.out.println(supplierOrderJson);
			System.out.println(133);
			boolean state = post(SUPPLYPRODUCT_URL, entity);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return productsRunningOut;
	}

	@Override
	public boolean addDrugstore(String address, String email, String name, String phone, String uri) {
		
		System.out.println("Servicio de añadir una droguería ");
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
		System.out.println("Servicio obtener lista de droguerias");
		
		return Drugstore.getDrugstores();
	}

	@Override
	public boolean modifyDrugstore(int id, String address, String email, String name, String phone, String uri) {
		System.out.println("Servicio modificar info de alguna drogueria");
		return Drugstore.UpdateDrugstoreById(id, address, email, name, phone, uri);
		
	}

	@Override
	public boolean deleteDrugstore(int id) {
		System.out.println("Sacando remover una droguria");
		return Drugstore.deleteById(id);
	}

	@Override
	public List<ProductAdapter> getCatalog(String keywords, int page) {
		System.out.println("Servicio obtener un catalogo de todos los productos(incluyendo los de las droguerias)");
		List<ProductAdapter> products=new ArrayList<ProductAdapter>();
		List<Product> prds=this.getProducts();
		List<ProductFromDrugstore> prdsfd=this.getProductsFromDrugstore();
		for(Product p:prds) {
			if(keywords==null||(p.getKeywords()!=null&&p.getKeywords().contains(keywords))) {
				products.add(new ProductAdapter(p));
			}
		}
		for(ProductFromDrugstore p:prdsfd) {
			if(keywords==null||(p.getKeywords()!=null&&p.getKeywords().contains(keywords))) {
				products.add(new ProductAdapter(p));
			}
		}
		final int PAGE_SIZE = 15;
		page-= 1;
		System.out.println("Page:" + page);
		if(page == 0 && products.size() < PAGE_SIZE) {
			return products;
		}
		if(products.size()>page*PAGE_SIZE) {
			if(products.size() > page*PAGE_SIZE+PAGE_SIZE) {
				return products.subList(page*PAGE_SIZE, page*15+15);
			}
			else {
				return products.subList(page*PAGE_SIZE, products.size());
			}
		}
		return new ArrayList<ProductAdapter>();
	}

	@Override
	public List<ProductFromDrugstore> getProductsFromDrugstore() {
		System.out.println("Servicio obtener productos de la drogueria");
		return ProductFromDrugstore.getProducts();
	}

	@Override
	public boolean addProductFromDrugstore(String name, String description, String keywords, float price,int drugstore_id) {
		System.out.println("Servicio que añade los productos que son de las droguerias");
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
		System.out.println("Servicio modificar algún aspecto de algun producto");
		return Product.UpdateProductById(id, name, description, location, image, price,threshold, amount,keyword);
	}

	@Override
	public boolean removeProductFromDrugstore(int id) {
		System.out.println("Servicio de remover lagún producto que venga de alguna drogueria");
		boolean succesfulltransaction = false;
		try {
			ProductFromDrugstore product = ProductFromDrugstore.getProduct(id);
			succesfulltransaction=product.removeProduct();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return succesfulltransaction;
	}

	@Override
	public boolean modifyProductFromDrugstore(int id, String name, String description, Float price, String keywords) {
		System.out.println("Servicio de remover");
		return ProductFromDrugstore.UpdateProductById(id, name, description,price,keywords);
	}

	@Override
	public boolean consumeProducts(List<ProductAdapter> products,String destiny_address) {
		
		List<DeliveryProduct> deliveryProducts=new ArrayList<DeliveryProduct>();
		ProductAdapter pa;
		for(ProductAdapter p:products) {
			if(p.getType().equals("drugstore")) {
				ProductFromDrugstore product = ProductFromDrugstore.getProduct(p.getId());
				if(product==null) {
					System.out.println("no existe PD:"+p.getId());
					return false;
				}
				pa=new ProductAdapter(product);
				pa.setAmount(p.getAmount());
				deliveryProducts.add(new DeliveryProduct(pa));
			}else {
				Product product=Product.getProduct(p.getId());
				if(product==null) {
					System.out.println("no existe P:"+p.getId());
					return false;
				}
				int amount=product.getAmount()-p.getAmount();
				if(amount<0) {
					System.out.println("no suficiente P:"+p.getId());
					return false;
				}
				pa=new ProductAdapter(product);
				pa.setAmount(p.getAmount());
				deliveryProducts.add(new DeliveryProduct(pa));
			}
			
		}
		
		
		DeliveryOrder deliveryOrder = new DeliveryOrder();
		deliveryOrder.setProducts(deliveryProducts);
		deliveryOrder.setDestin_address(destiny_address);
		
		Gson gson = new Gson();
		String deliveryOrderJson = gson.toJson(deliveryOrder);
		try {
			StringEntity entity = new StringEntity(deliveryOrderJson);
			post(DELIVERY_URL, entity);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		for(ProductAdapter p:products) {
			if(p.getType().equals(ProductAdapter.INVENTARY)) {
				Product product=Product.getProduct(p.getId());
				int amount=product.getAmount()-p.getAmount();
				product.setAmount((amount>=0)?amount:0);
				product.save();
			}
		}
		return true;
	}

	public Integer getPrice(List<ProductAdapter> products) {
		System.out.println("Se obtiene el precio de algún producto");
		double total=0;
		for(ProductAdapter p:products) {
			if(p.getType().equals("drugstore")) {
				ProductFromDrugstore product = ProductFromDrugstore.getProduct(p.getId());
				if(product!=null) {
					total+=(product.getPrice()*p.getAmount());
				}
			}else {
				Product product=Product.getProduct(p.getId());
				if(product!=null) {
					total+=(product.getPrice()*p.getAmount());
				}
			}
			
		}
		return (int)total;
	}

}
