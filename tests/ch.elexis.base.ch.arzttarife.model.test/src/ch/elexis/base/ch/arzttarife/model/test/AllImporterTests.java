package ch.elexis.base.ch.arzttarife.model.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ch.elexis.base.ch.arzttarife.complementary.model.importer.ComplementaryImporterTest;
import ch.elexis.base.ch.arzttarife.pandemie.model.importer.PandemieImporterTest;
import ch.elexis.base.ch.arzttarife.tarmed.model.importer.TarmedReferenceDataImporterTest;

@RunWith(Suite.class)
@SuiteClasses({
	TarmedReferenceDataImporterTest.class, ComplementaryImporterTest.class,
	PandemieImporterTest.class
})
public class AllImporterTests {
	
}
