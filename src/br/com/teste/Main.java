package br.com.teste;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {

	public static void main(String[] args) {

//		JpqlBuild $ = new JpqlBuild();
//		
//		$.from(Entidade.class)
//			.join(Entidade.class)
//			.on(Entidade.class)
//			.where("1=1")
//			.and("1=1");
//		
//		System.out.println($.getJpql());
		
		Entidade entidade = new Entidade();
		entidade.setDescricao("teste123");
		entidade.setId(1l);
		Field[] fields = entidade.getClass().getDeclaredFields();
		 
	    List<String> actualFieldNames = getFieldNames(fields);
	    
	    System.out.println(beanProperties2(entidade));
		
	}
	
	private static List<String> getFieldNames(Field[] fields) {
	    List<String> fieldNames = new ArrayList<>();
	    for (Field field : fields)
	      fieldNames.add(field.getName());
	    return fieldNames;
	}
	
	public static void printGettersSetters(Class aClass){
		  Method[] methods = aClass.getMethods();

		  for(Method method : methods){
		    if(isGetter(method)) System.out.println("getter: " + method);
		    if(isSetter(method)) System.out.println("setter: " + method);
		  }
		}

		public static boolean isGetter(Method method){
		  if(!method.getName().startsWith("get"))      return false;
		  if(method.getParameterTypes().length != 0)   return false;  
		  if(void.class.equals(method.getReturnType())) return false;
		  return true;
		}

		public static boolean isSetter(Method method){
		  if(!method.getName().startsWith("set")) return false;
		  if(method.getParameterTypes().length != 1) return false;
		  return true;
		}
		
	public static Map<String, Object> beanProperties(Object bean) {
		  try {
		    return Arrays.asList(
		         Introspector.getBeanInfo(bean.getClass(), Object.class)
		                     .getPropertyDescriptors()
		      )
		      .stream()
		      // filter out properties with setters only
		      .filter(pd -> Objects.nonNull(pd.getReadMethod()))
		      .collect(Collectors.toMap(
		        // bean property name
		        PropertyDescriptor::getName,
		        pd -> { // invoke method to get value
		            try { 
		                return pd.getReadMethod().invoke(bean);
		            } catch (Exception e) {
		                // replace this with better error handling
		               return null;
		            }
		        }));
		  } catch (IntrospectionException e) {
		    // and this, too
		    return Collections.emptyMap();
		  }
		}
	
	public static Map<String, Object> beanProperties2(Object bean) {
		try {
			Map<String, Object> map = new HashMap<>();
			PropertyDescriptor[] propertyDescriptors = Introspector.getBeanInfo(bean.getClass(), Object.class).getPropertyDescriptors();
			System.out.println("propertyDescriptors "+ Arrays.asList(propertyDescriptors).stream().map(PropertyDescriptor::toString).collect(Collectors.joining(", ")));
			Arrays.asList(propertyDescriptors)
				.stream()
				// filter out properties with setters only
				.filter(pd -> Objects.nonNull(pd.getReadMethod())).forEach(pd -> { // pega os valores dos métodos
					try {
						Object value = pd.getReadMethod().invoke(bean);
						if (value != null) {
							map.put(pd.getName(), value);
						}
					} catch (Exception e) {
						// add proper error handling here
					}
				});
			return map;
		} catch (IntrospectionException e) {
			// retorna uma collection vazia
			return Collections.emptyMap();
		}
	}

}
