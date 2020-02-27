package code.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class Main {
    static class ReferenceTest {
        static final int _1M = 1024;
        // equal to: = new ReferenceQueue<Object>();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();

        // 测试结果：内存直线上升，直到内存溢出，程序退出。
        void testStrongReference() {
            ArrayList<byte[]> strongReferences = new ArrayList<>();
            try {
                while (true) {
                    strongReferences.add(new byte[_1M]);
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }

        // 测试结果，内存上升后稳定在一个值不变。因为软引用会在 OutOfMemoryError 时被回收。
        void testSoftReference() {
            ArrayList<SoftReference<byte[]>> softReferences = new ArrayList<>();
            try {
                while (true) {
                    softReferences.add(new SoftReference<>(new byte[_1M], referenceQueue));
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }

        // 测试结果，和 testSoftReference 一样。
        void testWeakReference() {
            ArrayList<WeakReference<byte[]>> weakReferences = new ArrayList<>();
            try {
                while (true) {
                    weakReferences.add(new WeakReference<>(new byte[_1M], referenceQueue));
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }

        // 测试结果，也是维持在一个高内存使用率上。
        void testPhantomReference() {
            ArrayList<PhantomReference<byte[]>> phantomReferences = new ArrayList<>();
            try {
                while (true) {
                    phantomReferences.add(new PhantomReference<byte[]>(new byte[_1M], referenceQueue));
                }
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ReferenceTest test = new ReferenceTest();
        test.testPhantomReference();
    }
}