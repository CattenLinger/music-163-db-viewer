package com.shinonometn.hacks.music.viewer.utils;

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

    public static void call(ExceptionConsumer consumer){
        try {
            consumer.call();
        }catch (Throwable e){
            throwable(e);
        }
    }

    private static <E extends Throwable> void throwable(Throwable throwable) throws E {
        throw (E)throwable;
    }

    public interface ExceptionSupplier<T>{
        T get() throws Throwable;
    }

    public interface ExceptionConsumer {
        void call() throws Throwable;
    }
}
