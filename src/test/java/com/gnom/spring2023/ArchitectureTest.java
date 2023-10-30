package com.gnom.spring2023;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ArchitectureTest {
    private JavaClasses importedClasses;

    private String packageName;

    @BeforeEach
    public void setup() {
        packageName = "com.gnom.spring2023";
        importedClasses = new ClassFileImporter().importPackages(packageName);
    }

    @Test
    @DisplayName("Соблюдены требования слоеной архитектуры")
    void testLayeredArchitecture() {
        Architectures.layeredArchitecture().consideringAllDependencies()
                .layer("domain").definedBy(packageName + ".domain..")
                .layer("app").definedBy(packageName + ".app..")
                .layer("extern").definedBy(packageName + ".extern..")
                .whereLayer("app").mayOnlyBeAccessedByLayers("app", "extern")
                .whereLayer("extern").mayOnlyBeAccessedByLayers("extern")
                .check(importedClasses);
    }
}
