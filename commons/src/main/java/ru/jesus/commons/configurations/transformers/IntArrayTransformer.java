package ru.jesus.commons.configurations.transformers;

import java.lang.reflect.Field;

import ru.jesus.commons.configurations.PropertyTransformer;
import ru.jesus.commons.configurations.TransformFactory;
import ru.jesus.commons.configurations.TransformationException;
import ru.jesus.commons.util.ArrayUtils;

public class IntArrayTransformer implements PropertyTransformer<int []> {
	static {
		TransformFactory.registerTransformer(int[].class, new IntArrayTransformer());
	}
	@Override
	public int[] transform(String value, Field field, Object... data)
			throws TransformationException {
		int [] result = {};
		for(String s : value.split(",")) try {
			result = ArrayUtils.add(result, Integer.valueOf(s.trim()));
		} catch(NumberFormatException nfe  ) {} 
			
		return result;
	}

}
