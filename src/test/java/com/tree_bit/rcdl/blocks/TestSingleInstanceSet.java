package com.tree_bit.rcdl.blocks;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.google.common.collect.ImmutableSet;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;


@SuppressWarnings("javadoc")
public class TestSingleInstanceSet {

    private static class A {

        @Override
        public String toString() {
            return "A";
        }
    }
    @SuppressWarnings("synthetic-access")
    private static class B extends A {

        @Override
        public String toString() {
            return "B";
        }
    }
    @SuppressWarnings("synthetic-access")
    private static class C extends B {

        @Override
        public String toString() {
            return "C";
        }
    }
    @SuppressWarnings("synthetic-access")
    private static class D extends A {

        @Override
        public String toString() {
            return "D";
        }
    }

    @SuppressWarnings("synthetic-access")
    private static A a1 = new A();
    @SuppressWarnings("synthetic-access")
    private static A a2 = new A();
    @SuppressWarnings("synthetic-access")
    private static B b = new B();
    @SuppressWarnings("synthetic-access")
    private static C c = new C();
    @SuppressWarnings("synthetic-access")
    private static D d = new D();
    private static A dAsA = d;

    private SingleInstanceSet<A> setA = new SingleInstanceSet<>();
    private SingleInstanceSet<A> setA_B = new SingleInstanceSet<>(B.class);
    private SingleInstanceSet<A> setA_A = new SingleInstanceSet<>(A.class);

    @BeforeClass
    public static void setUpBeforeClass() {
        // Code coverage
        a1.toString();
        b.toString();
        c.toString();
        d.toString();
    }

    @Before
    public void setUp() throws Exception {
        this.setA = new SingleInstanceSet<>();
        this.setA_B = new SingleInstanceSet<>(B.class);
        this.setA_A = new SingleInstanceSet<>(A.class);
    }

    @SuppressWarnings("static-method")
    @Test
    public void testSingleInstanceConstructorEqualityConstraint() {
        final SingleInstanceSet<C> theSet = new SingleInstanceSet<>(C.class);
        final HashSet<Class<? extends C>> hs = new HashSet<>();
        hs.add(C.class);
        assertEquals(theSet, new SingleInstanceSet<>(hs));
    }

    @SuppressWarnings("static-method")
    @Test
    public void testSingleInstanceConstructorEquality() {
        final SingleInstanceSet<C> theSet = new SingleInstanceSet<>();
        final HashSet<Class<? extends C>> hs = new HashSet<>();
        assertEquals(theSet, new SingleInstanceSet<>(hs));
    }

    @Test
    public void testAdd() {
        this.setA.add(a1);
        this.setA.add(b);
        this.setA.add(c);
        this.setA.add(d);

        assertTrue(this.setA.asSet().containsAll(ImmutableSet.of(a1, b, c, d)));
    }

    @Test
    public void testAddSameClass() {
        this.setA.add(a1);
        this.setA.add(a2);

        assertTrue(this.setA.asSet().contains(a2));
        assertFalse(this.setA.asSet().contains(a1));
    }

    @Test
    public void testAddCast() {
        this.setA.add(a1);
        this.setA.add(dAsA);
        assertTrue(this.setA.asSet().containsAll(ImmutableSet.of(a1, d)));
    }

    @Test
    public void testAddConstraintA() {
        this.setA_A.add(b);
        this.setA_A.add(c);


        assertTrue(this.setA_A.asSet().contains(c));
        assertFalse(this.setA_A.asSet().contains(b));
    }

    @Test
    public void testAddConstraintB() {
        this.setA_B.add(b);
        this.setA_B.add(c);
        this.setA_B.add(a1);

        assertTrue(this.setA_B.asSet().contains(c));
        assertTrue(this.setA_B.asSet().contains(a1));
        assertFalse(this.setA_B.asSet().contains(b));
    }

    @Test
    public void testAddConstraintBCast() {
        final B cAsB = c;
        this.setA_B.add(b);
        this.setA_B.add(cAsB);
        this.setA_B.add(a1);

        assertTrue(this.setA_B.asSet().contains(c));
        assertTrue(this.setA_B.asSet().contains(a1));
        assertFalse(this.setA_B.asSet().contains(b));
    }

    @Test
    public void testIterator() {
        this.setA.add(a1);
        this.setA.add(b);

        final Set<A> content = new HashSet<>();

        for (final A foo : this.setA) {
            content.add(foo);
        }
        assertEquals(content, this.setA.asSet());
    }

    @Test
    public void testGet() {
        this.setA.add(a1);
        this.setA.add(b);

        assertEquals(a1, this.setA.get(A.class).get());
        assertEquals(b, this.setA.get(B.class).get());
    }

    @Test
    public void testGetInstancesOf() {
        this.setA.add(a1);
        this.setA.add(b);
        this.setA.add(c);

        final Set<B> foo = this.setA.getInstancesOf(B.class);

        assertTrue(foo.containsAll(ImmutableSet.of(b, c)));
        assertFalse(foo.contains(a1));
    }

    @SuppressWarnings("static-method")
    @Test
    public void testCopyOf() {
        final SingleInstanceSet<B> foo = SingleInstanceSet.copyOf(ImmutableSet.of(b, c));

        assertTrue(foo.asSet().containsAll(ImmutableSet.of(b, c)));
    }

    @Test
    public void testToString() {
        assertFalse(this.setA.toString().contains("@"));
    }

}
