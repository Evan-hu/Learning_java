package com.company.shop.util;

public class DistanceUtil {
	public final static double DEFAULT_DISTANCE = 9999999999.99d;
			
	public final static double EARTH_RADIUS = 6378137.0;// 鍙朩GS84鏍囧噯鍙傝�妞悆涓殑鍦扮悆闀垮崐寰�鍗曚�?m)
    
	/**
     * 鏍规嵁缁忕含搴︼紝鑾峰彇涓ょ偣闂寸殑璺濈�?
     * @param lng1 缁忓�?
     * @param lat1 绾�?
     * @param lng2
     * @param lat2
     * @return 鍗曚綅m
     */
    public static double getDistance(double lng1, double lat1, double lng2, double lat2) {
    	if(lng1 > 0 && lat1 > 0 && lng2 > 0 && lat2 > 0){
            double radLat1 = lat1 * Math.PI / 180;
            double radLat2 = lat2 * Math.PI / 180;
            double a = radLat1 - radLat2;
            double b = lng1 * Math.PI / 180 - lng2 * Math.PI / 180;
            double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1)
                    * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
            s = s * EARTH_RADIUS;
            s = Math.round(s * 10000) / 10000;
            return s;
    	}
    	else {
    		return DEFAULT_DISTANCE;
		}
    }
    
    public static double getDistanceWithKm(double lng1, double lat1, double lng2, double lat2){
    	return meterToKm(getDistance(lng1, lat1, lng2, lat2));
    }
    
    public static double meterToKm(double meter){
    	return Math.round(meter * 10) / (10 * 1000);
    }
    
    public static double kmToMeter(double km){
    	return km * 1000;
    }
    
    /**
     * 鑾峰彇�?硅窛绂荤殑鎻忚堪澶т簬9999.99 鍗曚綅Km 鍚﹀垯m
     * @return
     */
    public static String getDistanceDesc(double lng1, double lat1, double lng2, double lat2){
    	return getDistanceDesc(getDistance(lng1, lat1, lng2, lat2));
    }
    
    /**
     * 鑾峰彇�?硅窛绂荤殑鎻忚堪澶т簬9999.99 鍗曚綅Km 鍚﹀垯m
     * @param distance
     * @return
     */
    public static String getDistanceDesc(double distance){
    	if(distance < 0){
    		distance = DEFAULT_DISTANCE;
    	}
    	if(distance < 9999.99d){
    		return Math.round(distance) + "M";
    	}
    	else{
    		return Math.round(distance / 1000 * 100) / 100 + "KM";
    	}
    }
    
    
}
