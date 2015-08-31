public interface ISuiteChainee {
	void add(ElementSuite nouvelElement);
	void removeAt(int position);
	void removeItem(ElementSuite element);
	void setAt(ElementSuite nouvelElement, int position);
	ElementSuite getAt(int position);
	int getSize();
	void reset();
	boolean isValid();
}