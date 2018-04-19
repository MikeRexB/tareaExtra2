package mx.com.itam.adsi.ejercicio;

import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestEjercicio {
    private final static Logger LOG = Logger.getLogger(Ejercicio.class);
    

    
    private Ejercicio e = new Ejercicio();

    @BeforeClass
    public static void beforeClass() {
        System.out.println("Before Class");
    }

    @Before
    public void before() {
        System.out.println("Before Test Case");
    }

    @Test
    public void lineasTotalesTest() {
        LOG.info("Ejecutando la prueba de lineas totales");
        
        assertEquals(6,e.lineasTotales("/Archivo01.java"));
	assertEquals(7,e.lineasTotales("/Archivo02.java"));
	assertEquals(12,e.lineasTotales("/Archivo03.java"));
	assertEquals(13,e.lineasTotales("/Archivo04.java"));
	assertEquals(11,e.lineasTotales("/Archivo05.java"));
    }

    @Test
    public void lineasEjecutablesTest() {
        LOG.info("Ejecutando la prueba de lineas ejecutables");
        
        assertEquals(6,e.lineasEjecutables("/Archivo01.java"));
	assertEquals(6,e.lineasEjecutables("/Archivo02.java"));
	assertEquals(6,e.lineasEjecutables("/Archivo03.java"));
	assertEquals(6,e.lineasEjecutables("/Archivo04.java"));
	assertEquals(6,e.lineasEjecutables("/Archivo05.java"));
    }

    @Test
    public void lineasComentariosTest() {
        LOG.info("Ejecutando la prueba de lineas comentarios");
        
        assertEquals(0,e.lineasComentarios("/Archivo01.java"));
	assertEquals(2,e.lineasComentarios("/Archivo02.java"));
	assertEquals(7,e.lineasComentarios("/Archivo03.java"));
	assertEquals(8,e.lineasComentarios("/Archivo04.java"));
	assertEquals(6,e.lineasComentarios("/Archivo05.java"));
    }

    @After
    public void after() {
        System.out.println("After Test Case");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("After Class");
    }
}
