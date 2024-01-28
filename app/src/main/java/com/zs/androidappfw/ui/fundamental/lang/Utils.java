package com.zs.androidappfw.ui.fundamental.lang;

import java.lang.annotation.Annotation;
import java.lang.annotation.AnnotationFormatError;
import java.lang.annotation.AnnotationTypeMismatchException;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.IncompleteAnnotationException;
import java.lang.annotation.Inherited;
import java.lang.annotation.Native;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.invoke.CallSite;
import java.lang.invoke.ConstantCallSite;
import java.lang.invoke.LambdaConversionException;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandleInfo;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.MutableCallSite;
import java.lang.invoke.VolatileCallSite;
import java.lang.invoke.WrongMethodTypeException;
import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.GenericSignatureFormatError;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Proxy;
import java.lang.reflect.ReflectPermission;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.UndeclaredThrowableException;
import java.lang.reflect.WildcardType;

public class Utils {

    public static Class<?>[] JAVA_LANG_INTERFACES = {
            Appendable.class, AutoCloseable.class, CharSequence.class, Cloneable.class,
            Comparable.class, Iterable.class, Readable.class, Runnable.class,
            Thread.UncaughtExceptionHandler.class
    };

    public static Class<?>[] JAVA_LANG_CLASSES = {
            Byte.class, Short.class, Integer.class, Long.class,
            Boolean.class, Character.class,
            Float.class, Double.class, Number.class, Void.class,
            Object.class, Package.class, Class.class, Enum.class,
            ClassLoader.class, Compiler.class,
            Throwable.class, System.class,
            Thread.class, ThreadGroup.class,
            ThreadLocal.class, InheritableThreadLocal.class,
            StackTraceElement.class, SecurityManager.class,
            Process.class, ProcessBuilder.class,
            Runtime.class, RuntimePermission.class,
            Math.class, StrictMath.class,
            String.class, StringBuilder.class, StringBuffer.class,
    };

    public static Class<?>[] JAVA_LANG_ENUMS = {
            Character.UnicodeScript.class, ProcessBuilder.Redirect.Type.class, Thread.State.class
    };

    public static Class<?>[] JAVA_LANG_ERRORS = {
            AbstractMethodError.class, AssertionError.class, BootstrapMethodError.class,
            ClassCircularityError.class, ClassFormatError.class, Error.class,
            ExceptionInInitializerError.class, IllegalAccessError.class, IncompatibleClassChangeError.class,
            InstantiationError.class, InternalError.class, LinkageError.class,
            NoClassDefFoundError.class, NoSuchFieldError.class, NoSuchMethodError.class,
            OutOfMemoryError.class, StackOverflowError.class, ThreadDeath.class,
            UnknownError.class, UnsatisfiedLinkError.class, UnsupportedClassVersionError.class,
            VerifyError.class, VirtualMachineError.class
    };

    public static Class<?>[] JAVA_LANG_ANNOTATIONS = {
            Deprecated.class, FunctionalInterface.class, Override.class,
            SafeVarargs.class, SuppressWarnings.class
    };

    public static Class<?>[][] JAVA_LANG_CLASSES_ALL = {
            JAVA_LANG_INTERFACES, JAVA_LANG_CLASSES, JAVA_LANG_ENUMS, JAVA_LANG_ERRORS,
            JAVA_LANG_ANNOTATIONS
    };

    public static Class<?>[] JAVA_LANG_ANNOTATION_INTERFACES = {
            Annotation.class
    };

    public static Class<?>[] JAVA_LANG_ANNOTATION_ENUMS = {
            ElementType.class, RetentionPolicy.class
    };

    public static Class<?>[] JAVA_LANG_ANNOTATION_EXCEPTIONS = {
            AnnotationTypeMismatchException.class, IncompleteAnnotationException.class
    };

    public static Class<?>[] JAVA_LANG_ANNOTATION_ERRORS = {
            AnnotationFormatError.class
    };

    public static Class<?>[] JAVA_LANG_ANNOTATION_ANNOTATIONS = {
            Documented.class, Inherited.class,
            Native.class, Repeatable.class,
            Retention.class, Target.class
    };

