package com.example.projetmpisi.demo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ExempleAvecBugsTest {

    private final ExempleAvecBugs demo = new ExempleAvecBugs();

    @Test
    void testCoverageForBugs() {
        // Covers getNomComplet
        assertEquals("JOHN DOE", demo.getNomComplet("John", "Doe"));
        
        // Covers evaluerNote branches
        assertEquals("Excellent", demo.evaluerNote(95));
        assertEquals("Passable", demo.evaluerNote(55));
        assertEquals("Insuffisant", demo.evaluerNote(10));
        
        // Covers remaining methods
        demo.methode1();
        demo.methode2();
        demo.rechercherUtilisateur("admin");
        demo.lireFichier("invalid/path");
    }
}