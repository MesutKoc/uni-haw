package Stowage;

import static Stowage.Entities.container;
import static Stowage.Entities.nullContainer;
import Stowage.interfaces.Container;
import Values.adminValues.Values;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class Bounded3DimStackImplTest {
    private static Bounded3DimStackImpl<Container> instance;
    private static int bays;
    private static int rows;
    private static int tiers;
    
    public Bounded3DimStackImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        bays = 2;
        rows = 4;
        tiers = 2;
        instance = Bounded3DimStackImpl.valueOf(Container.class, bays, rows, tiers);
    }
    
    @AfterClass
    public static void tearDownClass() {
        instance = null;
    }
    
    @Before
    public void setUp() {
        instance = Bounded3DimStackImpl.valueOf(Container.class, bays, rows, tiers);
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of valueOf method, of class Bounded3DimStackImpl.
     */
    @Test
    public void testValueOf() {
        assertNotNull(instance);
    }

    /**
     * Test of isEmpty method, of class Bounded3DimStackImpl.
     */
    @Test
    public void testIsEmpty() {
        assertTrue(instance.isEmpty());
        instance.load(container());
        assertFalse(instance.isEmpty());
    }

    /**
     * Test of isFull method, of class Bounded3DimStackImpl.
     */
    @Test
    public void testIsFull() {
        for (int i=0; i<(bays*rows*tiers); i++) {
            assertFalse(instance.isFull());
            instance.load(container());
        }
        assertTrue(instance.isFull());
    }

    /**
     * Test of tierIsEmpty method, of class Bounded3DimStackImpl.
     */
    @Test
    public void testTierIsEmpty() {
        assertTrue(instance.tierIsEmpty(0, 0));
        instance.load(container());
        assertFalse(instance.tierIsEmpty(0, 0));
    }

    /**
     * Test of tierIsFull method, of class Bounded3DimStackImpl.
     */
    @Test
    public void testTierIsFull() {
        for (int i=0; i<tiers; i++) {
            assertFalse(instance.tierIsFull(0, 0));
            instance.load(container());
        }
        assertTrue(instance.tierIsFull(0, 0));
    }

    /**
     * Test of contains method, of class Bounded3DimStackImpl.
     */
    @Test
    public void testContains() {
        Container elem = container();
        assertFalse(instance.contains(elem));
        instance.load(elem);
        assertTrue(instance.contains(elem));
    }

    /**
     * Test of containsAll method, of class Bounded3DimStackImpl.
     */
    @Test
    public void testContainsAll() {
        ArrayList<Container> elems = new ArrayList<>();
        for (int i=0; i<5; i++) {
            elems.add(container());
        }
        assertFalse(instance.containsAll(elems));
        
        for (int i=0; i<4; i++) {
            instance.load(elems.get(i));
        }
        assertFalse(instance.containsAll(elems));
        
        instance.load(elems.get(4));
        assertTrue(instance.containsAll(elems));
    }

    /**
     * Test of get method, of class Bounded3DimStackImpl.
     */
    @Test
    public void testGet() {
        assertEquals(nullContainer(), instance.get(Values.stowageLocation(0, 0, 0)));
        
        Container elem = container();
        instance.load(elem);
        assertEquals(elem, instance.get(Values.stowageLocation(0, 0, 0)));
    }

    /**
     * Test of getAll method, of class Bounded3DimStackImpl.
     */
    @Test
    public void testGetAll() {
        assertEquals(1, instance.getAll().size());
        for (int i=0; i<(bays*rows*tiers); i++) {
            instance.load(container());
        }
        assertEquals(bays*rows*tiers, instance.getAll().size());
    }

    /**
     * Test of locationOf method, of class Bounded3DimStackImpl.
     */
    @Test
    public void testLocationOf() {
        Container elem = container();
        instance.load(elem);
        assertEquals(Values.stowageLocation(0, 0, 0), instance.locationOf(elem));
    }

    /**
     * Test of load method, of class Bounded3DimStackImpl.
     */
    @Test
    public void testLoad_3args() {
        Container elem = container();
        assertFalse(instance.contains(elem));
        instance.load(1, 1, elem);
        assertTrue(instance.contains(elem));
        assertEquals(Values.stowageLocation(1, 1, 0), instance.locationOf(elem));
    }

    /**
     * Test of load method, of class Bounded3DimStackImpl.
     */
    @Test
    public void testLoad_GenericType() {
        Container elem = container();
        assertEquals(Values.nullStowageLocation(), elem.loc());
        assertFalse(instance.contains(elem));
        
        instance.load(elem);
        assertTrue(instance.contains(elem));
        assertEquals(Values.stowageLocation(0, 0, 0), instance.locationOf(elem));
    }

    /**
     * Test of load method, of class Bounded3DimStackImpl.
     */
    @Test
    public void testLoad_Collection() {
        ArrayList<Container> elems = new ArrayList<>();
        for (int i=0; i<5; i++) {
            elems.add(container());
        }
        assertFalse(instance.containsAll(elems));
        
        instance.loadAll(elems);
        assertTrue(instance.containsAll(elems));
    }
}