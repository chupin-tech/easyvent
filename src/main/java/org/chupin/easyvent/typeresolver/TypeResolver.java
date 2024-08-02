package org.chupin.easyvent.typeresolver;

import static org.chupin.easyvent.constant.Names.*;
import static org.chupin.easyvent.validation.Validation.*;

/**
 * This class has a responsibility to parse a type of class
 * and check whether an argument is assignable or instance of the type.
 * The first implementation is really simple.
 * However, it will be improved.
 *
 * @author monorisk, seungjoopet
 */
public final class TypeResolver {

	private final Class<?> clazz;

	private TypeResolver(final Class<?> clazz) {
		this.clazz = checkNotNull(clazz, CLAZZ);
	}

	/**
	 * @param clazz The literal of the value.
	 * @return Create an instance from a class literal.
	 */
	public static TypeResolver of(final Class<?> clazz) {
		checkNotNull(clazz, CLAZZ);

		return new TypeResolver(clazz);
	}

	/**
	 * Check whether the other class can be assignable to this class.
	 *
	 * @param otherClazz The class literal to check.
	 * @return Return true if the other class is a same or subclass of this class.
	 * 	If it's not, return false
	 */
	public boolean isAssignableFrom(final Class<?> otherClazz) {
		checkNotNull(otherClazz, OTHER_CLAZZ);

		return clazz.isAssignableFrom(otherClazz);
	}
}
