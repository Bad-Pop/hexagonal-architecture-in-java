package fr.carbon.it.tech.prez.archi.bootstrap.client;

import com.tngtech.archunit.core.importer.ImportOption.DoNotIncludeTests;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.junit.CacheMode.FOREVER;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static fr.carbon.it.tech.prez.archi.bootstrap.PackagesAndLayers.BOOTSTRAP_PACKAGE;
import static fr.carbon.it.tech.prez.archi.bootstrap.PackagesAndLayers.CLIENT_PACKAGE;

@AnalyzeClasses(
    packages = "fr.carbon.it.tech.prez.archi.hexa.client",
    cacheMode = FOREVER,
    importOptions = {DoNotIncludeTests.class})
public class ClientDevelopmentRulesTest {

  @ArchTest
  public static final ArchRule CLIENT_DEVELOPMENT_RULE =
      classes()
          .that()
          .resideInAPackage(CLIENT_PACKAGE)
          .should()
          .onlyHaveDependentClassesThat()
          .resideInAnyPackage(CLIENT_PACKAGE, BOOTSTRAP_PACKAGE)
          .andShould()
          .onlyBeAccessed()
          .byClassesThat()
          .resideInAnyPackage(BOOTSTRAP_PACKAGE, CLIENT_PACKAGE)
          .andShould()
          .onlyHaveDependentClassesThat()
          .resideInAnyPackage(BOOTSTRAP_PACKAGE, CLIENT_PACKAGE);
}
