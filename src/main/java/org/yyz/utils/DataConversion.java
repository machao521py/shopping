package org.yyz.utils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.yyz.helper.MyError;

public class DataConversion {
	

	/**作废 被dateModelToMapModel(List<?> list,Map<String, Object> fields)取代
	 * @deprecated
	 * @param list
	 * @param depth
	 * @param fields
	 * @return
	 */
	public static List<Map<String, Object>> dateModelToMapModel(List<?> list,Map<String, Object> depth, String[] fields) {
		List<Map<String, Object>> bean = new ArrayList<>();
		if (list != null && list.size() > 0) {
			if (list.get(0).getClass().isArray()) {// 数组类型
			//	throw new MyError("list存的不是对象");
			} else {// JavaBean或Map类型
				for (Object obj : list) {
					bean.add(dateModelToMapModel(obj,depth,fields));
				}
			}
		}
		return bean;
	}
	/**
	 * 
	 * @param list
	 * @param depth
	 * @param fields
	 * @return
	 */
	public static List<Map<String, Object>> dateModelToMapModel(Collection<?> list,Map<String, Object> fields) {
		List<Map<String, Object>> bean = new ArrayList<>();
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				bean.add(dateModelToMapModel(obj,fields));
			}
		}
		return bean;
	}

	/**作废 被dateModelToMapModel(List<?> list,Map<String, Object>)取代
	 * @deprecated
	 * @param list
	 * @param depth
	 * @param fields
	 * @return
	 */
	public static Map<String, Object> dateModelToMapModel(Object o,Map<String, Object> depth, String[] fields) {
		Map<String, Object> bean = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		Map<String, Object> map = (o instanceof Map) ? (Map<String, Object>) o: beanToMap(o,depth);
		if(fields==null){
			bean = map;
		}else{
			for (String key : fields) {
				bean.put(key, map.get(key));
			}
		}
		
		return bean;
	}

	/**
	 * 
	 * @param list
	 * @param depth
	 * @param fields
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public static Map<String, Object> dateModelToMapModel(Object o,Map<String, Object> fields) {
		Map<String, Object> bean = new HashMap<String, Object>();
		PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
		Stack<Object> sk = new Stack<Object>(); 
		Stack<Map<String, Object>> skMap = new Stack<Map<String, Object>>();
		Stack<Map<String, Object>> skMapfields = new Stack<Map<String, Object>>();
		sk.add(o);
		skMapfields.add(fields);
		skMap.add(bean);
		while(!sk.isEmpty()){
			Map<String, Object> bean2 = skMap.pop();
			Map<String, Object> fields2 = skMapfields.pop();
			Object o1 = sk.pop();
			if(o1 instanceof Map){
				Map<String, Object> map =  (Map<String, Object>) o;
				if(fields2==null){
					bean = map;
				}else{
					Set<String> fieldsKey = fields2.keySet();
					for (Object key : fieldsKey) {
						bean2.put(key.toString(), map.get(key.toString()));
					}
				}
			}else{
				Set<String> fieldsKey = fields2.keySet();
				for (String key : fieldsKey) {
					try {
						Object o2 = propertyUtilsBean.getNestedProperty(o1, key);
						Object o3 =fields2.get(key);
						if(o2==null){
							continue;
						}else if(o2 instanceof Collection){
							if(o3 instanceof Map){
								//TODO 用栈代替递归
								List<Map<String, Object>> bean3 = new ArrayList<>();
								Collection<Object> list= (Collection<Object>)o2;
								if (list != null && list.size() > 0) {
									for (Object obj : list) {
										Map<String, Object> bean4 = new HashMap<String, Object>();
										skMapfields.add((Map<String, Object>)o3);
										bean3.add(bean4);
										skMap.add(bean4);
										sk.add(obj);
									}
									
								}
								bean2.put(key, bean3);
								
							}else{
								throw new MyError("Map<String, Object> 存的不是map或者是String类型的数据");
							}
							
							continue;
						}
						if(o3 instanceof Map){
							sk.add(o2);
							Map<String, Object> bean3 = new HashMap<String, Object>();
							bean2.put(key, bean3);
							skMap.add(bean3);
							skMapfields.add((Map<String, Object>)o3);
						}else if(!(o3==Object.class)){
							bean2.put(key, o2);
						}else{
							throw new MyError("Map<String, Object> 存的不是map或者是String类型的数据");
						}
						
						
					} catch (Exception e) {
						throw new MyError("引用了不存在的方法或者属性或者对象不存在  key"+key+"  详细： "+e.getLocalizedMessage());
					}
					
				}
			
			}
		
		}
		
		return bean;
		
	}

	
	public static Map<String,Object> ObjectsToMap(Object[] os){
		Map<String,Object> map = new HashMap<String, Object>();
		for(Object o : os){
			map.put(o.getClass().getSimpleName(), o);
		}
		return map;
	}
	
	

	/**作废，内存消耗太大
	 * @deprecated 
	 * @param list
	 * @param depth
	 * @param fields
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, Object> beanToMap(Object obj,Map<String, Object> depth) {
		Map<String, Object> params = new HashMap<String, Object>(0);
		if(depth==null){
			depth = new HashMap<>();
		}
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!name.equals("class")) {
					Object o = propertyUtilsBean.getNestedProperty(obj, name);
					Object s = depth.get(name);
					if(s!=null){
						if(s instanceof Map){
							params.put(name, beanToMap(o,(Map<String, Object>)s));
						}else  if(s instanceof String){
							params.put(name,propertyUtilsBean.getNestedProperty(o, s.toString()));
						}else{
							throw new MyError("Map<String, Object> 存的不是map或者是String类型的数据");
						}
						
					}else{
						params.put(name,o);
					}
					
				}
			}
		} catch (Exception e) {
			throw new MyError("对象错误");
		}
		return params;
	}
}
