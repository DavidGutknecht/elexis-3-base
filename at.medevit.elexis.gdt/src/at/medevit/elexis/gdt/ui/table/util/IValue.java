package at.medevit.elexis.gdt.ui.table.util;

/**
 * IValue describes a value that can be get from / set to an object.
 * 
 * @author Ralf Ebert <info@ralfebert.de>
 */
public interface IValue {
	
	public Object getValue(Object element);
	
	public void setValue(Object element, Object value);
	
}
