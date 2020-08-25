package Settlers;


/*
 * This is the constants class... just keeps some of the
 * magic numbers in the game
 */
public class Constants {
	
	public static final double DIMENSIONS = 1000;
	public static final int MAX_VERTICES = 54;
	// Given by ((x1,y1),(x2,y2),...)... experimentally found by looking at the default board
	public static final double[][] VERTEX_COORDINATES = {		
															{400,50},
															{270,125},
															{270,275},
															{400,350},
															{530,275},
															{530,125},
	                                                    	
	                                                    		{660,50},
	                                                    		{530,125},
	                                                    		{530,275},
	                                                    		{660,350},
	                                                    		{790,275},
	                                                    		{790,125},
	                                                    		
	                                                    		{920	,50},
	                                                    		{790,125},
	                                                    		{790,275},
	                                                    		{920	,350},
	                                                    		{1050,275},
	                                                    		{1050,125},
	                                                    		
	                                                    		{270,275},
	                                                    		{150	,350},
	                                                    		{150	,500},
	                                                    		{270	,575},
	                                                    		{400	,500},
	                                                    		{400,350},
	                                                    		
	                                                    		{530,275},
	                                                    		{400,350},
	                                                    		{400	,500},
	                                                    		{530	,575},
	                                                    		{660	,500},
	                                                    		{660,350},
	                                                    		
	                                                    		{790,275},
	                                                    		{660,350},
	                                                    		{660	,500},
	                                                    		{790	,575},
	                                                    		{920	,500},
	                                                    		{920	,350},
	                                                    		
	                                                    		{1050,275},
	                                                    		{920	,350},
	                                                    		{920	,500},
	                                                    		{1050,575},
	                                                    		{1180,500},
	                                                    		{1180,350},
	                                                    		
	                                                    		{150	,500},
	                                                    		{20,575},
	                                                    		{20,725},
	                                                    		{150	,800},
	                                                    		{270	,725},
	                                                    		{270	,575},
	                                                    		
	                                                    		
	                                                    		{400	,500},
	                                                    		{270	,575},
	                                                    		{270	,725},
	                                                    		{400	,800},
	                                                    		{530	,725},
	                                                    		{530	,575},
	                                                    		
	                                                    		{660	,500},
	                                                    		{530	,575},
	                                                    		{530	,725},
	                                                    		{660,800},
	                                                    		{790,725},
	                                                    		{790,575},
	                                                    		
	                                                    		{920,500},
	                                                    		{790,575},
	                                                    		{790,725},
	                                                    		{920	,800},
	                                                    		{1050,725},
	                                                    		{1050,575},
	                                                    		
	                                                    		{1180,500},
	                                                    		{1050,575},
	                                                    		{1050,725},
	                                                    		{1180,800},
	                                                    		{1310,725},
	                                                    		{1310,575},
	                                                    		
	                                                    		{270,725},
	                                                    		{150,800},
	                                                    		{150	,950},
	                                                    		{270	,1025},
	                                                    		{400	,950},
	                                                    		{400,800},
	                                                    		
	                                                    		{530,725},
	                                                    		{400,800},
	                                                    		{400, 950},
	                                                    		{530	,1025},
	                                                    		{660	,950},
	                                                    		{660,800},
	                                                    		
	                                                    		{790,725},
	                                                    		{660,800},
	                                                    		{660	,950},
	                                                    		{790	,1025},
	                                                    		{920	,950},
	                                                    		{920,800},
	                                                    		
	                                                    		{1050,725},
	                                                    		{920,800},
	                                                    		{920	,950},
	                                                    		{1050,1025},
	                                                    		{1180,950},
	                                                    		{1180, 800},
	                                                    		
	                                                    		{400,950},
	                                                    		{270,1025},
	                                                    		{270	,1175},
	                                                    		{400	,1250},
	                                                    		{530	,1175},
	                                                    		{530,1025},
	                                                    		
	                                                    		{660	,950},
	                                                    		{530,1025},
	                                                    		{530	,1175},
	                                                    		{660	,1250},
	                                                    		{790	,1175},
	                                                    		{790	,1025},
	                                                    		
	                                                    		{920,950},
	                                                    		{790,1025},
	                                                    		{790,1175},
	                                                    		{920,1250},
	                                                    		{1050,1175},
	                                                    		{1050,1025} };
	public static final double SCALE = .50;
	public static final double VERTEX_SIZE = 10;
	public static final int MAX_POINTS = 5;
	public static final int SETTLEMENT_POINTS = 1;
	public static final int CITY_POINTS = 2;
	public static final int LONGEST_ROAD = 1;
	public static final int LARGEST_ARMY = 1;
	public static final double DURATION = 100;
	public static final int INFLECTION_ROUND = 15;
	public static final double DISCOUNT = 0.50;
	
	
}
