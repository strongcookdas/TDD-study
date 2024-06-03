package org.example.archunittest;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

@AnalyzeClasses(packages = "org.example.archunittest")
public class ArchUnitTests {
    @ArchTest
    public static final ArchRule 컨트롤러는_서비스패키지만_접근가능하다=
            classes().that().resideInAnyPackage("..controller..")
                    .should().accessClassesThat().resideInAnyPackage("..service..");

    @ArchTest
    public static final ArchRule 컨트롤러는_레파지토리패키지에_접근불가하다=
            noClasses().that().resideInAnyPackage("..controller..")
                    .should().accessClassesThat().resideInAnyPackage("..repository..");

    @ArchTest
    public static final ArchRule controllerNamingRuleTest =
            classes().that().resideInAPackage("..controller..")
                    .should().haveSimpleNameEndingWith("Controller")
                    .allowEmptyShould(true);

    @ArchTest
    Architectures.LayeredArchitecture layeredArchitectureRule = layeredArchitecture()
            .consideringAllDependencies()
            .layer("Controller").definedBy("..controller..")
            .layer("Service").definedBy("..service..")
            .layer("Repository").definedBy("..repository..")
            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
            .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller")
            .whereLayer("Repository").mayOnlyBeAccessedByLayers("Service");
}
