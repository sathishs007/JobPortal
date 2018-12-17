package com.myjobkart.utils;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import com.myjobkart.vo.BasicEntity;

public class TypeResolver {

	public static boolean isEnumType(Class<?> valueType) {
		return valueType.isEnum();
	}

	// ----------------------------------------------------------------------------

	public static boolean isNumericType(Class<?> c) {
		if (TypeResolver.isLongType(c)) {
			return true;
		}
		if (TypeResolver.isIntegerType(c)) {
			return true;
		}
		if (TypeResolver.isDoubleType(c)) {
			return true;
		}
		if (TypeResolver.isByteType(c)) {
			return true;
		}
		if (TypeResolver.isFloatType(c)) {
			return true;
		}
		if (TypeResolver.isBigDecimalType(c)) {
			return true;
		}
		return false;

	}

	public static boolean isBooleanType(Class<?> c) {
		if (c.equals(Boolean.class)) {
			return true;
		}
		if (c.equals(Boolean.TYPE)) {
			return true;
		}

		return false;
	}

	public static boolean isLongType(Class<?> c) {
		if (c.equals(Long.class)) {
			return true;
		}
		if (c.equals(Long.TYPE)) {
			return true;
		}

		return false;
	}

	public static boolean isIntegerType(Class<?> c) {
		if (c.equals(Integer.class)) {
			return true;
		}
		if (c.equals(Integer.TYPE)) {
			return true;
		}

		return false;
	}

	public static boolean isByteType(Class<?> c) {
		if (c.equals(Byte.class)) {
			return true;
		}
		if (c.equals(Byte.TYPE)) {
			return true;
		}

		return false;
	}

	public static boolean isFloatType(Class<?> c) {
		if (c.equals(Float.class)) {
			return true;
		}
		if (c.equals(Float.TYPE)) {
			return true;
		}

		return false;
	}

	public static boolean isDoubleType(Class<?> c) {
		if (c.equals(Double.class)) {
			return true;
		}
		if (c.equals(Double.TYPE)) {
			return true;
		}

		return false;
	}

	public static boolean isDateType(Class<?> c) {
		if (c.equals(Date.class)) {
			return true;
		}
		return false;
	}

	public static boolean isStringType(Class<?> c) {
		if (c.equals(String.class)) {
			return true;
		}

		return false;
	}

	/**
	 * @return true
	 *         <code>if (isStringType() || isNumericType() || isBooleanType())</code>
	 */
	public static boolean isPrimitiveType(Class<?> c) {
		return TypeResolver.isStringType(c) || TypeResolver.isNumericType(c)
				|| TypeResolver.isBooleanType(c);
	}

	/**
	 * @return true if property type is instanceof BasicEntity
	 */
	public static boolean isBusinessObjectType(Class<?> c) {
		if (BasicEntity.class.isAssignableFrom(c)) {
			return true;
		}
		return false;
	}

	public static boolean isCollectionType(Class<?> c) {
		if (Collection.class.isAssignableFrom(c)) {
			return true;
		}
		return false;
	}

	private static boolean isBigDecimalType(Class<?> c) {
		if (BigDecimal.class.isAssignableFrom(c)) {
			return true;
		}

		return false;
	}

}
