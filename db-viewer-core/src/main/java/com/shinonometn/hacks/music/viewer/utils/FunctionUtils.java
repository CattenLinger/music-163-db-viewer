package com.shinonometn.hacks.music.viewer.utils;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public final class FunctionUtils {

    public static <T> T invoke(ExceptionSupplier<T> supplier){
        try{
            return supplier.get();
        }catch (Throwable e){
            throwable(e);
            return null;
        }
    }

    public static <E extends Throwable> void throwable(Throwable throwable) throws E {
        throw (E)throwable;
    }

    public interface ExceptionSupplier<T>{
        T get() throws Throwable;
    }
}
