package ch.elexis.base.ch.arzttarife.xml.exporter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.junit.Test;

import at.medevit.elexis.tarmed.model.TarmedJaxbUtil;
import ch.elexis.base.ch.arzttarife.test.TestData;
import ch.elexis.base.ch.arzttarife.test.TestData.TestSzenario;
import ch.elexis.core.data.interfaces.IRnOutputter;
import ch.elexis.core.model.IInvoice;
import ch.fd.invoice450.request.RequestType;
import ch.fd.invoice450.request.VatRateType;
import ch.fd.invoice450.request.VatType;

public class Tarmed45ExporterTest {
	
	@Test
	public void doExportVatTest() throws IOException{
		TestSzenario szenario = TestData.getTestSzenarioInstance();
		assertNotNull(szenario);
		assertNotNull(szenario.getInvoices());
		assertFalse(szenario.getInvoices().isEmpty());
		Tarmed45Exporter exporter = new Tarmed45Exporter();
		List<IInvoice> invoices = szenario.getInvoices();
		RequestType nonNullRequest = null;
		for (IInvoice invoice : invoices) {
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			assertTrue(exporter.doExport(invoice, output, IRnOutputter.TYPE.ORIG));
			RequestType request = TarmedJaxbUtil
				.unmarshalInvoiceRequest450(new ByteArrayInputStream(output.toByteArray()));
			// check if the vat is included
			assertNotNull(request.getPayload().getBody().getTiersGarant());
			VatType vat = request.getPayload().getBody().getTiersGarant().getBalance().getVat();
			assertNotNull(vat);
			for (VatRateType vatRate : vat.getVatRate()) {
				if (vatRate.getVatRate() == 0.0) {
					assertEquals(0.0, vatRate.getVat(), 0.01);
				} else {
					Double expectedVat = (vatRate.getAmount() / (100.0 + vatRate.getVatRate()))
						* vatRate.getVatRate();
					assertEquals(expectedVat, vatRate.getVat(), 0.01);
					nonNullRequest = request;
				}
			}
		}
		assertNotNull(nonNullRequest);
	}
}