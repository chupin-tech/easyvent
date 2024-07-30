package org.chupin.easyvent.validation;

import javax.annotation.Nullable;

/**
 * This class provides methods to check the value status. Methods have to be included common
 * context.
 *
 * @author monorisk
 */
public final class Validation {
	private Validation() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Check whether the value is null or not.
	 *
	 * @param value The value that needs to be checked for null.
	 * @param name  The name of the value. It can be null.
	 * @return If the value is not null, the same value with the value will be returned.
	 * @throws IllegalArgumentException If the value is null, the IllegalArgumentException will be
	 *                                  thrown.
	 */
	public static <T> T checkNotNull(final T value, @Nullable final String name) {
		if (value != null) {
			return value;
		}

		throw new IllegalArgumentException(String.format("%s is null.", adjustName(name)));
	}

	private static String adjustName(@Nullable final String name) {
		return name == null ? "value" : name;
	}
}
