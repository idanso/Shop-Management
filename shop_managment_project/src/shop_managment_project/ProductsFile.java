package shop_managment_project;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Iterator;
import java.util.Map;

public class ProductsFile<T> implements Iterable<Map.Entry<String, Product>> {
	
	private RandomAccessFile iO;
	private int numOfObjects;
	private int pos; //like index of the object in the file

	public ProductsFile(File f) {
		
		try {
			iO = new RandomAccessFile(f, "rw");
			numOfObjects = iO.readInt();
			pos = 0;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Iterator<Map.Entry<String, Product>> iterator() {
		return new fileIterator();
	}
	
	public class fileIterator implements Iterator<Map.Entry<String, Product>>{

		@Override
		public boolean hasNext() {
			if(pos < numOfObjects)
				return true;
			else
				return false;
		}

		@Override
		public Map.Entry<String, Product> next() {
			pos++;
			try {
				String ProductName = iO.readUTF();
				int valuePrice = iO.readInt(); 
				int customerPrice = iO.readInt();
				String customerName = iO.readUTF();
				String customerNumber = iO.readUTF();
				boolean bNotification = iO.readBoolean();
				String productNum =  iO.readUTF(); //catalog number
				Customer customer = new Customer(customerName, customerNumber, bNotification);
				Product product = new Product(ProductName, valuePrice, customerPrice, customer);
				return new java.util.AbstractMap.SimpleEntry<String, Product>(productNum,product);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
		
		public long getPos() throws IOException {
			return iO.getFilePointer();
		}
		
		public void seek(long pos) throws IOException {
			iO.seek(pos);
		}
		
	}
	
	
	 
	
	
}