    public static Class<?>[][] JAVA_LANG_ANNOTATION_CLASSES_ALL = {
            JAVA_LANG_ANNOTATION_INTERFACES, JAVA_LANG_ANNOTATION_ENUMS,
            JAVA_LANG_ANNOTATION_EXCEPTIONS, JAVA_LANG_ANNOTATION_ERRORS,
            JAVA_LANG_ANNOTATION_ANNOTATIONS
    };

    public static Class<?>[] JAVA_LANG_INVOKE_INTERFACES = {
            MethodHandleInfo.class
    };

    public static Class<?>[] JAVA_LANG_INVOKE_CLASSES = {
            CallSite.class, ConstantCallSite.class,
            MethodHandle.class, MethodHandles.class,
            MethodType.class, MutableCallSite.class,
            VolatileCallSite.class
    };

    public static Class<?>[] JAVA_LANG_INVOKE_EXCEPTIONS = {
            LambdaConversionException.class, WrongMethodTypeException.class
    };

    public static Class<?>[][] JAVA_LANG_INVOKE_CLASSES_ALL = {
            JAVA_LANG_INVOKE_INTERFACES, JAVA_LANG_INVOKE_CLASSES, JAVA_LANG_INVOKE_EXCEPTIONS
    };

    public static Class<?>[] JAVA_LANG_REF_CLASSES = {
            /* Cleaner.class, API Level 33 */PhantomReference.class,
            Reference.class, ReferenceQueue.class,
            SoftReference.class, WeakReference.class
    };

    public static Class<?>[][] JAVA_LANG_REF_CLASSES_ALL = {
            JAVA_LANG_REF_CLASSES
    };

    public static Class<?>[] JAVA_LANG_REFLECT_INTERFACES = {
            AnnotatedElement.class, GenericArrayType.class,
            GenericDeclaration.class, InvocationHandler.class,
            Member.class, ParameterizedType.class, Type.class, TypeVariable.class,
            WildcardType.class
    };

    public static Class<?>[] JAVA_LANG_REFLECT_CLASSES = {
            AccessibleObject.class, Array.class, Constructor.class, Executable.class,
            Field.class, Method.class, Modifier.class, Parameter.class,
            Proxy.class, ReflectPermission.class
    };

    public static Class<?>[] JAVA_LANG_REFLECT_EXCEPTIONS = {
            InvocationTargetException.class, MalformedParameterizedTypeException.class,
            /* MalformedParametersException.class, API 28*/UndeclaredThrowableException.class
    };

    public static Class<?>[] JAVA_LANG_REFLECT_ERRORS = {
            GenericSignatureFormatError.class
    };

    public static Class<?>[][] JAVA_LANG_REFLECT_CLASSES_ALL = {
            JAVA_LANG_REFLECT_INTERFACES, JAVA_LANG_REFLECT_CLASSES,
            JAVA_LANG_REFLECT_EXCEPTIONS, JAVA_LANG_REFLECT_ERRORS
    };

    public static Class<?>[][] JAVA_LANG_ALL = {
            JAVA_LANG_INTERFACES, JAVA_LANG_CLASSES, JAVA_LANG_ENUMS, JAVA_LANG_ERRORS,
            JAVA_LANG_ANNOTATIONS,

            JAVA_LANG_ANNOTATION_INTERFACES, JAVA_LANG_ANNOTATION_ENUMS,
            JAVA_LANG_ANNOTATION_EXCEPTIONS, JAVA_LANG_ANNOTATION_ERRORS,
            JAVA_LANG_ANNOTATION_ANNOTATIONS,

            JAVA_LANG_INVOKE_INTERFACES, JAVA_LANG_INVOKE_CLASSES,
            JAVA_LANG_INVOKE_EXCEPTIONS,

            JAVA_LANG_REF_CLASSES,

            JAVA_LANG_REFLECT_INTERFACES, JAVA_LANG_REFLECT_CLASSES,
            JAVA_LANG_REFLECT_EXCEPTIONS, JAVA_LANG_REFLECT_ERRORS
    };
}
