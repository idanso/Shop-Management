package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ProductsFile implements Iterable<Map.Entry<String, Product>> {
	
	private RandomAccessFile raf;
	private int numOfProducts;
	private long pos;

	public ProductsFile(File f, String mode) throws FileNotFoundException {
		try {
			raf = new RandomAccessFile(f, "rw");
			if(raf.length() > 0)
					numOfProducts = raf.readInt();
			else
				numOfProducts = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public int getNumOfProducts() {
		return numOfProducts;
	}

	public void saveAllProducts(Set<Map.Entry<String, Product>> setProducts) throws IOException {
		raf.seek(0);
		raf.writeInt(numOfProducts);
		for(Map.Entry<String, Product> p : setProducts ) {
			saveProduct(p.getValue(), p.getKey());
		}
	}
	
	public void saveProduct(Product product, String productNumber) throws IOException {
		raf.writeUTF(product.getName());
		raf.writeInt(product.getValuePrice());
		raf.writeInt(product.getCostumerPrice());
		raf.writeUTF(product.getCustomer().getName());
		raf.writeUTF(product.getCustomer().getNumber());
		raf.writeBoolean(product.getCustomer().getBNotification());
		raf.writeUTF(productNumber);
	}
	
	public void setNumOfProducts(int numOfProducts) throws IOException {
		this.numOfProducts = numOfProducts;
		raf.seek(0);
		raf.writeInt(numOfProducts);
	}
	
	public void clear() throws IOException {
		raf.setLength(0);
	}
	
	public void close() throws IOException {
		raf.close();
	}

	@Override
	public Iterator<Map.Entry<String, Product>> iterator() {
		return new fileIterator();
	}
	
	public class fileIterator implements Iterator<Map.Entry<String, Product>>{
		
		
		
		public fileIterator() {
			try {
				raf.seek(0);
				raf.readInt();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public boolean hasNext() {
			try {
				if(raf.getFilePointer() < raf.length())
					return true;
			} catch (IOException e) {

				e.printStackTrace();
			}
			return false;
		}

		@Override
		public Map.Entry<String, Product> next() {
			try {
				pos = raf.getFilePointer(); //save the pointer file of where the object begin
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				String ProductName = raf.readUTF();
				int valuePrice = raf.readInt(); 
				int customerPrice = raf.readInt();
				String customerName = raf.readUTF();
				String customerNumber = raf.readUTF();
				boolean bNotification = raf.readBoolean();
				String productNum =  raf.readUTF(); //catalog number
				Customer customer = new Customer(customerName, customerNumber, bNotification);
				Product product = new Product(ProductName, valuePrice, customerPrice, customer);
				return new java.util.AbstractMap.SimpleEntry<String, Product>(productNum,product);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		@Override
		public void remove() {
				long readPointer = 0, writePointer = pos, lengthBeforeTheDeletedObject = 0;
				
				if(hasNext()) //check if not last product
					next();
				else
					try {
						
						pos = raf.getFilePointer();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				
				readPointer = pos;
				
				try {
					lengthBeforeTheDeletedObject = raf.length() - readPointer;
					if(lengthBeforeTheDeletedObject != 0) { //check if not last product
						byte[] data = new byte[ (int) lengthBeforeTheDeletedObject];
						raf.seek(readPointer);
						raf.read(data);
						raf.seek(writePointer);
						raf.write(data);
					}
					raf.setLength(lengthBeforeTheDeletedObject + writePointer);
					raf.seek(writePointer);
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		
	}

	public boolean isEmpty() throws IOException {
		return raf.length() > 0 ? false : true;
	}
	
}
