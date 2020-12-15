package ch.elexis.base.solr.ui.spotlight.ui;

import org.eclipse.swt.widgets.Composite;
import org.osgi.service.component.annotations.Component;
import ch.elexis.core.spotlight.ISpotlightResultEntry.Category;
import ch.elexis.core.spotlight.ui.ISpotlightResultEntryDetailComposite;
import ch.elexis.core.spotlight.ui.ISpotlightResultEntryDetailCompositeContributor;

@Component
public class EncounterSpotlightResultEntryDetailCompositeContributor
		implements ISpotlightResultEntryDetailCompositeContributor {
	
	@Override
	public ISpotlightResultEntryDetailComposite createDetailComposite(Composite parent, int style){
		return new EncounterSpotlightResultEntryDetailComposite(parent, style);
	}
	
	@Override
	public Category appliedForCategory(){
		return Category.ENCOUNTER;
	}
	
}
