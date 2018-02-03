package com.shinonometn.hacks.music.viewer.utils;

import sun.misc.Unsafe;

import java.lang.reflect.*;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ReflectionHelper {
    private static final Unsafe UNSAFE;
    private static final long OBJECT_FIELD_OFFSET;
    private static final Map<String, Class<?>> PRIMITIVES;

    static {
        try {
            UNSAFE = (Unsafe) AccessController.doPrivileged((PrivilegedExceptionAction<Object>) () -> {
                Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
                theUnsafe.setAccessible(true);
                return theUnsafe.get(null);
            });

            OBJECT_FIELD_OFFSET = UNSAFE.objectFieldOffset(getField(AccessibleObject.class, "override"));

        } catch (PrivilegedActionException ex) {
            throw new AssertionError(ex);
        }

        PRIMITIVES = new HashMap<String,Class<?>>() {
            {
                put("byte", Byte.class);
                put("short", Short.class);
                put("int", Integer.class);
                put("long", Long.class);
                put("char", Character.class);
                put("float", Float.class);
                put("double", Double.class);
                put("boolean", Boolean.class);
            }
        };
    }

    private static void setAccessibleForcibly(AccessibleObject object) {
        UNSAFE.putBoolean(object, OBJECT_FIELD_OFFSET, true);
    }

    public static Method getMethod(Object object, String name) {
        return getMethod(object.getClass(), name);
    }

    public static Method getMethod(Class<?> clazz, String name) {
        try {
            Method m = clazz.getDeclaredMethod(name);
            setAccessibleForcibly(m);
            return m;
        } catch (Exception e) {
            return null;
        }
    }

    public static Field getField(Object object, String name) {
        return getField(object.getClass(), name);
    }

    public static Field getField(Class<?> clazz, String name) {
        try {
            Field f = clazz.getDeclaredField(name);
            setAccessibleForcibly(f);
            return f;
        } catch (Exception e) {
            return null;
        }
    }

    public static Object call(Class<?> cls, String name, Object object, Object... args) {
        try {
            if (args.length == 0)
                try {
                    return cls.getDeclaredField(name).get(object);
                } catch (NoSuchFieldException ignored) {
                }
            if ("new".equals(name)) {
                for (Constructor<?> c : cls.getDeclaredConstructors())
                    if (checkParameter(c, args))
                        return c.newInstance(args);
            } else
                return forMethod(cls, name, args).get().invoke(object, args);
            throw new RuntimeException();
        } catch (Exception e) {
            throw new IllegalArgumentException("Cannot find '" + name + "' in class '" + cls.getName() + "'", e);
        }
    }

    public static Object construct(Class<?> clazz, Object... args) {
        return call(clazz, "new", null, args);
    }

    public static Object call(Object obj, String name, Object... args) {
        return call(obj.getClass(), name, obj, args);
    }

    public static boolean checkParameter(Executable exec, Object... args) {
        Class<?>[] cArgs = exec.getParameterTypes();
        if (args.length == cArgs.length) {
            for (int i = 0; i < args.length; ++i) {
                Object arg = args[i];
                if (arg != null ? !isInstance(cArgs[i], arg) : cArgs[i].isPrimitive())
                    return false;
            }
            setAccessibleForcibly(exec);
            return true;
        } else
            return false;
    }

    public static boolean isInstance(Class<?> superClass, Object obj) {
        return superClass.isInstance(obj) || PRIMITIVES.get(superClass.getName()) == obj.getClass();
    }

    public static Optional<Method> forMethod(Class<?> cls, String name, Object... args) {
        return Arrays.stream(cls.getDeclaredMethods())
                .filter(s -> name.equals(s.getName()))
                .filter(s -> checkParameter(s, args))
                .findFirst();
    }
}
