package com.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import com.entities.*;

/**
 * Session Bean implementation class SupplierService
 */
@Stateless
@LocalBean
public class SupplierService implements SupplierServiceRemote, SupplierServiceLocal {
	
	@PersistenceContext(unitName="suppliermicroservice-jpa",type=PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public SupplierService() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public List<Supplier> findSupplier(String name) {
    String consulta = "SELECT s FROM supplier s WHERE s.supplier_name=:userName";
    TypedQuery<Supplier> query = entityManager.createQuery(consulta, Supplier.class);
    query.setParameter("userName", name);
    //query.setParameter("password", password);
    query.setMaxResults(1);
    List<Supplier> resultList = query.getResultList();
    return resultList;
    }
    
    @Override
    public String addSupplier(String name, String addres,String phone, String email,String uri) {
    Supplier newSupplier = new Supplier();
    
    newSupplier.setSupplierName(name);
    newSupplier.setAddress(addres);
    newSupplier.setPhone(phone);
    newSupplier.setEmail(email);
    newSupplier.setUri(uri);
    Supplier supplier = entityManager.find(Supplier.class, newSupplier.getIdsupplier());
    if (supplier == null) {
    entityManager.persist(newSupplier);
    return "insertado";
    } else
    return "existe";
    }
    
    @Override
    public List<Supplier> getAllSuppliers() {
    // TODO Auto-generated method stub
    return entityManager.createQuery("SELECT p FROM supplier p", Supplier.class).getResultList();
    }

	@Override
	public String updateSupplier(int id, String supplier_name,String address, String phone, String email, String uri) {
		// TODO Auto-generated method stub
		String consulta = "UPDATE supplier s SET s.supplier_name=supplier_name,s.address=address,s.phone=phone,s.email=email, s.uri=uri"
				+" WHERE u.idsupplier=:id";
			    TypedQuery<Supplier> query = entityManager.createQuery(consulta, Supplier.class);
			    query.setParameter("id", id);
			    query.setParameter("supplier_name", supplier_name);
			    query.setParameter("address", address);
			    query.setParameter("phone", phone);
			    query.setParameter("email", email);
			    query.setParameter("uri", uri);
			    int rowsUpdated = query.executeUpdate();
		return null;
	}

	@Override
	public String deleteSupplier(String name, int supplierId) {
		String consulta = "DELETE FROM supplier s WHERE s.idsupplier=:id";
	    TypedQuery<Supplier> query = entityManager.createQuery(consulta, Supplier.class);
	    int deleted=query.setParameter("id", supplierId).executeUpdate();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addSupplierProduct(String name, String description, float price, String keywords, int supplierId) {
		Productfromsupplier newSupplierProduct = new Productfromsupplier();
	    
	    newSupplierProduct.setName(name);
	    newSupplierProduct.setDescription(description);
	    newSupplierProduct.setPrice(price);
	    newSupplierProduct.setKeywords(keywords);
	    newSupplierProduct.setSupplierId(supplierId);
	    Supplier supplierProduct = entityManager.find(Supplier.class, newSupplierProduct.getIdproductfromsupplier());
	    if (supplierProduct == null) {
	    entityManager.persist(newSupplierProduct);
	    return "insertado";
	    } else
	    return "existe";
	}

	@Override
	public List<Productfromsupplier> findSupplierProduct(String name, int supplierProductId) {
		// TODO Auto-generated method stub
		String consulta = "SELECT u FROM productfromsupplier u WHERE u.idproductfromsupplier=:id";
	    TypedQuery<Productfromsupplier> query = entityManager.createQuery(consulta, Productfromsupplier.class);
	    query.setParameter("id", supplierProductId);
	    //query.setParameter("password", password);
	    query.setMaxResults(1);
	    List<Productfromsupplier> resultList = query.getResultList();
	    return resultList;
		//return null;
	}

	@Override
	public String updateSupplierProduct(int id,String name, String description, float price, String keywords) {
		// TODO Auto-generated method stub
		String consulta = "UPDATE productfromsupplier s SET s.name=name,s.description=description,s.price=price,s.keywords=keywords"
				+" WHERE u.idproductfromsupplier=:id";
			    TypedQuery<Productfromsupplier> query = entityManager.createQuery(consulta, Productfromsupplier.class);
			    query.setParameter("id", id);
			    query.setParameter("name", name);
			    query.setParameter("description", description);
			    query.setParameter("keywords", keywords);
			    query.setParameter("price", price);
			    int rowsUpdated = query.executeUpdate();
		return null;
	}

	@Override
	public String deleteSupplierProduct(int supplierProductId) {
		
		String consulta = "DELETE FROM productfromsupplier s WHERE s.idproductfromsupplier=:id";
	    TypedQuery<Productfromsupplier> query = entityManager.createQuery(consulta, Productfromsupplier.class);
	    int deleted=query.setParameter("id", supplierProductId).executeUpdate();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Productfromsupplier> getAllSupplierProducts() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("SELECT p FROM productfromsupplier p", Productfromsupplier.class).getResultList();
		
	}

	@Override
	public List<Supplierorder> findSupplierOrder(int id) {
		// TODO Auto-generated method stub
		String consulta = "SELECT u FROM supplierorder u WHERE u.idsupplierorder=:id";
	    TypedQuery<Supplierorder> query = entityManager.createQuery(consulta, Supplierorder.class);
	    query.setParameter("id", id);
	    //query.setParameter("password", password);
	    query.setMaxResults(1);
	    List<Supplierorder> resultList = query.getResultList();
	    return resultList;
		
	}

	@Override
	public String addSupplierOrder(String productName, int supplierId, int productId, float productPrice, int amount) {
		Supplierorder newSupplierOrder = new Supplierorder();
	    newSupplierOrder.setProductName(productName);
	    newSupplierOrder.setAmount(amount);
	    //newSupplierOrder.setPayed(payed);
	    newSupplierOrder.setProductId(productId);
	    newSupplierOrder.setSupplierId(supplierId);
	    newSupplierOrder.setProductPrice(productPrice);
	    Supplierorder supplierOrder = entityManager.find(Supplierorder.class, newSupplierOrder.getIdsupplierorder());
	    if (supplierOrder == null) {
	    entityManager.persist(newSupplierOrder);
	    return "insertado";
	    } else
	    return "existe";
	}

	@Override
	public String updateSupplierOrder(int id,String productName, int supplierId, int productId, float productPrice, int amount,int payed) {
		//List<Supplierorder> toUpdate=findSupplierOrder(id);
		String consulta = "UPDATE supplierorder s SET s.product_name=productName,s.product_price=productPrice,s.amount=amount,s.payed=payed"
		+" WHERE u.idsupplierorder=:id";
	    TypedQuery<Supplierorder> query = entityManager.createQuery(consulta, Supplierorder.class);
	    query.setParameter("id", id);
	    query.setParameter("productName", productName);
	    query.setParameter("productPrice", productPrice);
	    query.setParameter("amount", amount);
	    query.setParameter("payed", payed);
	    int rowsUpdated = query.executeUpdate();
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteSupplierOrder(int supplierOrderId) {
		// TODO Auto-generated method stub
		String consulta = "DELETE FROM supplierorder s WHERE s.idsupplierorder=:id";
	    TypedQuery<Supplierorder> query = entityManager.createQuery(consulta, Supplierorder.class);
	    int deleted=query.setParameter("id", supplierOrderId).executeUpdate();
	    
		return null;
	}

	@Override
	public List<Supplierorder> getAllSupplierOrders() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("SELECT p FROM supplierorder p", Supplierorder.class).getResultList();
	}

	@Override
	public String paySuppliers() {
		//Supplierorder newSupplierOrder ;
		
		List <Supplierorder> supplierOrder=getAllSupplierOrders();
		
		for (Supplierorder supO : supplierOrder) {
			
			// TODO Conexión con pagos
			/*
			 * Aquí supongo que iria la conexión con pagos
			 * con algun metodo o algo o localozando el contenedor de 
			 * pagos y eso
			 */
			 }
			 System.out.println("Size: " + supplierOrder.size());
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String orderToSuppliers(String name,int amount) {
		
		
		float minimo = 0;
		int cont=0;
		Productfromsupplier newSupP= new Productfromsupplier();
		List <Productfromsupplier> supplierProduct=getAllSupplierProducts();
		List <Productfromsupplier> supplierProductCorrect = null;
		for(Productfromsupplier supP:supplierProduct) {
			if(supP.getName().equals(name)){
				supplierProductCorrect.add(supP);
			}
		}
		for(Productfromsupplier supP:supplierProductCorrect) {
			if(cont==0) {
				minimo=supP.getPrice();
				cont++;
			}
				
			if(supP.getPrice()<=minimo) {
				newSupP=supP;
				minimo=supP.getPrice();	
			}
		}
		addSupplierOrder(name, newSupP.getSupplierId(), newSupP.getIdproductfromsupplier(), minimo, amount);
		// TODO Conexión con email
		/*
		 * Aquí me imagino que iria la conexión con email 
		 */
		
		return null;
	}
    
    
    

}
