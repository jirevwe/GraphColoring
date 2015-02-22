/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package util;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    public static <K, V extends Comparable<? super V>> HashMap<K, V> sortMapByValue( HashMap<K, V> map )
    {
        List<Map.Entry<K, V>> list =
            new LinkedList<>( map.entrySet() );
        Collections.sort( list, (Map.Entry<K, V> o1, Map.Entry<K, V> o2) -> {
            return (o2.getValue()).compareTo( o1.getValue() );
        });

        HashMap<K, V> result = new LinkedHashMap<>();
        list.stream().forEach((entry) -> {
            result.put( entry.getKey(), entry.getValue() );
        });
        
        return result;
    }
}
