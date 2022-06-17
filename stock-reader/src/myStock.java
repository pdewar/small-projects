import java.io.*;
import java.math.*;
import java.util.*;
import java.util.Map.Entry;

import yahoofinance.*;

public class myStock {

	HashMap<String, stockInfo> mapdata;
	TreeSet<Map.Entry<String, stockInfo>> treedata;
	ArrayList<Entry<String, stockInfo>> topk;
	
	// TODO: declare the data structures that store the stock info as the database here
	// HINT: you may consider to use HashMap which provides fast retrieval
	//       it can be declared as: HashMap<String, stockInfo>
	// HINT: you may also consider to use TreeSet which allows key-value pairs to be sorted by value 
	//       it can be declared as: TreeSet<Map.Entry<String, stockInfo>> so that we can easily return top k stocks with type of Map.Entry<String, stockInfo>
	// HINT: you're allowed to use more than one data structures, each of which holds the same data and serve for different purposes.

	// This is the nested class provided for you to store the infomation associated with a stock symbol
	
	private static class stockInfo{
		private String name;
		private BigDecimal price;
		public stockInfo(String nameIn, BigDecimal priceIn) {
			name = nameIn;
			price = priceIn;
		}
		public String toString() {
			StringBuilder stockInfoString = new StringBuilder("");
			stockInfoString.append(name + " " + price.toString());
			return stockInfoString.toString();
		}
		public BigDecimal get_price()
		{
			BigDecimal hold = price;
			return hold;
		}
	}
	
	public myStock (){
		// TODO: implement the constructor to create the database
		// HINT: you need to create the data structures used for the database here,
		//		 and override the data structure's compare method if needed
		//       such that the stocks would be sorted by price in the data structure
		mapdata = new HashMap<String, stockInfo>();
		treedata = new TreeSet<Map.Entry<String, stockInfo>>(new MySort());
		topk = new ArrayList<Map.Entry<String, stockInfo>>();
	}
	
		class MySort implements Comparator<Map.Entry<String, stockInfo>>{
		public int compare(Entry<String, stockInfo> e1, Entry<String, stockInfo> e2) {
			stockInfo v1 = e1.getValue();
			stockInfo v2 = e2.getValue();
			BigDecimal d1 = v1.get_price();
			BigDecimal d2 = v2.get_price();
			
			return d2.compareTo(d1);
			}
		}
    
	public void insertOrUpdate(String symbol, stockInfo stock) {
		// TODO: implement this method which is used to initialize and update the database
		// HINT: make sure the time complexity is at least O(log(n))   
		// HINT: if you use multiple data structures, make sure all of them are updated
		mapdata.put(symbol, stock);
		Map.Entry<String, stockInfo> entry = new MyEntry<String, stockInfo>(symbol, stock);
		treedata.add(entry);
	}
	
	final class MyEntry<K, V> implements Map.Entry<K, V> {
	    private final K key;
	    private V value;

	    public MyEntry(K key, V value) {
	        this.key = key;
	        this.value = value;
	    }

	    @Override
	    public K getKey() {
	        return key;
	    }

	    @Override
	    public V getValue() {
	        return value;
	    }

	    @Override
	    public V setValue(V value) {
	        V old = this.value;
	        this.value = value;
	        return old;
	    }
	}
	
	public stockInfo get(String symbol) {
		// TODO: implement this method to quickly retrive record from database
		// HINT: time complexity should be O(1) constant time 
		return mapdata.get(symbol);
	}
	
	public List<Map.Entry<String, stockInfo>> top(int k) {
		// TODO: implement this method to return the stock records with top k prices.
		// HINT: this retrieval should be done in O(k) 
		// HINT: you can use Iterator to retrive items in the sorted order. For example, if you use TreeSet,
		//       the Iterator can be created like: 
		//			set = new TreeSet<Map.Entry<String, stockInfo>>;
		//			Iterator<Map.Entry<String, stockInfo>> setIterator = set.iterator();
		//		 more usages of iterator can be learned from: https://www.geeksforgeeks.org/treeset-iterator-method-in-java/
		
		Iterator<Map.Entry<String, stockInfo>> setIterator = treedata.iterator();
		int i = 0;
		while(i<k)
		{
			topk.add(setIterator.next());
			i++;
		}
		return (List<Entry<String, stockInfo>>) topk;
	}
	

    public static void main(String[] args) throws IOException {   	
    	
    	// test the database creation based on the input file
    	myStock techStock = new myStock();
    	String[] symbols = new String[10];
        String [] allSymbols = new String[765];
        int i = 0;
        try {
            File myObj = new File("data/US-Tech-Symbols.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                symbols = data.split(":");
                allSymbols[i] = symbols[0];
                i++;
            }
			Map<String, Stock> stocks = YahooFinance.get(allSymbols);
			i = 0;
			myReader = new Scanner(myObj);
			while(myReader.hasNextLine()){
				String data = myReader.nextLine();
                symbols = data.split(":");
				if((stocks.get(symbols[0]) != null) && (stocks.get(symbols[0]).getName() != null) && (stocks.get(symbols[0]).getQuote().getPrice() != null)){
					stockInfo newstockInfo = new stockInfo( stocks.get(symbols[0]).getName(),  stocks.get(symbols[0]).getQuote().getPrice());
    				techStock.insertOrUpdate(symbols[0], newstockInfo);
				}
			}
		}	catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        
		// test the top operation
		int g = 1;
		System.out.println("===========Top 10 stocks===========");
		for (Map.Entry<String, stockInfo> element : techStock.top(10)) {
			System.out.println("[" + g + "]" +element.getKey() + " " + element.getValue());
			g++;
		}
			
		// test the get operation
		System.out.println("===========Stock info retrieval===========");
		System.out.println("VMW" + " " + techStock.get("VMW"));
		System.out.println("MSFT" + " " + techStock.get("MSFT"));
    }
}