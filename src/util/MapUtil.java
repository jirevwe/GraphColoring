/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Raymond Tukpe
 */
public class MapUtil
{

    /**
     *
     * @param <K>
     * @param <V>
     * @param map the HashMap to be sorted
     * @return a version of map, sorted by the values
     */
	public static <K, V extends Comparable<? super V>> HashMap<K, V> sortByValue( HashMap<K, V> map ){
		List<HashMap.Entry<K, V>> list = new LinkedList<HashMap.Entry<K, V>>( map.entrySet() );
	    Collections.sort( list, new Comparator<HashMap.Entry<K, V>>()
	    {
	        public int compare( HashMap.Entry<K, V> o1, HashMap.Entry<K, V> o2 )
	        {
	            return (o2.getValue()).compareTo( o1.getValue() );
	        }
	    } );
	
	    HashMap<K, V> result = new LinkedHashMap<K, V>();
	    for (HashMap.Entry<K, V> entry : list)
	    {
	        result.put( entry.getKey(), entry.getValue() );
	    }
	    return result;
    }
}
