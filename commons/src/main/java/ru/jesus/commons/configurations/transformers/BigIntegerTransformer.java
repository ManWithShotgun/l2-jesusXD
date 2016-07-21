package ru.jesus.commons.configurations.transformers;

import java.lang.reflect.Field;
import java.math.BigInteger;

import ru.jesus.commons.configurations.PropertyTransformer;
import ru.jesus.commons.configurations.TransformFactory;
import ru.jesus.commons.configurations.TransformationException;

public class BigIntegerTransformer implements PropertyTransformer<BigInteger> {
	static {
		TransformFactory.registerTransformer(BigInteger.class, new BigIntegerTransformer());
	}
	@Override
	public BigInteger transform(String value, Field field, Object... data)
			throws TransformationException {
		return new BigInteger(value);
	}
	

}
