package com.neilclarke.tech;

import org.springframework.stereotype.Service;

import com.sun.jna.Library;
import com.sun.jna.Native;

@Service
public class NativeIncrementer implements Incrementer {

	/*
	 * Binding to native method
	 */
	public interface IncrementLibrary extends Library {

		// "libinc.so"
		static final String LIB_NAME = "inc";

		IncrementLibrary INSTANCE = (IncrementLibrary) Native.loadLibrary(LIB_NAME,
				IncrementLibrary.class);

		/*
		 * just increment the number provided
		 */
		public int increment(int number);
	}
	
	@Override
	public int increment(int number) {
		return IncrementLibrary.INSTANCE.increment(number);
	}

	public static void main(String[] args) {
		System.out.println(new NativeIncrementer().increment(20));
	}
}
