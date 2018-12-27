package Indy;

import java.util.HashMap;
import java.util.Set;

/*
 * a neat structure for mapping keys to values like a dictionary, I use it 
 * a bunch in kruskal
 */
public class Decorator<K, V> {
	
	HashMap<K,V> _hash;
	
	public Decorator() {
		_hash = new HashMap<K,V>();
  }

  public V getDecoration(K key) {
      return _hash.get(key);
  }

  /*
   * setter of the key and value
   */
  public void setDecoration(K key, V value) {
	  _hash.put(key,value);

  }

  /*
   * returns true if the key is decorated
   */
  public boolean hasDecoration(K key) {
	  return _hash.containsKey(key);
  }

  /*
   * removes the decoration and returns the value
   */
  public V removeDecoration(K key) {
    return _hash.remove(key);
  }

  //getter for the key-set
  public Set<K> getKeys() {
    return _hash.keySet();
  }
}
