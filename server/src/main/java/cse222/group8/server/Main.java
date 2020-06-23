package cse222.group8.server;
import java.io.File;
import java.util.Iterator;
import java.util.Scanner;
import java.util.TreeMap;

import cse222.group8.server.DataStructures.*;


public class Main {
	
	
	
	public static void main(String[] args) {
		
		
		
	}
	
	
	private static void testCities() {
		
		BinarySearchTree<City> cities = new BinarySearchTree<City>();
		TreeMap<Integer, City> cityIds = new TreeMap<Integer, City>();
		// number of vertex must be 81 + 1  
		// graph is directed
		ListGraph borderCities = new ListGraph(82, true);	
		
		
		readCityInfo(cities, cityIds, borderCities);
		
		
		City city = cities.find(new City("Mersin", 0,null));
		System.out.println(city.getCityId());
		
		System.out.println(cityIds.get(city.getCityId()).getName());
		
		System.out.println(" ");
		
		
		Iterator<Edge> iter = borderCities.edgeIterator(city.getCityId());
		while(iter.hasNext()) {
			
			int id = iter.next().getDest();
			
			System.out.println(cityIds.get(id).getName());
			
		}
		
		
		
	}
	
	
	protected static void readCityInfo(BinarySearchTree<City> cities, TreeMap<Integer, City> cityIds, ListGraph borderCities) {
		
		File file = new File("Cities.txt");
		
		try {
			
			Scanner sc = new Scanner(file);
			
			while( sc.hasNextLine() ) {
				
				String str = sc.nextLine();
				String[] keys = str.split(" ");
				
				int cityID = Integer.parseInt(keys[0]);
				City city = new City( keys[1], cityID , null);
				
				
				cities.add(city);
				
				cityIds.put(cityID, city);
				
				for(int i=2; i < keys.length; i++ ) {
					
					Edge edge = new Edge(cityID, Integer.parseInt(keys[i]));
					borderCities.insert(edge);
								
				}
						
				
			}
			
			sc.close();
				
		}
		catch ( Exception e ) {
			System.out.println(e.getMessage());
			System.err.println("Something went wrong while reading cities from file");
			System.exit(0);
			
		}
		
		
	}
	
	
	

}
